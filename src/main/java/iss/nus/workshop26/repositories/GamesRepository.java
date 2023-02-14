package iss.nus.workshop26.repositories;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import static iss.nus.workshop26.utils.MongoConstants.*;

import iss.nus.workshop26.models.Game;

@Repository
public class GamesRepository {
    
    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Game> findAllGames(Integer limit, Integer offset) {
        
        // db.games.find().skip(0).limit(25);

        Query query = Query.query(new Criteria()).skip(offset).limit(limit);
        query.fields()
            .include(FIELD_NAME);

        List<Document> docs = mongoTemplate.find(query, Document.class, COLLECTION_GAMES);
        LocalDateTime now = LocalDateTime.now();
        List<Game> games = docs.stream()
                            .map(x -> Game.fromMongoDocument(x))
                            .toList();

        games.forEach(x -> x.setTimestamp(now));
                            
        return games;
    }

    public List<Game> findAllGamesSortRank(Integer limit, Integer offset) {
        
        // db.games.find({}).sort({ranking: 1}).skip(0).limit(25);

        Query query = Query.query(new Criteria())
                        .skip(offset).limit(limit)
                        .with(Sort.by(Sort.Direction.ASC, FIELD_RANKING));

        query.fields()
            .include(FIELD_NAME);

        List<Document> docs = mongoTemplate.find(query, Document.class, COLLECTION_GAMES);
        LocalDateTime now = LocalDateTime.now();
        List<Game> games = docs.stream()
                            .map(x -> Game.fromMongoDocument(x))
                            .toList();

        games.forEach(x -> x.setTimestamp(now));

        return games;
    }

    public Optional<Game> findGameById(String id) {
        
        // db.games.find({_id: ObjectId("63ea65df0f0c43442f8adf2d")});
        
        try {
        Criteria criteria = Criteria.where(FIELD_ID).is(new ObjectId(id));
        Query query = Query.query(criteria);


        //todo: can I change Document.class to something else --> Nope
        Document document = mongoTemplate.findOne(query, Document.class, COLLECTION_GAMES);
        
        Game game = Game.fromMongoDocument(document);
        game.setTimestamp(LocalDateTime.now());

        return Optional.ofNullable(game);

    } catch (IllegalArgumentException e) {
            return Optional.empty();
        }
        
    }
}
