package de.nordakademie.informaticup.pandemicfighter.gameengine;

import com.google.gson.JsonObject;
import de.nordakademie.informaticup.pandemicfighter.gameengine.provider.ActionProvider;
import de.nordakademie.informaticup.pandemicfighter.gameengine.provider.cabinets.MedicationCabinet;
import de.nordakademie.informaticup.pandemicfighter.gameengine.elements.Pathogen;
import de.nordakademie.informaticup.pandemicfighter.gameengine.elements.events.PathogenEncounteredEvent;

public class ActionSelector {
    private Game game;

    public ActionSelector(Game game) {
        this.game = game;
    }

    public JsonObject getAction() {
        JsonObject action = ActionProvider.endRound();
        boolean noOtherAction = true; // TODO: tbd
        if (!"pending".equals(game.getOutcome()) || noOtherAction) {
            PathogenEncounteredEvent pathogenEvent = (PathogenEncounteredEvent) game.getEvents().get(0);
            Pathogen pathogen = pathogenEvent.getPathogen();
            int roundsUntilMedicationIsAvailable = MedicationCabinet.roundsUntilMedicationIsAvailable(pathogen.getName());
            if (roundsUntilMedicationIsAvailable == -1) {
                action = ActionProvider.developMedication(pathogen);
            } else {
                action = ActionProvider.endRound();
            }
            System.out.println("Medication status: " + roundsUntilMedicationIsAvailable);
        }
        return action;
    }
}
