package com.nilsgaebel.sports.client;

import com.nilsgaebel.sports.domain.Team;
import jakarta.enterprise.context.ApplicationScoped;

/**
 * Translates the external provider's payload into our own domain type.
 * <p>
 * Isolating this mapping in one place is the whole point of the anti-corruption
 * layer: if TheSportsDB renames a field or we swap providers entirely, this is
 * the only class that changes.
 */
@ApplicationScoped
public class TeamMapper {

    public Team toDomain(TheSportsDbResponse.TeamPayload payload) {
        return new Team(
                payload.idTeam(),
                payload.strTeam(),
                payload.strLeague(),
                payload.strCountry(),
                payload.strStadium(),
                payload.strSport(),
                payload.strBadge(),
                Numbers.toIntOrNull(payload.intFormedYear()),
                payload.strWebsite(),
                payload.strDescriptionEN()
        );
    }
}
