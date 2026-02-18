package org.reactive.heroes;

import java.lang.reflect.Field;

/**
 * Utility class for generating hash strings from objects using reflection.
 */
public class PowerHasher {

    /**
     * Generates a hexadecimal hash string from the given object.
     * <p>
     * This method uses reflection to access all declared fields of the object,
     * concatenates their string representations, and returns a hexadecimal hash
     * of the resulting string.
     * </p>
     *
     * @param object the object to hash; must not be null
     * @return a hexadecimal string representation of the object's hash
     * @throws RuntimeException if reflection operations fail or if any other
     *                          exception occurs during field access
     */
    public static String hash(final Object object) {
        try {
            final Field[] fields = object.getClass().getDeclaredFields();
            final StringBuilder sb = new StringBuilder();

            for (final Field field : fields) {
                field.setAccessible(true);
                final Object value = field.get(object);
                if (value != null) {
                    sb.append(value.toString());
                }
            }

            return Integer.toHexString(sb.toString().hashCode());
        } catch (final Exception exception) {
            throw new RuntimeException(exception);
        }
    }
}