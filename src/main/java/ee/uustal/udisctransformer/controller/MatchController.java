package ee.uustal.udisctransformer.controller;

import ee.uustal.udisctransformer.api.request.GetPlayerRequest;
import ee.uustal.udisctransformer.pojo.udisc.UDiscMatchData;
import ee.uustal.udisctransformer.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
public class MatchController {

    private final MatchService matchService;

    @Autowired
    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    @PostMapping("/upload")
    public List<UDiscMatchData> uploadFile(@RequestParam("file") MultipartFile file) {
        return matchService.importFromCsv(file);
    }

    @GetMapping("/getplayer")
    public List<UDiscMatchData> getPlayer(GetPlayerRequest request) {
        return matchService.getPlayerMatches(request.getPlayerName());
    }

    @GetMapping("/getbydate")
    public List<UDiscMatchData> getMatchByDate(String date) {
        return matchService.getMatchByDate(date);
    }
}
