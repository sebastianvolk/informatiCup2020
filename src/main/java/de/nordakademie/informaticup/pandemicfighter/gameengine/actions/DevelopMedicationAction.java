package de.nordakademie.informaticup.pandemicfighter.gameengine.actions;

import com.google.gson.JsonObject;
import de.nordakademie.informaticup.pandemicfighter.gameengine.elements.Pathogen;
import de.nordakademie.informaticup.pandemicfighter.gameengine.provider.JsonActionProvider;

public class DevelopMedicationAction extends Action {
    private Pathogen pathogen;

    public DevelopMedicationAction(Pathogen pathogen) {
        this.pathogen = pathogen;
        setPoints();
    }

    @Override
    public JsonObject toJson() {
        return JsonActionProvider.developMedication(pathogen);
    }

    @Override
    protected double calculateThreatIndicator() {
        return 1;
    }

    @Override
    protected int calculatePoints() {
        return 20;
    }
}
