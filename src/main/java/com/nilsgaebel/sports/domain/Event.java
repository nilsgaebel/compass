package com.nilsgaebel.sports.domain;

// Scores are null for a fixture that hasn't been played yet.
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
