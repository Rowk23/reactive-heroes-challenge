package org.reactive.heroes;


/**
 * Utility class that uses reflection to create objects.
 */
public class InstanceCreator {

    /**
     * Creates a new instance of the given class using reflection.
     *
     * @param clazz the class to instantiate
     * @param <T>   the type of the class
     * @return a new instance of the class
     * @throws RuntimeException if reflection operations fail
     */
    public static <T> T createInstance(final Class<T> clazz) {
        try {
            return clazz.getDeclaredConstructor().newInstance();
        } catch (final Exception exception) {
            throw new RuntimeException("Failed to create instance of " + clazz.getName(), exception);
        }
    }

}

