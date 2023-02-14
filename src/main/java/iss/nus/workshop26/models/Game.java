package iss.nus.workshop26.models;

import java.time.LocalDateTime;

import org.bson.Document;

import jakarta.json.Json;
import jakarta.json.JsonObject;

import static iss.nus.workshop26.utils.MongoConstants.*;

public class Game {
    private String id;
    private Integer gid;
    private String name;
    private Integer year;
    private Integer ranking;
    private Integer userRated;
    private String url;
    private String image;
    private LocalDateTime timestamp;

    public static Game fromMongoDocument(Document doc) {
        Game game = new Game();
        game.setId(doc.getObjectId(FIELD_ID).toString());
        game.setGid(doc.getInteger(FIELD_GID));
        // game.setGid((Integer) doc.getOrDefault(FIELD_GID, null)); // TODO will this work?
        game.setName(doc.getString(FIELD_NAME));
        game.setYear(doc.getInteger(FIELD_YEAR));
        game.setRanking(doc.getInteger(FIELD_RANKING));
        game.setUserRated(doc.getInteger(FIELD_USER_RATED));
        game.setUrl(doc.getString(FIELD_URL));
        game.setImage(doc.getString(FIELD_IMAGE));
        return game;
    }

    public JsonObject toJsonShort() {
        return Json.createObjectBuilder()
                .add("game_id", this.getId())
                .add("name", this.getName())
                .build();
    }

    public JsonObject toJsonLong() {
        return Json.createObjectBuilder()
                .add("game_id", this.getId())
                .add("name", this.getName())
                .add("year", this.getYear())
                .add("ranking", this.getRanking())
                .add("users_rated", this.getUserRated())
                .add("url", this.getUrl())
                .add("thumbnnail", this.getImage())
                .add("timestamp", this.getTimestamp().toString())
                .build();
    }

    @Override
    public String toString() {
        return "Game [id=" + id + ", gid=" + gid + ", name=" + name + ", year=" + year + ", ranking=" + ranking
                + ", userRated=" + userRated + ", url=" + url + ", image=" + image + ", timestamp=" + timestamp + "]";
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public Integer getGid() {
        return gid;
    }
    public void setGid(Integer gid) {
        this.gid = gid;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getYear() {
        return year;
    }
    public void setYear(Integer year) {
        this.year = year;
    }
    public Integer getRanking() {
        return ranking;
    }
    public void setRanking(Integer ranking) {
        this.ranking = ranking;
    }
    public Integer getUserRated() {
        return userRated;
    }
    public void setUserRated(Integer userRated) {
        this.userRated = userRated;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
