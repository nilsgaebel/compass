package com.nilsgaebel.sports.client;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * DTOs mirroring the raw TheSportsDB team payload.
 * <p>
 * These live in the client layer on purpose. Only the {@code SportsApiClient}
 * and the mapping code touch them; the rest of the app works with the clean
 * {@link com.nilsgaebel.sports.domain.Team} record instead. {@code @JsonIgnoreProperties}
 * keeps us resilient to the many fields we don't care about.
 */
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
