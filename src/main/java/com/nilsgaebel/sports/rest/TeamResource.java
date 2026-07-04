package com.nilsgaebel.sports.rest;

import com.nilsgaebel.sports.domain.Event;
import com.nilsgaebel.sports.domain.Player;
import com.nilsgaebel.sports.domain.Team;
import com.nilsgaebel.sports.service.TeamService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.Optional;

/**
 * HTTP boundary for team resources. Intentionally thin: it validates input,
 * delegates to {@link TeamService}, and shapes the HTTP response. No business
 * logic lives here.
 */
@Path("/teams")
@Produces(MediaType.APPLICATION_JSON)
public class TeamResource {

    private final TeamService teamService;

    @Inject
    public TeamResource(TeamService teamService) {
        this.teamService = teamService;
    }

    /**
     * GET /teams/search?name=Arsenal
     */
    @GET
    @Path("/search")
    public Response searchByName(@QueryParam("name") String name) {
        if (name == null || name.isBlank()) {
            return badRequest("Query parameter 'name' is required");
        }
        List<Team> teams = teamService.searchByName(name);
        return Response.ok(teams).build();
    }

    /**
     * GET /teams/{id} — full details for one team, 404 when unknown.
     */
    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") String id) {
        if (isBlank(id)) {
            return badRequest("Path parameter 'id' is required");
        }
        Optional<Team> team = teamService.findById(id);
        return team.map(t -> Response.ok(t).build())
                .orElseGet(() -> Response.status(Response.Status.NOT_FOUND)
                        .entity("No team found for id '" + id + "'")
                        .build());
    }

    /**
     * GET /teams/{id}/events/next — upcoming fixtures.
     */
    @GET
    @Path("/{id}/events/next")
    public Response nextEvents(@PathParam("id") String id) {
        if (isBlank(id)) {
            return badRequest("Path parameter 'id' is required");
        }
        List<Event> events = teamService.nextEvents(id);
        return Response.ok(events).build();
    }

    /**
     * GET /teams/{id}/events/last — most recent results.
     */
    @GET
    @Path("/{id}/events/last")
    public Response lastEvents(@PathParam("id") String id) {
        if (isBlank(id)) {
            return badRequest("Path parameter 'id' is required");
        }
        List<Event> events = teamService.lastEvents(id);
        return Response.ok(events).build();
    }

    /**
     * GET /teams/{id}/players — the team's roster.
     */
    @GET
    @Path("/{id}/players")
    public Response players(@PathParam("id") String id) {
        if (isBlank(id)) {
            return badRequest("Path parameter 'id' is required");
        }
        List<Player> players = teamService.players(id);
        return Response.ok(players).build();
    }

    private static boolean isBlank(String value) {
        return value == null || value.isBlank();
    }

    private static Response badRequest(String message) {
        return Response.status(Response.Status.BAD_REQUEST).entity(message).build();
    }
}
