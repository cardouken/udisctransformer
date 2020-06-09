package ee.uustal.udisctransformer.dao;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.ReplaceOptions;
import com.mongodb.client.model.UpdateOptions;
import ee.uustal.udisctransformer.database.MongoService;
import ee.uustal.udisctransformer.pojo.udisc.UDiscMatchData;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.set;
import static ee.uustal.udisctransformer.database.MongoUtility.asList;

@Repository
public class UDiscMatchDao {

    private final MongoCollection<UDiscMatchData> collection;

    @Autowired
    public UDiscMatchDao(MongoService mongoService) {
        MongoDatabase database = mongoService.getDatabase();
        this.collection = database.getCollection("UDiscMatchData", UDiscMatchData.class);
    }

    public void save(UDiscMatchData u) {
        collection.replaceOne(
                eq("_id", u.getId()),
                u,
                new ReplaceOptions().upsert(true)
        );
    }

    public void updateMany(List<UDiscMatchData> uDiscMatchDataList) {
        collection.insertMany(uDiscMatchDataList);
    }

    public UDiscMatchData get(ObjectId userId) {
        return collection.find(
                eq("_id", userId)
        ).first();
    }

    public List<UDiscMatchData> getByPlayerName(String playerName) {
        MongoCursor<UDiscMatchData> cursor = collection.find(
                eq("playerName", playerName)
        ).iterator();

        return asList(cursor);
    }

}
