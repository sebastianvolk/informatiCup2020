package de.nordakademie.informaticup.pandemicfighter.gameengine;

import com.google.gson.JsonObject;

public class GameExecutor {
    private Game game;
    private JsonObject action = new JsonObject();

    public GameExecutor(Game game) {
        this.game = game;
        playRound();
    }

    private void playRound(){
        setAction("endRound");
    }

    private void setAction(String actionType){
        this.action.addProperty("type", actionType);
    }

    public JsonObject getAction(){
        return action;
    }
}
