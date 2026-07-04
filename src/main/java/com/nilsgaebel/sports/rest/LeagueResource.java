package com.nilsgaebel.sports.rest;

import com.nilsgaebel.sports.domain.Standing;
import com.nilsgaebel.sports.service.LeagueService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

/**
 * HTTP boundary for league resources. Thin by design: validates input,
 * delegates to {@link LeagueService}, and shapes the response.
 */
@Path("/leagues")
@Produces(MediaType.APPLICATION_JSON)
public class LeagueResource {

    private final LeagueService leagueService;

    @Inject
    public LeagueResource(LeagueService leagueService) {
        this.leagueService = leagueService;
    }

    /**
     * GET /leagues/{id}/table?season=2020-2021 — the league table for a season.
     */
    @GET
    @Path("/{id}/table")
    public Response table(@PathParam("id") String id, @QueryParam("season") String season) {
        if (id == null || id.isBlank()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Path parameter 'id' is required")
                    .build();
        }
        if (season == null || season.isBlank()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Query parameter 'season' is required (e.g. 2020-2021)")
                    .build();
        }
        List<Standing> table = leagueService.table(id, season);
        return Response.ok(table).build();
    }
}
