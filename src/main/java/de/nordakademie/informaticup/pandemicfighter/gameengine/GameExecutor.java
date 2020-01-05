package de.nordakademie.informaticup.pandemicfighter.gameengine;

import com.google.gson.JsonObject;

public class GameExecutor {
    private Game game;
    private JsonObject action = new JsonObject();

    public GameExecutor(Game game) {
        this.game = game;
    }

    public GameExecutor playRound(){
        CityProvider.setCities(game.getCities());
        ActionSelector actionSelector = new ActionSelector(game);
        action = actionSelector.getAction();
        return this;
    }

    public JsonObject getAction() {
        return action;
    }
}
