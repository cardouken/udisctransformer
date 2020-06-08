package ee.uustal.udisctransformer.pojo.udisc;

import java.util.List;

public class UDiscPlayerData {

    private String playerName;
    private String courseName;
    private String layoutName;
    private String date;
    private List<PlayerHoleScore> playerHoleScores;

    public String getPlayerName() {
        return playerName;
    }

    public UDiscPlayerData setPlayerName(String playerName) {
        this.playerName = playerName;
        return this;
    }

    public String getCourseName() {
        return courseName;
    }

    public UDiscPlayerData setCourseName(String courseName) {
        this.courseName = courseName;
        return this;
    }

    public String getLayoutName() {
        return layoutName;
    }

    public UDiscPlayerData setLayoutName(String layoutName) {
        this.layoutName = layoutName;
        return this;
    }

    public String getDate() {
        return date;
    }

    public UDiscPlayerData setDate(String date) {
        this.date = date;
        return this;
    }

    public List<PlayerHoleScore> getPlayerHoleScores() {
        return playerHoleScores;
    }

    public UDiscPlayerData setPlayerHoleScores(List<PlayerHoleScore> playerHoleScores) {
        this.playerHoleScores = playerHoleScores;
        return this;
    }

}
