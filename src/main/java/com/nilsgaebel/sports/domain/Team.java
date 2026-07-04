package com.nilsgaebel.sports.domain;

/**
 * Immutable domain representation of a team.
 * <p>
 * Deliberately decoupled from the external API's response shape: the
 * {@code client} layer maps the raw provider payload into this record so the
 * rest of the application never depends on a third party's field names.
 */
public record Team(
        String id,
        String name,
        String league,
        String country,
        String stadium,
        String sport,
        String badge,
        Integer formedYear,
        String website,
        String description
) {
}
