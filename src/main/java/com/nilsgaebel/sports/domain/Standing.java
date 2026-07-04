package com.nilsgaebel.sports.domain;

/**
 * Immutable domain representation of one row in a league table.
 */
public record Standing(
        int rank,
        String teamId,
        String teamName,
        int played,
        int win,
        int draw,
        int loss,
        int goalsFor,
        int goalsAgainst,
        int goalDifference,
        int points
) {
}
