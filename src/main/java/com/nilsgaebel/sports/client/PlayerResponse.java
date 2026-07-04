package com.nilsgaebel.sports.client;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Raw TheSportsDB roster payload from {@code lookup_all_players.php}.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record PlayerResponse(
        @JsonProperty("player") List<PlayerPayload> player
) {

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record PlayerPayload(
            @JsonProperty("idPlayer") String idPlayer,
            @JsonProperty("strPlayer") String strPlayer,
            @JsonProperty("strTeam") String strTeam,
            @JsonProperty("strPosition") String strPosition,
            @JsonProperty("strNationality") String strNationality,
            @JsonProperty("dateBorn") String dateBorn,
            @JsonProperty("strThumb") String strThumb
    ) {
    }
}
