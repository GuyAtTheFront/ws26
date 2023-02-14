package iss.nus.workshop26.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import iss.nus.workshop26.models.Game;
import iss.nus.workshop26.repositories.GamesRepository;
import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;

@Service
public class GamesService {
    
    @Autowired
    private GamesRepository gameRepo;

    public JsonObject findGamesLimitOffset(Integer limit, Integer offset) {

        List<Game> games = gameRepo.findAllGames(limit, offset);

        JsonArrayBuilder arrBuilder = Json.createArrayBuilder();
        for (Game game : games) {
            arrBuilder.add(game.toJsonShort());
        }

        return Json.createObjectBuilder()
                .add("games", arrBuilder)
                .add("offset", offset)
                .add("limit", limit)
                .add("total", games.size())
                .add("timestamp", games.get(0).getTimestamp().toString())
                .build();
    }

    public JsonObject findGamesSortRankLimitOffset(Integer limit, Integer offset) {

        List<Game> games = gameRepo.findAllGamesSortRank(limit, offset);

        JsonArrayBuilder arrBuilder = Json.createArrayBuilder();
        for (Game game : games) {
            arrBuilder.add(game.toJsonShort());
        }

        return Json.createObjectBuilder()
                .add("games", arrBuilder)
                .add("offset", offset)
                .add("limit", limit)
                .add("total", games.size())
                .add("timestamp", games.get(0).getTimestamp().toString())
                .build();
    }

    public Optional<JsonObject> findGameById(String id) {
        Optional<Game> game = gameRepo.findGameById(id);
        
        if(game.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(game.get().toJsonLong());
    }
}
