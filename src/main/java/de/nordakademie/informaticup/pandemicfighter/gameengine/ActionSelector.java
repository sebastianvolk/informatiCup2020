package de.nordakademie.informaticup.pandemicfighter.gameengine;

import com.google.gson.JsonObject;
import de.nordakademie.informaticup.pandemicfighter.gameengine.actions.Action;
import de.nordakademie.informaticup.pandemicfighter.gameengine.provider.JsonActionProvider;
import de.nordakademie.informaticup.pandemicfighter.gameengine.provider.cabinets.MedicationCabinet;
import de.nordakademie.informaticup.pandemicfighter.gameengine.elements.Pathogen;
import de.nordakademie.informaticup.pandemicfighter.gameengine.elements.events.PathogenEncounteredEvent;
import de.nordakademie.informaticup.pandemicfighter.gameengine.provider.cabinets.VaccineCabinet;

import java.util.ArrayList;

public class ActionSelector {
    private Game game;

    public ActionSelector(Game game) {
        this.game = game;
    }

    public JsonObject getAction() {
        JsonObject action = JsonActionProvider.endRound();
        boolean noOtherAction = true; // TODO: tbd
        if (!"pending".equals(game.getOutcome()) || noOtherAction) {
            PathogenEncounteredEvent pathogenEvent = (PathogenEncounteredEvent) game.getEvents().get(0);
            Pathogen pathogen = pathogenEvent.getPathogen();
            int roundsUntilVaccineIsAvailable = VaccineCabinet.roundsUntilVaccineIsAvailable(pathogen.getName());
            if (roundsUntilVaccineIsAvailable == -1) {
                action = ActionProvider.developVaccine(pathogen);
            } else {
                action = JsonActionProvider.endRound();
            }
            ArrayList<Action> actions = new ActionCreator().getAllPossibleActions(game);
            System.out.println("Vaccine status: " + roundsUntilVaccineIsAvailable);
        }
        return action;
    }
}
