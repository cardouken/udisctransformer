package ee.uustal.udisctransformer.pojo;

public class PlayerData {

    private String playerName;
    private int playerId;

    public String getPlayerName() {
        return playerName;
    }

    public PlayerData setPlayerName(String playerName) {
        this.playerName = playerName;
        return this;
    }

    public int getPlayerId() {
        return playerId;
    }

    public PlayerData setPlayerId(int playerId) {
        this.playerId = playerId;
        return this;
    }
}
