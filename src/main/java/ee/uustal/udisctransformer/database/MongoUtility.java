package ee.uustal.udisctransformer.database;

import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class MongoUtility {

    public static <T> List<T> asList(MongoCursor<T> cursor) {
        List<T> result = new ArrayList<>();
        cursor.forEachRemaining(result::add);
        return result;
    }

    public static <T, Y> List<Y> asList(MongoCursor<T> cursor, Function<T, Y> transformer) {
        List<Y> result = new ArrayList<>();
        cursor.forEachRemaining(o -> result.add(transformer.apply(o)));
        return result;
    }

    public static boolean collectionExists(MongoDatabase mongoDatabase, final String collectionName) {
        for (final String name : mongoDatabase.listCollectionNames()) {
            if (name.equalsIgnoreCase(collectionName)) {
                return true;
            }
        }
        return false;
    }
}
