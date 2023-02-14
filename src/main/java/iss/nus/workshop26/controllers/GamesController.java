package iss.nus.workshop26.controllers;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import iss.nus.workshop26.services.GamesService;
import jakarta.json.Json;
import jakarta.json.JsonObject;

@RestController
@RequestMapping("/games")
public class GamesController {
    
    @Autowired
    private GamesService gamesService;

    @GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> findGamesLimitOffset(
                @RequestParam(defaultValue="25") Integer limit,
                @RequestParam(defaultValue="0") Integer offset) {

        JsonObject payload = gamesService.findGamesLimitOffset(limit, offset);
        
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(payload.toString());
    }

    @GetMapping(path="/rank", produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> findGamesSortRank(
                        @RequestParam(defaultValue="25") Integer limit,
                        @RequestParam(defaultValue="0") Integer offset) {

        
        JsonObject payload = gamesService.findGamesSortRankLimitOffset(limit, offset);

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(payload.toString());
    }

    @GetMapping(path="/{game_id}", produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> findGameById(@PathVariable(name="game_id") String gameId) {

        Optional<JsonObject> payload = gamesService.findGameById(gameId);

        if (payload.isEmpty()) {
            return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .contentType(MediaType.APPLICATION_JSON)
            .body(Json.createObjectBuilder()
                    .add("status", 404)
                    .add("message", "Game with id %s not found".formatted(gameId))
                    .add("timestamp", LocalDateTime.now().toString())
                    .build().toString()
                    );
        }

        return ResponseEntity
        .status(HttpStatus.OK)
        .contentType(MediaType.APPLICATION_JSON)
        .body(payload.toString());
    }
}
