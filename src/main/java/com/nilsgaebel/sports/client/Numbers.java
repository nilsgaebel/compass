package com.nilsgaebel.sports.client;

/**
 * TheSportsDB sends every numeric field as a string, and frequently leaves it
 * empty or null. These helpers keep the mappers tidy and the domain types honest.
 */
final class Numbers {

    private Numbers() {
    }

    /** Parse to a boxed {@code Integer}, or {@code null} if absent/unparseable. */
    static Integer toIntOrNull(String value) {
        if (value == null || value.isBlank()) {
            return null;
        }
        try {
            return Integer.valueOf(value.trim());
        } catch (NumberFormatException e) {
            return null;
        }
    }

    /** Parse to a primitive {@code int}, falling back to {@code 0} when absent. */
    static int toIntOrZero(String value) {
        Integer parsed = toIntOrNull(value);
        return parsed != null ? parsed : 0;
    }
}
