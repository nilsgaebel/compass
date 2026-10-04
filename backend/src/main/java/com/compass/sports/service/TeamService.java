package com.compass.sports.service;

import com.compass.sports.client.EventResponse;
import com.compass.sports.client.PlayerResponse;
import com.compass.sports.client.SportsApiClient;
import com.compass.sports.client.SportsMapper;
import com.compass.sports.client.TheSportsDbResponse;
import com.compass.sports.domain.Event;
import com.compass.sports.domain.Player;
import com.compass.sports.domain.Team;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.List;
import java.util.Optional;

// Business logic for team-scoped data. All methods return empty (never null) when nothing matches.
@ApplicationScoped
public class TeamService {

    private final SportsApiClient client;
    private final SportsMapper mapper;

    @Inject
    public TeamService(@RestClient SportsApiClient client, SportsMapper mapper) {
        this.client = client;
        this.mapper = mapper;
    }

    public List<Team> searchByName(String name) {
        TheSportsDbResponse response = client.searchTeamsByName(name);
        if (response == null || response.teams() == null) {
            return List.of();
        }
        return response.teams().stream().map(mapper::toTeam).toList();
    }

    public Optional<Team> findById(String id) {
        TheSportsDbResponse response = client.lookupTeamById(id);
        if (response == null || response.teams() == null || response.teams().isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(mapper.toTeam(response.teams().get(0)));
    }

    public List<Event> nextEvents(String teamId) {
        return mapFixtures(client.nextEvents(teamId));
    }

    public List<Event> lastEvents(String teamId) {
        return mapFixtures(client.lastEvents(teamId));
    }

    public List<Player> players(String teamId) {
        PlayerResponse response = client.playersByTeam(teamId);
        if (response == null || response.player() == null) {
            return List.of();
        }
        return response.player().stream().map(mapper::toPlayer).toList();
    }

    private List<Event> mapFixtures(EventResponse response) {
        if (response == null) {
            return List.of();
        }
        return response.fixtures().stream().map(mapper::toEvent).toList();
    }
}
