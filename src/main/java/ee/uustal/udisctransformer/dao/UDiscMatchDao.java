package ee.uustal.udisctransformer.dao;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.ReplaceOptions;
import ee.uustal.udisctransformer.database.MongoService;
import ee.uustal.udisctransformer.pojo.udisc.UDiscMatchData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;
import static ee.uustal.udisctransformer.database.MongoUtility.asList;

@Repository
public class UDiscMatchDao {

    private final MongoCollection<UDiscMatchData> collection;

    @Autowired
    public UDiscMatchDao(MongoService mongoService) {
        MongoDatabase database = mongoService.getDatabase();
        this.collection = database.getCollection("UDiscMatchData", UDiscMatchData.class);
    }

    public void save(UDiscMatchData matchData) {
        collection.replaceOne(
                eq("_id", matchData.getMatchId()),
                matchData,
                new ReplaceOptions().upsert(true)
        );
    }

    public void insertMany(List<UDiscMatchData> uDiscMatchDataList) {
        collection.insertMany(uDiscMatchDataList);
    }

    public List<UDiscMatchData> getByPlayerName(String playerName) {
        MongoCursor<UDiscMatchData> cursor = collection.find(
                eq("playerName", playerName)
        ).iterator();

        return asList(cursor);
    }

    public List<UDiscMatchData> getByTimestamp(long timestamp) {
        MongoCursor<UDiscMatchData> cursor = collection.find(
                eq("date", new Date(timestamp))
        ).iterator();

        return asList(cursor);
    }

}
