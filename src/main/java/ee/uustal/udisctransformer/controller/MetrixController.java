package ee.uustal.udisctransformer.controller;

import ee.uustal.udisctransformer.api.request.AddPlayersRequest;
import ee.uustal.udisctransformer.api.request.CreateMatchRequest;
import ee.uustal.udisctransformer.service.http.DiscGolfMetrixHttpClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public void addPlayer(@RequestParam  String id, AddPlayersRequest request) {
        discGolfMetrixHttpClient.addPlayers(id, request);
    }

}
