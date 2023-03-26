package ua.mani123.database;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import lombok.Getter;
import org.bson.Document;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import ua.mani123.database.DatabaseObjects.WMUser;

import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

@Getter
public class Database {
    private final MongoDatabase database;
    private final MongoClient mongoClient;

    public Database(String address, String port, String databaseName) {
        CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
        CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));

        this.mongoClient = MongoClients.create("mongodb://" + address + ":" + port);
        this.database = mongoClient.getDatabase(databaseName).withCodecRegistry(pojoCodecRegistry);
    }

    public void insertUser(WMUser user) {
        this.database.getCollection("Users", WMUser.class).insertOne(user);
    }

    public void updateUser(WMUser user) {
        this.database.getCollection("Users", WMUser.class).updateOne(new Document("_id", user.getId()), new Document("$set", user.toDocument()));
    }

    public WMUser getUserByDiscordId(Long discordId) {
        return this.database.getCollection("Users", WMUser.class).find(new Document("discordId", discordId)).first();
    }
}
