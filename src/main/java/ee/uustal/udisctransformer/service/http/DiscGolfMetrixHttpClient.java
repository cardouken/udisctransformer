package ee.uustal.udisctransformer.service.http;

import ee.uustal.udisctransformer.api.request.AddPlayerRequest;
import ee.uustal.udisctransformer.api.request.CreateMatchRequest;
import ee.uustal.udisctransformer.configuration.properties.DiscGolfMetrixProperties;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

@Component
public class DiscGolfMetrixHttpClient {

    private final DiscGolfMetrixProperties discGolfMetrixProperties;
    private final RestTemplate discGolfMetrixRestTemplate;
    private final HttpHeaders headers = new HttpHeaders();

    @Autowired
    public DiscGolfMetrixHttpClient(DiscGolfMetrixProperties discGolfMetrixProperties, RestTemplate discGolfMetrixRestTemplate) {
        this.discGolfMetrixProperties = discGolfMetrixProperties;
        this.discGolfMetrixRestTemplate = discGolfMetrixRestTemplate;
        headers.setBasicAuth(discGolfMetrixProperties.getUsername(), discGolfMetrixProperties.getPassword());
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.add("Cookie", "PHPSESSID=k4sl05sq4vk5d2ll6eodig5k33");
    }

    public void createTraining(CreateMatchRequest request) {
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
        discGolfMetrixRestTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);
    }

    public void addPlayer(AddPlayerRequest request) {
        URIBuilder uriBuilder;
        URI url = null;

        try {
            uriBuilder = new URIBuilder(discGolfMetrixProperties.getUri() + "?u=competition_add_player2&back=competition_start_players&selected_group=0&");
            uriBuilder.addParameter("ID", request.getId());
            url = uriBuilder.build();

        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("player_name", request.getPlayerName());
        body.add("addPlayer", request.getAddPlayer());

        HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<>(body, headers);
        discGolfMetrixRestTemplate.exchange(url, HttpMethod.POST, httpEntity, String.class);
    }
}
