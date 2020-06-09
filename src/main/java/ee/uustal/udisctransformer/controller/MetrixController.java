package ee.uustal.udisctransformer.controller;

import ee.uustal.udisctransformer.api.request.AddPlayerRequest;
import ee.uustal.udisctransformer.api.request.CreateMatchRequest;
import ee.uustal.udisctransformer.service.http.DiscGolfMetrixHttpClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MetrixController {

    private final DiscGolfMetrixHttpClient discGolfMetrixHttpClient;

    public MetrixController(DiscGolfMetrixHttpClient discGolfMetrixHttpClient) {
        this.discGolfMetrixHttpClient = discGolfMetrixHttpClient;
    }

    @PostMapping("/new-match")
    public void createTraining(CreateMatchRequest request) {
        discGolfMetrixHttpClient.createTraining(request);
    }

    @PostMapping("/add-player")
    public void addPlayer(AddPlayerRequest request) {
        discGolfMetrixHttpClient.addPlayer(request);
    }

}
