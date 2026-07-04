package com.nilsgaebel.sports.domain;

/**
 * Immutable domain representation of a player on a team's roster.
 */
public record Player(
        String id,
        String name,
        String team,
        String position,
        String nationality,
        String dateOfBirth,
        String thumbnail
) {
}
