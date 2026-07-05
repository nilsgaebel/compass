package com.nilsgaebel.sports.domain;

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
