package com.compass.sports.rest;

import com.compass.sports.service.EventService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/events")
@Produces(MediaType.APPLICATION_JSON)
public class EventResource {

    private final EventService eventService;

    @Inject
    public EventResource(EventService eventService) {
        this.eventService = eventService;
    }

    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") String id) {
        if (id == null || id.isBlank()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Path parameter 'id' is required").build();
        }
        return eventService.findById(id)
                .map(e -> Response.ok(e).build())
                .orElseGet(() -> Response.status(Response.Status.NOT_FOUND)
                        .entity("No event found for id '" + id + "'").build());
    }
}
