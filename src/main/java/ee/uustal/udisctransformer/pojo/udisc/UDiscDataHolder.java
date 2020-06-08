package ee.uustal.udisctransformer.pojo.udisc;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;

import java.util.Date;

public class UDiscDataHolder {

    @CsvBindByName
    private String playerName;
    @CsvBindByName
    private String courseName;
    @CsvBindByName
    private String layoutName;
    @CsvDate(value = "yyyy-MM-dd HH:mm")
    @CsvBindByName
    private Date date;
    private UDiscScoreData uDiscScoreData;

    public String getPlayerName() {
        return playerName;
    }

    public UDiscDataHolder setPlayerName(String playerName) {
        this.playerName = playerName;
        return this;
    }

    public String getCourseName() {
        return courseName;
    }

    public UDiscDataHolder setCourseName(String courseName) {
        this.courseName = courseName;
        return this;
    }

    public String getLayoutName() {
        return layoutName;
    }

    public UDiscDataHolder setLayoutName(String layoutName) {
        this.layoutName = layoutName;
        return this;
    }

    public Date getDate() {
        return date;
    }

    public UDiscDataHolder setDate(Date date) {
        this.date = date;
        return this;
    }

    public UDiscScoreData getuDiscScoreData() {
        return uDiscScoreData;
    }

    public UDiscDataHolder setuDiscScoreData(UDiscScoreData uDiscScoreData) {
        this.uDiscScoreData = uDiscScoreData;
        return this;
    }
}
