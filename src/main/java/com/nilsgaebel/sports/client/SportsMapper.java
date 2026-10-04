package com.nilsgaebel.sports.client;

import com.nilsgaebel.sports.domain.Event;
import com.nilsgaebel.sports.domain.League;
import com.nilsgaebel.sports.domain.Player;
import com.nilsgaebel.sports.domain.Standing;
import com.nilsgaebel.sports.domain.Team;
import jakarta.enterprise.context.ApplicationScoped;

// Single place that translates raw TheSportsDB payloads into our domain records.
@ApplicationScoped
public class SportsMapper {

    public Team toTeam(TheSportsDbResponse.TeamPayload p) {
        return new Team(p.idTeam(), p.strTeam(), p.strLeague(), p.strCountry(), p.strStadium(),
                p.strSport(), p.strBadge(), toIntOrNull(p.intFormedYear()), p.strWebsite(), p.strDescriptionEN());
    }

    public Event toEvent(EventResponse.EventPayload p) {
        return new Event(p.idEvent(), p.strEvent(), p.strLeague(), p.strSeason(), p.strHomeTeam(),
                p.strAwayTeam(), toIntOrNull(p.intHomeScore()), toIntOrNull(p.intAwayScore()),
                p.dateEvent(), p.strTime(), p.strVenue(), p.strStatus());
    }

    public Player toPlayer(PlayerResponse.PlayerPayload p) {
        return new Player(p.idPlayer(), p.strPlayer(), p.strTeam(), p.strPosition(),
                p.strNationality(), p.dateBorn(), p.strThumb());
    }

    public Standing toStanding(TableResponse.TablePayload p) {
        return new Standing(toIntOrZero(p.intRank()), p.idTeam(), p.strTeam(), toIntOrZero(p.intPlayed()),
                toIntOrZero(p.intWin()), toIntOrZero(p.intDraw()), toIntOrZero(p.intLoss()),
                toIntOrZero(p.intGoalsFor()), toIntOrZero(p.intGoalsAgainst()),
                toIntOrZero(p.intGoalDifference()), toIntOrZero(p.intPoints()));
    }

    public League toLeague(LeagueResponse.LeaguePayload p) {
        return new League(p.idLeague(), p.strLeague(), p.strSport(), p.strLeagueAlternate());
    }

    // TheSportsDB sends numbers as strings, often empty.
    private static Integer toIntOrNull(String value) {
        if (value == null || value.isBlank()) {
            return null;
        }
        try {
            return Integer.valueOf(value.trim());
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private static int toIntOrZero(String value) {
        Integer parsed = toIntOrNull(value);
        return parsed != null ? parsed : 0;
    }
}
