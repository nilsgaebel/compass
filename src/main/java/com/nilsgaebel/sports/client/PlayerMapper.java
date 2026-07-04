package com.nilsgaebel.sports.client;

import com.nilsgaebel.sports.domain.Player;
import jakarta.enterprise.context.ApplicationScoped;

/**
 * Maps a raw TheSportsDB player payload into the {@link Player} domain record.
 */
@ApplicationScoped
public class PlayerMapper {

    public Player toDomain(PlayerResponse.PlayerPayload payload) {
        return new Player(
                payload.idPlayer(),
                payload.strPlayer(),
                payload.strTeam(),
                payload.strPosition(),
                payload.strNationality(),
                payload.dateBorn(),
                payload.strThumb()
        );
    }
}
