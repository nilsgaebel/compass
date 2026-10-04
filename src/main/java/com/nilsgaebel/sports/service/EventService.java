package com.nilsgaebel.sports.service;

import com.nilsgaebel.sports.client.EventResponse;
import com.nilsgaebel.sports.client.SportsApiClient;
import com.nilsgaebel.sports.client.SportsMapper;
import com.nilsgaebel.sports.domain.Event;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.List;
import java.util.Optional;

// Business logic for single-event lookups.
@ApplicationScoped
public class EventService {

    private final SportsApiClient client;
    private final SportsMapper mapper;

    @Inject
    public EventService(@RestClient SportsApiClient client, SportsMapper mapper) {
        this.client = client;
        this.mapper = mapper;
    }

    public Optional<Event> findById(String id) {
        EventResponse response = client.lookupEventById(id);
        if (response == null) {
            return Optional.empty();
        }
        List<EventResponse.EventPayload> fixtures = response.fixtures();
        if (fixtures.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(mapper.toEvent(fixtures.get(0)));
    }
}
