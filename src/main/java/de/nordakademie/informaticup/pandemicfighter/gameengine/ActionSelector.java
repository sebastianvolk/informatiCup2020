package de.nordakademie.informaticup.pandemicfighter.gameengine;

import com.google.gson.JsonObject;

public class ActionSelector {
    private Game game;

    public ActionSelector(Game game) {
        this.game = game;
    }

    public JsonObject getAction() {
        JsonObject action = new JsonObject();
        boolean noOtherAction = true; // TODO: tbd
        if (!"pending".equals(game.getOutcome()) || noOtherAction) {
            action.addProperty("type", "endRound");
        }
        return action;
    }
}
