package com.nilsgaebel.sports.rest;

import com.nilsgaebel.sports.service.LeagueService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/leagues")
@Produces(MediaType.APPLICATION_JSON)
public class LeagueResource {

    private final LeagueService leagueService;

    @Inject
    public LeagueResource(LeagueService leagueService) {
        this.leagueService = leagueService;
    }

    @GET
    public Response all() {
        return Response.ok(leagueService.allLeagues()).build();
    }

    @GET
    @Path("/{id}/table")
    public Response table(@PathParam("id") String id, @QueryParam("season") String season) {
        if (id == null || id.isBlank()) {
            return badRequest("Path parameter 'id' is required");
        }
        if (season == null || season.isBlank()) {
            return badRequest("Query parameter 'season' is required (e.g. 2020-2021)");
        }
        return Response.ok(leagueService.table(id, season)).build();
    }

    private static Response badRequest(String message) {
        return Response.status(Response.Status.BAD_REQUEST).entity(message).build();
    }
}
