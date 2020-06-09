package ee.uustal.udisctransformer.pojo.udisc;

public class PlayerScores {

    private UDiscMatchData UDiscMatchData;
    private String holeNameNumber;
    private String holeScore;

    public UDiscMatchData getUDiscMatchData() {
        return UDiscMatchData;
    }

    public PlayerScores setUDiscMatchData(UDiscMatchData UDiscMatchData) {
        this.UDiscMatchData = UDiscMatchData;
        return this;
    }

    public String getHoleNameNumber() {
        return holeNameNumber;
    }

    public PlayerScores setHoleNameNumber(String holeNameNumber) {
        this.holeNameNumber = holeNameNumber;
        return this;
    }

    public String getHoleScore() {
        return holeScore;
    }

    public PlayerScores setHoleScore(String holeScore) {
        this.holeScore = holeScore;
        return this;
    }

}
