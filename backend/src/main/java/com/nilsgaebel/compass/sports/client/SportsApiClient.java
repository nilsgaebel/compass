package com.nilsgaebel.compass.sports.client;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

// Typed REST client for TheSportsDB. Base URL comes from config key "sports-api".
@RegisterRestClient(configKey = "sports-api")
@Produces(MediaType.APPLICATION_JSON)
public interface SportsApiClient {

    @GET
    @Path("/searchteams.php")
    TheSportsDbResponse searchTeamsByName(@QueryParam("t") String teamName);

    @GET
    @Path("/lookupteam.php")
    TheSportsDbResponse lookupTeamById(@QueryParam("id") String teamId);

    @GET
    @Path("/eventsnext.php")
    EventResponse nextEvents(@QueryParam("id") String teamId);

    @GET
    @Path("/eventslast.php")
    EventResponse lastEvents(@QueryParam("id") String teamId);

    @GET
    @Path("/lookup_all_players.php")
    PlayerResponse playersByTeam(@QueryParam("id") String teamId);

    @GET
    @Path("/lookuptable.php")
    TableResponse leagueTable(@QueryParam("l") String leagueId, @QueryParam("s") String season);

    @GET
    @Path("/all_leagues.php")
    LeagueResponse allLeagues();

    @GET
    @Path("/lookupevent.php")
    EventResponse lookupEventById(@QueryParam("id") String eventId);
}
