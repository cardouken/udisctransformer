package ee.uustal.udisctransformer.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AddPlayersRequest {

    @JsonProperty("SelectedUserId")
    private String selectedUserId;
    @JsonProperty("player_name")
    private String playerName;
    private String addPlayer;

    public String getSelectedUserId() {
        return selectedUserId;
    }

    public AddPlayersRequest setSelectedUserId(String selectedUserId) {
        this.selectedUserId = selectedUserId;
        return this;
    }

    public String getPlayerName() {
        return playerName;
    }

    public AddPlayersRequest setPlayerName(String playerName) {
        this.playerName = playerName;
        return this;
    }

    public String getAddPlayer() {
        return addPlayer;
    }

    public AddPlayersRequest setAddPlayer(String addPlayer) {
        this.addPlayer = addPlayer;
        return this;
    }
}
