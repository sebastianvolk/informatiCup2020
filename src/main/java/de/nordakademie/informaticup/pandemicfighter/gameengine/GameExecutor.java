package de.nordakademie.informaticup.pandemicfighter.gameengine;

import com.google.gson.JsonObject;
import de.nordakademie.informaticup.pandemicfighter.gameengine.actions.Action;
import de.nordakademie.informaticup.pandemicfighter.gameengine.actions.EndRoundAction;
import de.nordakademie.informaticup.pandemicfighter.gameengine.elements.Game;
import de.nordakademie.informaticup.pandemicfighter.gameengine.provider.CityProvider;
import de.nordakademie.informaticup.pandemicfighter.gameengine.provider.cabinets.MedicationCabinet;
import de.nordakademie.informaticup.pandemicfighter.gameengine.provider.cabinets.VaccineCabinet;

import java.util.ArrayList;

public class GameExecutor {
    private Game game;

    public GameExecutor(Game game) {
        this.game = game;
    }

    public JsonObject getAction() {
        initializeProvider();
        Action action = new EndRoundAction();
        if ("pending".equals(game.getOutcome())) {
            ArrayList<Action> actions = new ActionCreator().getAllPossibleActions(game);
            action = new DecisionMaker(game.getPoints()).getBestAction(actions);
        }
        return action.toJson();
    }

    private void initializeProvider() {
        CityProvider.setCities(game.getCities());
        MedicationCabinet.initialize(game);
        VaccineCabinet.initialize(game);
    }
}
