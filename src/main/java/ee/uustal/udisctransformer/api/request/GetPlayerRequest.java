package ee.uustal.udisctransformer.api.request;

public class GetPlayerRequest {

    private String playerName;

    public String getPlayerName() {
        return playerName;
    }

    public GetPlayerRequest setPlayerName(String playerName) {
        this.playerName = playerName;
        return this;
    }
}
