package com.nilsgaebel.sports.client;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Raw TheSportsDB league-table payload from {@code lookuptable.php}.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record TableResponse(
        @JsonProperty("table") List<TablePayload> table
) {

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record TablePayload(
            @JsonProperty("intRank") String intRank,
            @JsonProperty("idTeam") String idTeam,
            @JsonProperty("strTeam") String strTeam,
            @JsonProperty("intPlayed") String intPlayed,
            @JsonProperty("intWin") String intWin,
            @JsonProperty("intDraw") String intDraw,
            @JsonProperty("intLoss") String intLoss,
            @JsonProperty("intGoalsFor") String intGoalsFor,
            @JsonProperty("intGoalsAgainst") String intGoalsAgainst,
            @JsonProperty("intGoalDifference") String intGoalDifference,
            @JsonProperty("intPoints") String intPoints
    ) {
    }
}
