## Setup

* Import  `game.json` into `boardgames` database as `games` collection

```
mongoimport "mongodb://localhost:27017" -d boardgames -c games --jsonArray --file json/game.json --drop
```

* Import  `comment.json` into `boardgames` database as `comments` collection

```
mongoimport "mongodb://localhost:27017" -d boardgames -c comments --jsonArray --file json/comment.json --drop
```

## Mongo Query Used

Skip + Limit (Pagination):
```
db.games.find().skip(0).limit(25);
```

Sort + Skip + Limit:
```
db.games.find({}).sort({ranking: 1}).skip(0).limit(25);
```

Find by ObjectId:
```
db.games.find({_id: ObjectId("63ea65df0f0c43442f8adf2d")});
```
