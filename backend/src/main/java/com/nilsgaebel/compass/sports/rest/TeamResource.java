package com.nilsgaebel.compass.sports.rest;

import com.nilsgaebel.compass.sports.domain.Team;
import com.nilsgaebel.compass.sports.service.TeamService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.Optional;

@Path("/teams")
@Produces(MediaType.APPLICATION_JSON)
public class TeamResource {

    private final TeamService teamService;

    @Inject
    public TeamResource(TeamService teamService) {
        this.teamService = teamService;
    }

    @GET
    @Path("/search")
    public Response searchByName(@QueryParam("name") String name) {
        if (isBlank(name)) {
            return badRequest("Query parameter 'name' is required");
        }
        return Response.ok(teamService.searchByName(name)).build();
    }

    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") String id) {
        if (isBlank(id)) {
            return badRequest("Path parameter 'id' is required");
        }
        Optional<Team> team = teamService.findById(id);
        return team.map(t -> Response.ok(t).build())
                .orElseGet(() -> Response.status(Response.Status.NOT_FOUND)
                        .entity("No team found for id '" + id + "'").build());
    }

    @GET
    @Path("/{id}/events/next")
    public Response nextEvents(@PathParam("id") String id) {
        if (isBlank(id)) {
            return badRequest("Path parameter 'id' is required");
        }
        return Response.ok(teamService.nextEvents(id)).build();
    }

    @GET
    @Path("/{id}/events/last")
    public Response lastEvents(@PathParam("id") String id) {
        if (isBlank(id)) {
            return badRequest("Path parameter 'id' is required");
        }
        return Response.ok(teamService.lastEvents(id)).build();
    }

    @GET
    @Path("/{id}/players")
    public Response players(@PathParam("id") String id) {
        if (isBlank(id)) {
            return badRequest("Path parameter 'id' is required");
        }
        return Response.ok(teamService.players(id)).build();
    }

    private static boolean isBlank(String value) {
        return value == null || value.isBlank();
    }

    private static Response badRequest(String message) {
        return Response.status(Response.Status.BAD_REQUEST).entity(message).build();
    }
}
