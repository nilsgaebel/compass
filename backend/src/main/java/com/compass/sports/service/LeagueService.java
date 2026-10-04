package com.compass.sports.service;

import com.compass.sports.client.LeagueResponse;
import com.compass.sports.client.SportsApiClient;
import com.compass.sports.client.SportsMapper;
import com.compass.sports.client.TableResponse;
import com.compass.sports.domain.League;
import com.compass.sports.domain.Standing;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.List;

// Business logic for league-scoped data.
@ApplicationScoped
public class LeagueService {

    private final SportsApiClient client;
    private final SportsMapper mapper;

    @Inject
    public LeagueService(@RestClient SportsApiClient client, SportsMapper mapper) {
        this.client = client;
        this.mapper = mapper;
    }

    public List<Standing> table(String leagueId, String season) {
        TableResponse response = client.leagueTable(leagueId, season);
        if (response == null || response.table() == null) {
            return List.of();
        }
        return response.table().stream().map(mapper::toStanding).toList();
    }

    public List<League> allLeagues() {
        LeagueResponse response = client.allLeagues();
        if (response == null || response.leagues() == null) {
            return List.of();
        }
        return response.leagues().stream().map(mapper::toLeague).toList();
    }
}
