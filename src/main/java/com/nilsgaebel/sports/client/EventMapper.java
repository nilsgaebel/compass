package com.nilsgaebel.sports.client;

import com.nilsgaebel.sports.domain.Event;
import jakarta.enterprise.context.ApplicationScoped;

/**
 * Maps a raw TheSportsDB event payload into the {@link Event} domain record.
 * Scores stay nullable so an unplayed fixture is distinguishable from a 0–0.
 */
@ApplicationScoped
public class EventMapper {

    public Event toDomain(EventResponse.EventPayload payload) {
        return new Event(
                payload.idEvent(),
                payload.strEvent(),
                payload.strLeague(),
                payload.strSeason(),
                payload.strHomeTeam(),
                payload.strAwayTeam(),
                Numbers.toIntOrNull(payload.intHomeScore()),
                Numbers.toIntOrNull(payload.intAwayScore()),
                payload.dateEvent(),
                payload.strTime(),
                payload.strVenue(),
                payload.strStatus()
        );
    }
}
