package de.nordakademie.informaticup.pandemicfighter.gameengine.actions;

import com.google.gson.JsonObject;
import de.nordakademie.informaticup.pandemicfighter.gameengine.ThreatEvaluator;
import de.nordakademie.informaticup.pandemicfighter.gameengine.elements.Pathogen;
import de.nordakademie.informaticup.pandemicfighter.gameengine.provider.JsonActionProvider;

public class DevelopMedicationAction extends Action {
    private Pathogen pathogen;

    public DevelopMedicationAction(Pathogen pathogen) {
        this.pathogen = pathogen;
        setCharacteristics();
    }

    @Override
    public JsonObject toJson() {
        return JsonActionProvider.developMedication(pathogen);
    }

    @Override
    protected double calculateThreatIndicator() {
        return new ThreatEvaluator().calculateForDevelopMedication(pathogen);
    }

    @Override
    protected int calculatePoints() {
        return 20;
    }
}
