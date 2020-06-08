package ee.uustal.udisctransformer.pojo.udisc;

public class PlayerHoleScore {

    private UDiscPlayerData UDiscPlayerData;
    private String holeNameNumber;
    private String holeScore;

    public UDiscPlayerData getUDiscPlayerData() {
        return UDiscPlayerData;
    }

    public PlayerHoleScore setUDiscPlayerData(UDiscPlayerData UDiscPlayerData) {
        this.UDiscPlayerData = UDiscPlayerData;
        return this;
    }

    public String getHoleNameNumber() {
        return holeNameNumber;
    }

    public PlayerHoleScore setHoleNameNumber(String holeNameNumber) {
        this.holeNameNumber = holeNameNumber;
        return this;
    }

    public String getHoleScore() {
        return holeScore;
    }

    public PlayerHoleScore setHoleScore(String holeScore) {
        this.holeScore = holeScore;
        return this;
    }

}
