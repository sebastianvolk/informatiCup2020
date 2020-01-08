package de.nordakademie.informaticup.pandemicfighter.gameengine.actions;

import com.google.gson.JsonObject;
import de.nordakademie.informaticup.pandemicfighter.gameengine.ThreatEvaluator;
import de.nordakademie.informaticup.pandemicfighter.gameengine.elements.Pathogen;
import de.nordakademie.informaticup.pandemicfighter.gameengine.provider.JsonActionProvider;

public class DevelopVaccineAction extends Action {
    private Pathogen pathogen;

    public DevelopVaccineAction(Pathogen pathogen) {
        this.pathogen = pathogen;
        setCharacteristics();
    }

    @Override
    public JsonObject toJson() {
        return JsonActionProvider.developVaccine(pathogen);
    }

    @Override
    protected double calculateThreatIndicator() {
        return new ThreatEvaluator().calculateDevelopVaccine(pathogen);
    }

    @Override
    protected int calculatePoints() {
        return 40;
    }
}
