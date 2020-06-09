package ee.uustal.udisctransformer.controller;

import ee.uustal.udisctransformer.api.request.GetPlayerRequest;
import ee.uustal.udisctransformer.pojo.udisc.UDiscMatchData;
import ee.uustal.udisctransformer.service.FileService;
import ee.uustal.udisctransformer.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.List;

@RestController
public class MatchController {

    private final MatchService matchService;
    private final FileService fileService;

    @Autowired
    public MatchController(MatchService matchService, FileService fileService) {
        this.matchService = matchService;
        this.fileService = fileService;
    }

    @PostMapping("/upload")
    public List<UDiscMatchData> uploadFile(@RequestParam("file") MultipartFile file) {
        Path path = fileService.uploadFile(file);
        return matchService.importFromCsv(path);
    }

    @GetMapping("/getplayer")
    public List<UDiscMatchData> getPlayer(GetPlayerRequest request) {
        return matchService.getPlayerMatches(request.getPlayerName());
    }
}
