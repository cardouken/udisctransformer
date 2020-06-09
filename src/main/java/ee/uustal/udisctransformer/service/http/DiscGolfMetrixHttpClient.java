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
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
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

    public void addPlayers(String id, AddPlayersRequest request) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(discGolfMetrixProperties.getUsername(), discGolfMetrixProperties.getPassword());
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.add("Cookie", "PHPSESSID=11rg46jn2pvntn263kcl6bib00");
        URIBuilder uriBuilder;
        URI url = null;

        try {
            uriBuilder = new URIBuilder(discGolfMetrixProperties.getUri() + "?u=competition_add_player2&back=competition_start_players&selected_group=0&");
            uriBuilder.addParameter("ID", id);

            url = uriBuilder.build();

        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("player_name", request.getPlayerName());
        body.add("addPlayer", request.getAddPlayer());

        HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<>(body, headers);
        ResponseEntity<String> exchange = discGolfMetrixRestTemplate.postForEntity(url, httpEntity, String.class);

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
