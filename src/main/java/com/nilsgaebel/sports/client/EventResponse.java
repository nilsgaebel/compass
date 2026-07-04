package com.nilsgaebel.sports.client;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Raw TheSportsDB event payload.
 * <p>
 * The provider is inconsistent here: {@code eventsnext.php} nests fixtures
 * under {@code "events"} while {@code eventslast.php} uses {@code "results"}.
 * We map both and expose {@link #fixtures()} so callers never see the quirk.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record EventResponse(
        @JsonProperty("events") List<EventPayload> events,
        @JsonProperty("results") List<EventPayload> results
) {

    /** Whichever list the provider populated for this particular endpoint. */
    public List<EventPayload> fixtures() {
        if (events != null) {
            return events;
        }
        return results != null ? results : List.of();
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record EventPayload(
            @JsonProperty("idEvent") String idEvent,
            @JsonProperty("strEvent") String strEvent,
            @JsonProperty("strLeague") String strLeague,
            @JsonProperty("strSeason") String strSeason,
            @JsonProperty("strHomeTeam") String strHomeTeam,
            @JsonProperty("strAwayTeam") String strAwayTeam,
            @JsonProperty("intHomeScore") String intHomeScore,
            @JsonProperty("intAwayScore") String intAwayScore,
            @JsonProperty("dateEvent") String dateEvent,
            @JsonProperty("strTime") String strTime,
            @JsonProperty("strVenue") String strVenue,
            @JsonProperty("strStatus") String strStatus
    ) {
    }
}
