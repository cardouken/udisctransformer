package ee.uustal.udisctransformer.configuration;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import ee.uustal.udisctransformer.database.MongoService;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

@Configuration
public class MongoConfiguration {

    @Bean
    @Primary
    public MongoProperties mongoProperties(@Value("${backend.database.connection-string}") String connectionString,
                                           @Value("${backend.database.username}") String username,
                                           @Value("${backend.database.password}") String password,
                                           @Value("${backend.database.name}") String databaseName) {
        String uri = String.format(connectionString, username, password);
        MongoProperties mongoProperties = new MongoProperties();
        mongoProperties.setUri(uri);
        mongoProperties.setDatabase(databaseName);
        return mongoProperties;
    }

    @Bean
    public MongoService mongoService(MongoClient mongoClient, MongoProperties mongoProperties) {
        return new MongoService(mongoClient, mongoProperties.getDatabase());
    }

    @Bean
    public MongoClient mongoClient(MongoProperties mongoProperties) {

        CodecRegistry globalPojoCodecs = fromProviders(
                PojoCodecProvider.builder()
                        .automatic(true)
                        .build()
        );
        CodecRegistry codecRegistry = fromRegistries(getDefaultCodecRegistry(), globalPojoCodecs);

        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(mongoProperties.getUri()))
                .codecRegistry(codecRegistry)
                .build();

        return MongoClients.create(settings);
    }

}
