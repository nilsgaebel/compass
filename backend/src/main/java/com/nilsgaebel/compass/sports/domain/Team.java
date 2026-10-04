package com.nilsgaebel.compass.sports.domain;

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
