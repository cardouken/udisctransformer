package ee.uustal.udisctransformer.pojo.udisc;

import org.bson.types.ObjectId;

import java.util.Date;
import java.util.List;

public class UDiscMatchData {

    private ObjectId matchId;
    private String playerName;
    private String courseName;
    private String layoutName;
    private Date date;
    private List<PlayerScores> playerHoleScores;

    public ObjectId getMatchId() {
        return matchId;
    }

    public UDiscMatchData setMatchId(ObjectId matchId) {
        this.matchId = matchId;
        return this;
    }

    public String getPlayerName() {
        return playerName;
    }

    public UDiscMatchData setPlayerName(String playerName) {
        this.playerName = playerName;
        return this;
    }

    public String getCourseName() {
        return courseName;
    }

    public UDiscMatchData setCourseName(String courseName) {
        this.courseName = courseName;
        return this;
    }

    public String getLayoutName() {
        return layoutName;
    }

    public UDiscMatchData setLayoutName(String layoutName) {
        this.layoutName = layoutName;
        return this;
    }

    public Date getDate() {
        return date;
    }

    public UDiscMatchData setDate(Date date) {
        this.date = date;
        return this;
    }

    public List<PlayerScores> getPlayerHoleScores() {
        return playerHoleScores;
    }

    public UDiscMatchData setPlayerHoleScores(List<PlayerScores> playerHoleScores) {
        this.playerHoleScores = playerHoleScores;
        return this;
    }

}
