package ee.uustal.udisctransformer.service;

import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBeanBuilder;
import ee.uustal.udisctransformer.configuration.BeanNameVerifier;
import ee.uustal.udisctransformer.pojo.udisc.UDiscDataHolder;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Service
public class CsvMapper {

    private final BeanNameVerifier beanNameVerifier;

    public CsvMapper(BeanNameVerifier beanNameVerifier) {
        this.beanNameVerifier = beanNameVerifier;
    }

    public void mapCsvToObject(Path path) {
        List<UDiscDataHolder> uDiscDataHolderList = new ArrayList<>();
        try {
            uDiscDataHolderList = new CsvToBeanBuilder<UDiscDataHolder>(new FileReader(path.toFile()))
                    .withType(UDiscDataHolder.class)
                    .withVerifier(beanNameVerifier)
                    .build()
                    .parse();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println(uDiscDataHolderList);
    }
}