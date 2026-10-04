package com.nilsgaebel.sports.client;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

// Raw league-list payload (all_leagues.php).
@JsonIgnoreProperties(ignoreUnknown = true)
public record LeagueResponse(
        @JsonProperty("leagues") List<LeaguePayload> leagues
) {

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record LeaguePayload(
            @JsonProperty("idLeague") String idLeague,
            @JsonProperty("strLeague") String strLeague,
            @JsonProperty("strSport") String strSport,
            @JsonProperty("strLeagueAlternate") String strLeagueAlternate
    ) {
    }
}
