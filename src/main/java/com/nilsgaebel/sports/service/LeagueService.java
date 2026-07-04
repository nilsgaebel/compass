package com.nilsgaebel.sports.service;

import com.nilsgaebel.sports.client.SportsApiClient;
import com.nilsgaebel.sports.client.StandingMapper;
import com.nilsgaebel.sports.client.TableResponse;
import com.nilsgaebel.sports.domain.Standing;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.List;

/**
 * Application service for league-scoped data. Kept separate from
 * {@link TeamService} so each service maps to one resource concept.
 */
@ApplicationScoped
public class LeagueService {

    private final SportsApiClient sportsApiClient;
    private final StandingMapper standingMapper;

    @Inject
    public LeagueService(@RestClient SportsApiClient sportsApiClient, StandingMapper standingMapper) {
        this.sportsApiClient = sportsApiClient;
        this.standingMapper = standingMapper;
    }

    /**
     * The standings for a league and season, ordered as the provider returns
     * them (by rank). Empty list when the combination has no table.
     */
    public List<Standing> table(String leagueId, String season) {
        TableResponse response = sportsApiClient.leagueTable(leagueId, season);
        if (response == null || response.table() == null) {
            return List.of();
        }
        return response.table().stream()
                .map(standingMapper::toDomain)
                .toList();
    }
}
