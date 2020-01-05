package de.nordakademie.informaticup.pandemicfighter.gameengine;

import com.google.gson.JsonObject;
import de.nordakademie.informaticup.pandemicfighter.gameengine.elements.Pathogen;
import de.nordakademie.informaticup.pandemicfighter.gameengine.elements.events.PathogenEncounteredEvent;

public class ActionSelector {
    private Game game;

    public ActionSelector(Game game) {
        this.game = game;
    }

    public JsonObject getAction() {
        JsonObject action;
        boolean noOtherAction = true; // TODO: tbd
        if (!"pending".equals(game.getOutcome()) || noOtherAction) {
            //action = ActionProvider.endRound();
            PathogenEncounteredEvent pathogenEvent = (PathogenEncounteredEvent) game.getEvents().get(0);
            Pathogen pathogen = pathogenEvent.getPathogen();
            action = ActionProvider.developMedication(pathogen);
        }
        else {
            action = ActionProvider.endRound();
        }
        return action;
    }
}
