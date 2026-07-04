package com.nilsgaebel.sports.client;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

/**
 * Typed, declarative REST client for TheSportsDB.
 * <p>
 * Quarkus generates the implementation at build time. The base URL is bound
 * via config key {@code sports-api} in application.properties, so nothing about
 * the endpoint is hard-coded here.
 */
@RegisterRestClient(configKey = "sports-api")
@Produces(MediaType.APPLICATION_JSON)
public interface SportsApiClient {

    /**
     * Look up teams by name, e.g. {@code searchteams.php?t=Arsenal}.
     */
    @GET
    @Path("/searchteams.php")
    TheSportsDbResponse searchTeamsByName(@QueryParam("t") String teamName);

    /**
     * Look up a single team by its provider id, e.g. {@code lookupteam.php?id=133604}.
     */
    @GET
    @Path("/lookupteam.php")
    TheSportsDbResponse lookupTeamById(@QueryParam("id") String teamId);

    /**
     * The next scheduled fixtures for a team, e.g. {@code eventsnext.php?id=133604}.
     */
    @GET
    @Path("/eventsnext.php")
    EventResponse nextEvents(@QueryParam("id") String teamId);

    /**
     * The most recent results for a team, e.g. {@code eventslast.php?id=133604}.
     */
    @GET
    @Path("/eventslast.php")
    EventResponse lastEvents(@QueryParam("id") String teamId);

    /**
     * The full roster of a team, e.g. {@code lookup_all_players.php?id=133604}.
     */
    @GET
    @Path("/lookup_all_players.php")
    PlayerResponse playersByTeam(@QueryParam("id") String teamId);

    /**
     * The standings for a league and season, e.g.
     * {@code lookuptable.php?l=4328&s=2020-2021}.
     */
    @GET
    @Path("/lookuptable.php")
    TableResponse leagueTable(@QueryParam("l") String leagueId, @QueryParam("s") String season);
}
