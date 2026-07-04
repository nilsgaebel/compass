package com.nilsgaebel.sports.service;

import com.nilsgaebel.sports.client.EventMapper;
import com.nilsgaebel.sports.client.EventResponse;
import com.nilsgaebel.sports.client.PlayerMapper;
import com.nilsgaebel.sports.client.PlayerResponse;
import com.nilsgaebel.sports.client.SportsApiClient;
import com.nilsgaebel.sports.client.TeamMapper;
import com.nilsgaebel.sports.client.TheSportsDbResponse;
import com.nilsgaebel.sports.domain.Event;
import com.nilsgaebel.sports.domain.Player;
import com.nilsgaebel.sports.domain.Team;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.List;
import java.util.Optional;

/**
 * Application service: holds the business logic and orchestrates the
 * collaborators (remote client + mappers). The REST resource stays thin and
 * delegates here, which keeps HTTP concerns and domain logic separated.
 */
@ApplicationScoped
public class TeamService {

    private final SportsApiClient sportsApiClient;
    private final TeamMapper teamMapper;
    private final EventMapper eventMapper;
    private final PlayerMapper playerMapper;

    @Inject
    public TeamService(@RestClient SportsApiClient sportsApiClient,
                       TeamMapper teamMapper,
                       EventMapper eventMapper,
                       PlayerMapper playerMapper) {
        this.sportsApiClient = sportsApiClient;
        this.teamMapper = teamMapper;
        this.eventMapper = eventMapper;
        this.playerMapper = playerMapper;
    }

    /**
     * Search teams by name and return them as domain objects.
     * Returns an empty list (never null) when the provider has no match.
     */
    public List<Team> searchByName(String name) {
        TheSportsDbResponse response = sportsApiClient.searchTeamsByName(name);
        if (response == null || response.teams() == null) {
            return List.of();
        }
        return response.teams().stream()
                .map(teamMapper::toDomain)
                .toList();
    }

    /**
     * Look up a single team by its provider id. Empty when nothing matches.
     */
    public Optional<Team> findById(String id) {
        TheSportsDbResponse response = sportsApiClient.lookupTeamById(id);
        if (response == null || response.teams() == null || response.teams().isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(teamMapper.toDomain(response.teams().get(0)));
    }

    /**
     * The next scheduled fixtures for a team (empty list when none/unknown).
     */
    public List<Event> nextEvents(String teamId) {
        return mapFixtures(sportsApiClient.nextEvents(teamId));
    }

    /**
     * The most recent results for a team (empty list when none/unknown).
     */
    public List<Event> lastEvents(String teamId) {
        return mapFixtures(sportsApiClient.lastEvents(teamId));
    }

    /**
     * The full roster of a team (empty list when none/unknown).
     */
    public List<Player> players(String teamId) {
        PlayerResponse response = sportsApiClient.playersByTeam(teamId);
        if (response == null || response.player() == null) {
            return List.of();
        }
        return response.player().stream()
                .map(playerMapper::toDomain)
                .toList();
    }

    private List<Event> mapFixtures(EventResponse response) {
        if (response == null) {
            return List.of();
        }
        return response.fixtures().stream()
                .map(eventMapper::toDomain)
                .toList();
    }
}
