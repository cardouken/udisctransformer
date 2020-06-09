package ee.uustal.udisctransformer.pojo.udisc;

import org.supercsv.cellprocessor.CellProcessorAdaptor;
import org.supercsv.util.CsvContext;

public class ParseUDiscScoreData extends CellProcessorAdaptor {

    private final String[] header;

    public ParseUDiscScoreData(final String[] header) {
        this.header = header;
    }

    public Object execute(Object value, CsvContext context) {

        if (value == null) {
            return null;
        }
        return new PlayerScores()
                .setHoleNameNumber(header[context.getColumnNumber() - 1])
                .setHoleScore(value.toString());

    }
}