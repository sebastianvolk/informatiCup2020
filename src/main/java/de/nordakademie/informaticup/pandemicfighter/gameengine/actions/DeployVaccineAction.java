package de.nordakademie.informaticup.pandemicfighter.gameengine.actions;

import com.google.gson.JsonObject;
import de.nordakademie.informaticup.pandemicfighter.gameengine.elements.City;
import de.nordakademie.informaticup.pandemicfighter.gameengine.elements.Pathogen;
import de.nordakademie.informaticup.pandemicfighter.gameengine.provider.JsonActionProvider;

public class DeployVaccineAction extends Action {
    private Pathogen pathogen;
    private City city;

    public DeployVaccineAction(Pathogen pathogen, City city) {
        this.pathogen = pathogen;
        this.city = city;
        setCharacteristics();
    }

    @Override
    public JsonObject toJson() {
        return JsonActionProvider.deployVaccine(pathogen, city);
    }

    @Override
    protected double calculateThreatIndicator() {
        return 1;
    }

    @Override
    protected int calculatePoints() {
        return 5;
    }
}
