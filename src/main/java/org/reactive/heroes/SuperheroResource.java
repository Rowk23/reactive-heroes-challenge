package org.reactive.heroes;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

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
    public Multi<Superhero> getAll() {
        return Multi.createFrom().iterable(store.values());
    }

    //Thread.sleep blocks the nonblocking endpoint
    @POST
    public Uni<Superhero> create(final Superhero hero) {
        return Uni.createFrom().item(() -> {
//            try {
//                Thread.sleep(3000);
//            } catch (final InterruptedException exception) {
//                throw new RuntimeException(exception);
//            }

            hero.setSecretHash(PowerHasher.hash(hero));

            store.put(hero.getId(), hero);
            return hero;
        });
    }
}