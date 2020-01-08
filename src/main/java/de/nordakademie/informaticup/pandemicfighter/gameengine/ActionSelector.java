package de.nordakademie.informaticup.pandemicfighter.gameengine;

import com.google.gson.JsonObject;
import de.nordakademie.informaticup.pandemicfighter.gameengine.actions.Action;
import de.nordakademie.informaticup.pandemicfighter.gameengine.actions.EndRoundAction;

import java.util.ArrayList;

public class ActionSelector {
    private Game game;

    public ActionSelector(Game game) {
        this.game = game;
    }

    public JsonObject getAction() {
        JsonObject action = new EndRoundAction().toJson();
        if ("pending".equals(game.getOutcome())) {
            ArrayList<Action> actions = new ActionCreator().getAllPossibleActions(game);
            action = new DecisionMaker().getBestAction(actions).toJson();
        }
        return action;
    }
}
