package com.nilsgaebel.sports.domain;

/**
 * Immutable domain representation of a single match/event.
 * <p>
 * Used for both upcoming and past fixtures of a team. Scores are nullable
 * because a not-yet-played event carries no result.
 */
public record Event(
        String id,
        String name,
        String league,
        String season,
        String homeTeam,
        String awayTeam,
        Integer homeScore,
        Integer awayScore,
        String date,
        String time,
        String venue,
        String status
) {
}
