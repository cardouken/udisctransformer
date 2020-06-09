package ee.uustal.udisctransformer.service;

import ee.uustal.udisctransformer.dao.UDiscMatchDao;
import ee.uustal.udisctransformer.pojo.udisc.ParseUDiscScoreData;
import ee.uustal.udisctransformer.pojo.udisc.PlayerScores;
import ee.uustal.udisctransformer.pojo.udisc.UDiscMatchData;
import org.springframework.stereotype.Service;
import org.supercsv.cellprocessor.Optional;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.dozer.CsvDozerBeanReader;
import org.supercsv.io.dozer.ICsvDozerBeanReader;
import org.supercsv.prefs.CsvPreference;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Service
public class CsvMapper {

    private static final int HOLE_START_INDEX = 6;
    private static final int DATA_END_INDEX = 4;

    public List<UDiscMatchData> mapCsvToObject(Path path) {
        List<UDiscMatchData> uDiscMatchDataList = new ArrayList<>();
        try (ICsvDozerBeanReader beanReader = new CsvDozerBeanReader(new FileReader(path.toFile()), CsvPreference.STANDARD_PREFERENCE)) {

            final String[] header = beanReader.getHeader(true);
            final String[] fieldMapping = new String[header.length];
            final CellProcessor[] processors = new CellProcessor[header.length];
            final Class<?>[] hintTypes = new Class<?>[header.length];

            for (int i = 0; i < header.length; i++) {
                if (i < DATA_END_INDEX) {
                    fieldMapping[i] = header[i];
                }
                if (i >= HOLE_START_INDEX) {
                    fieldMapping[i] = String.format("playerHoleScores[%d]", i - HOLE_START_INDEX);
                    processors[i] = new Optional(new ParseUDiscScoreData(header));
                    hintTypes[i] = PlayerScores.class;
                }
            }

            beanReader.configureBeanMapping(UDiscMatchData.class, fieldMapping, hintTypes);


            UDiscMatchData uDiscMatchData;
            while ((uDiscMatchData = beanReader.read(UDiscMatchData.class, processors)) != null) {
                if (!"Par".equals(uDiscMatchData.getPlayerName())) {
                    uDiscMatchDataList.add(uDiscMatchData);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return uDiscMatchDataList;
    }
}
