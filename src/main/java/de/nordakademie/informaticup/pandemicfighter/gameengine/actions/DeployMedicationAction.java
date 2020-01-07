package de.nordakademie.informaticup.pandemicfighter.gameengine.actions;

import com.google.gson.JsonObject;
import de.nordakademie.informaticup.pandemicfighter.gameengine.elements.City;
import de.nordakademie.informaticup.pandemicfighter.gameengine.elements.Pathogen;
import de.nordakademie.informaticup.pandemicfighter.gameengine.provider.JsonActionProvider;

public class DeployMedicationAction extends Action {
    private Pathogen pathogen;
    private City city;

    public DeployMedicationAction(Pathogen pathogen, City city) {
        this.pathogen = pathogen;
        this.city = city;
        setPoints();
    }

    @Override
    public JsonObject toJson() {
        return JsonActionProvider.deployMedication(pathogen, city);
    }

    @Override
    protected double calculateThreatIndicator() {
        return 1;
    }

    @Override
    protected int calculatePoints() {
        return 10;
    }
}
