package ee.uustal.udisctransformer.database;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;

public class MongoService {

    protected final MongoDatabase database;

    public MongoService(MongoClient mongoClient, String databaseName) {
        this.database = mongoClient.getDatabase(databaseName);
    }

    public MongoDatabase getDatabase() {
        return database;
    }
}
