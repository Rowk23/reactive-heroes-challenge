package org.reactive.heroes;

import io.smallrye.common.annotation.Blocking;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Resource class for managing superheroes. Provides endpoints to create and retrieve superheroes.
 */
@Path("/heroes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SuperheroResource {

    // stores superheroes in memory; in a real application, this would be a database
    private final Map<Long, Superhero> store = new ConcurrentHashMap<>();

    @GET
    public Response getAll() {
        return Response.ok(store.values()).build();
    }

    @POST
    @Blocking
    public Response create(@QueryParam("id") final Long id, @QueryParam("name") final String name,
                           final @QueryParam("power") String power) {
        try {

            /*
            The constructor is called by reflection, the Superhero.class constructor is not used directly,
            marking it as not needed for the native package
             */
            final Superhero hero = InstanceCreator.createInstance(Superhero.class);
            hero.setId(id);
            hero.setName(name);
            hero.setPower(power);
            hero.setSecretHash(PowerHasher.hash(hero));

            store.put(hero.getId(), hero);

            return Response.ok("Created hero: " + hero.getName() + " with power: " + hero.getPower()).build();
        } catch (Exception exception) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("An error occurred! Please check the implementation to find out the problem!")
                    .build();
        }
    }
}