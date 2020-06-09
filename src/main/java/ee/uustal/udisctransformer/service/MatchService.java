package ee.uustal.udisctransformer.service;

import ee.uustal.udisctransformer.dao.UDiscMatchDao;
import ee.uustal.udisctransformer.pojo.udisc.UDiscMatchData;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.List;

@Service
public class MatchService {

    private final CsvMapper csvMapper;
    private final FileService fileService;
    private final UDiscMatchDao uDiscMatchDao;

    public MatchService(CsvMapper csvMapper,
                        FileService fileService,
                        UDiscMatchDao uDiscMatchDao) {
        this.csvMapper = csvMapper;
        this.fileService = fileService;
        this.uDiscMatchDao = uDiscMatchDao;
    }

    public List<UDiscMatchData> importFromCsv(MultipartFile file) {
        Path path = fileService.uploadFile(file);
        List<UDiscMatchData> uDiscMatchDataList = csvMapper.mapCsvToObject(path);
        uDiscMatchDao.insertMany(uDiscMatchDataList);

        return uDiscMatchDataList;
    }

    public List<UDiscMatchData> getPlayerMatches(String playerName) {
        return uDiscMatchDao.getByPlayerName(playerName);
    }

    public List<UDiscMatchData> getMatchByTimestamp(long timestamp) {
        return uDiscMatchDao.getByTimestamp(timestamp);
    }

}
