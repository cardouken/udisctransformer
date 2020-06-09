package ee.uustal.udisctransformer.service.http;

import ee.uustal.udisctransformer.api.request.AddPlayersRequest;
import ee.uustal.udisctransformer.api.request.CreateMatchRequest;
import ee.uustal.udisctransformer.configuration.properties.DiscGolfMetrixProperties;
import ee.uustal.udisctransformer.util.JsonUtility;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.text.MessageFormat;

@Component
public class DiscGolfMetrixHttpClient {

    private final DiscGolfMetrixProperties discGolfMetrixProperties;
    private final RestTemplate discGolfMetrixRestTemplate;

    @Autowired
    public DiscGolfMetrixHttpClient(DiscGolfMetrixProperties discGolfMetrixProperties, RestTemplate discGolfMetrixRestTemplate) {
        this.discGolfMetrixProperties = discGolfMetrixProperties;
        this.discGolfMetrixRestTemplate = discGolfMetrixRestTemplate;
    }

    public void createTraining(CreateMatchRequest request) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(discGolfMetrixProperties.getUsername(), discGolfMetrixProperties.getPassword());

        URIBuilder uriBuilder;
        URI url = null;

        try {
            uriBuilder = new URIBuilder(discGolfMetrixProperties.getUri() + "?u=competition_add&create_training=1&");
            uriBuilder.addParameter("competitiontype", String.valueOf(request.getCompetitionType()));
            uriBuilder.addParameter("courseid", String.valueOf(request.getCourseId()));

            url = uriBuilder.build();

        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        HttpEntity<String> httpEntity = new HttpEntity<>(headers);
        ResponseEntity<String> exchange = discGolfMetrixRestTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);

        if (!exchange.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException(
                    MessageFormat.format(
                            "Unsuccessful response: {0} received from Metrix at endpoint {1} with response: {2}",
                            exchange.getStatusCodeValue(),
                            url,
                            exchange.getBody()
                    )
            );
        }
    }

    public void addPlayers(AddPlayersRequest request) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(discGolfMetrixProperties.getUsername(), discGolfMetrixProperties.getPassword());

        URIBuilder uriBuilder;
        URI url = null;

        try {
            uriBuilder = new URIBuilder(discGolfMetrixProperties.getUri() + "?u=competition_add_player2&back=competition_start_players&selected_group=0&");
            uriBuilder.addParameter("ID", "1305813");

            url = uriBuilder.build();

        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        HttpEntity<String> httpEntity = new HttpEntity<>(JsonUtility.toJson(request), headers);
        ResponseEntity<String> exchange = discGolfMetrixRestTemplate.exchange(url, HttpMethod.POST, httpEntity, String.class);

        if (!exchange.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException(
                    MessageFormat.format(
                            "Unsuccessful response: {0} received from Metrix at endpoint {1} with response: {2}",
                            exchange.getStatusCodeValue(),
                            url,
                            exchange.getBody()
                    )
            );
        }
    }
}
