package com.nilsgaebel.sports.client;

import com.nilsgaebel.sports.domain.Standing;
import jakarta.enterprise.context.ApplicationScoped;

/**
 * Maps a raw TheSportsDB table row into the {@link Standing} domain record.
 * Counters use primitive ints and default to 0, since a table row without a
 * value there is meaningless rather than "unknown".
 */
@ApplicationScoped
public class StandingMapper {

    public Standing toDomain(TableResponse.TablePayload payload) {
        return new Standing(
                Numbers.toIntOrZero(payload.intRank()),
                payload.idTeam(),
                payload.strTeam(),
                Numbers.toIntOrZero(payload.intPlayed()),
                Numbers.toIntOrZero(payload.intWin()),
                Numbers.toIntOrZero(payload.intDraw()),
                Numbers.toIntOrZero(payload.intLoss()),
                Numbers.toIntOrZero(payload.intGoalsFor()),
                Numbers.toIntOrZero(payload.intGoalsAgainst()),
                Numbers.toIntOrZero(payload.intGoalDifference()),
                Numbers.toIntOrZero(payload.intPoints())
        );
    }
}
