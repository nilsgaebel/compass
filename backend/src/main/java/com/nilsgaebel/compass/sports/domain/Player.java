package com.nilsgaebel.compass.sports.domain;

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
