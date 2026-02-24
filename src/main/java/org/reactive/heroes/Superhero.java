package org.reactive.heroes;

import io.quarkus.runtime.annotations.RegisterForReflection;

/**
 * Simple POJO representing a superhero with an id, name, power, and a secret hash.
 */
@RegisterForReflection
public class Superhero {

    private Long id;
    private String name;
    private String power;
    private String secretHash;

    /**
     * Default constructor.
     *
     */
    public Superhero() {
    }

    /**
     * Constructor with parameters.
     *
     * @param id    the id of the superhero
     * @param name  the name of the superhero
     * @param power the power of the superhero
     *
     */
    public Superhero(final Long id, final String name, final String power) {
        this.id = id;
        this.name = name;
        this.power = power;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getPower() {
        return power;
    }

    public void setPower(final String power) {
        this.power = power;
    }

    public String getSecretHash() {
        return secretHash;
    }

    public void setSecretHash(final String secretHash) {
        this.secretHash = secretHash;
    }
}