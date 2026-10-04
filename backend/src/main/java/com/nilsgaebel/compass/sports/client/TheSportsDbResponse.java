package com.nilsgaebel.compass.sports.client;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

// Raw TheSportsDB team payload (searchteams.php / lookupteam.php).
@JsonIgnoreProperties(ignoreUnknown = true)
public record TheSportsDbResponse(
        @JsonProperty("teams") List<TeamPayload> teams
) {

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record TeamPayload(
            @JsonProperty("idTeam") String idTeam,
            @JsonProperty("strTeam") String strTeam,
            @JsonProperty("strLeague") String strLeague,
            @JsonProperty("strCountry") String strCountry,
            @JsonProperty("strStadium") String strStadium,
            @JsonProperty("strSport") String strSport,
            @JsonProperty("strBadge") String strBadge,
            @JsonProperty("intFormedYear") String intFormedYear,
            @JsonProperty("strWebsite") String strWebsite,
            @JsonProperty("strDescriptionEN") String strDescriptionEN
    ) {
    }
}
