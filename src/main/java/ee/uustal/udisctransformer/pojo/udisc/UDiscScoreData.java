package ee.uustal.udisctransformer.pojo.udisc;

import com.opencsv.bean.CsvBindByName;

public class UDiscScoreData {

    @CsvBindByName(column = "total")
    private int totalThrowsAmount;
    @CsvBindByName(column = "+/-")
    private int throwsVsPar;
    private int[] hole;

    public int getTotalThrowsAmount() {
        return totalThrowsAmount;
    }

    public UDiscScoreData setTotalThrowsAmount(int totalThrowsAmount) {
        this.totalThrowsAmount = totalThrowsAmount;
        return this;
    }

    public int getThrowsVsPar() {
        return throwsVsPar;
    }

    public UDiscScoreData setThrowsVsPar(int throwsVsPar) {
        this.throwsVsPar = throwsVsPar;
        return this;
    }

    public int[] getHole() {
        return hole;
    }

    public UDiscScoreData setHole(int[] hole) {
        this.hole = hole;
        return this;
    }
}
