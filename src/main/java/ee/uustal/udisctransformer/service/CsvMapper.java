package ee.uustal.udisctransformer.service;

import ee.uustal.udisctransformer.pojo.udisc.ParseUDiscScoreData;
import ee.uustal.udisctransformer.pojo.udisc.PlayerHoleScore;
import ee.uustal.udisctransformer.pojo.udisc.UDiscPlayerData;
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

    public void mapCsvToObject(Path path) {
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
                    hintTypes[i] = PlayerHoleScore.class;
                }
            }

            beanReader.configureBeanMapping(UDiscPlayerData.class, fieldMapping, hintTypes);

            List<UDiscPlayerData> UDiscPlayerDataList = new ArrayList<>();
            UDiscPlayerData uDiscPlayerData;
            while ((uDiscPlayerData = beanReader.read(UDiscPlayerData.class, processors)) != null) {
                if (!"Par".equals(uDiscPlayerData.getPlayerName())) {
                    UDiscPlayerDataList.add(uDiscPlayerData);
                }
            }

            System.out.println(UDiscPlayerDataList);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
