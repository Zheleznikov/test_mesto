package ru.zheleznikov.mesto.main.app;


import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.ObjectId;
import ru.zheleznikov.mesto.main.model.Card;
import ru.zheleznikov.mesto.main.model.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MongoHelper extends  HelperBase {

    private final String MONGO_HOST = properties.getProperty("host.mongo");

    public MongoHelper() throws IOException {
    }

    private MongoClient connectMongo() {
        return MongoClients.create(MONGO_HOST);
    }

    public List<Card> getCards() throws IOException {
        MongoClient mongoClient = connectMongo();

        MongoDatabase mestodb = mongoClient.getDatabase("mestodb");
        MongoCollection<Document> cards = mestodb.getCollection("cards");
        MongoCollection<Document> users = mestodb.getCollection("users");

        List<Card> cardsToSee = new ArrayList<>();

        for (Document card : cards.find()) {

            Object userId = card.get("owner");
            BasicDBObject query = new BasicDBObject("_id", new ObjectId(userId.toString()));
            Document owner = users.find(query).first();


            User user = new User()
                    .set_id(owner.get("_id").toString())
                    .setName(owner.get("name").toString())
                    .setAbout(owner.get("about").toString())
                    .setEmail(owner.get("email").toString())
                    .setAvatar(owner.get("avatar").toString());

            cardsToSee.add(
                    new Card()
                            .set_id(card.get("_id").toString())
                            .setOwner(user)
                            .setLikes((List<String>) card.get("likes"))
                            .setName(card.get("name").toString())
                            .setLink(card.get("link").toString())

            );
        }
        mongoClient.close();
        return cardsToSee;
    }

}
