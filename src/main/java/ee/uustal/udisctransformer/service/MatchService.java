package ee.uustal.udisctransformer.service;

import ee.uustal.udisctransformer.dao.UDiscMatchDao;
import ee.uustal.udisctransformer.pojo.udisc.UDiscMatchData;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.util.List;

@Service
public class MatchService {

    private final CsvMapper csvMapper;
    private final UDiscMatchDao uDiscMatchDao;

    public MatchService(CsvMapper csvMapper,
                        UDiscMatchDao uDiscMatchDao) {
        this.csvMapper = csvMapper;
        this.uDiscMatchDao = uDiscMatchDao;
    }

    public List<UDiscMatchData> importFromCsv(Path path) {
        List<UDiscMatchData> uDiscMatchDataList = csvMapper.mapCsvToObject(path);
        uDiscMatchDao.updateMany(uDiscMatchDataList);
        return uDiscMatchDataList;
    }

    public List<UDiscMatchData> getPlayerMatches(String playerName) {
        return uDiscMatchDao.getByPlayerName(playerName);
    }
}
