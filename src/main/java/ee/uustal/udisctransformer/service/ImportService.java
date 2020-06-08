package ee.uustal.udisctransformer.service;

import org.springframework.stereotype.Service;

import java.nio.file.Path;

@Service
public class ImportService {

    private final CsvMapper csvMapper;

    public ImportService(CsvMapper csvMapper) {
        this.csvMapper = csvMapper;
    }

    public void importFromCsv(Path path) {
        csvMapper.mapCsvToObject(path);
    }
}
