package de.nordakademie.informaticup.pandemicfighter.gameengine.actions;

import com.google.gson.JsonObject;
import de.nordakademie.informaticup.pandemicfighter.gameengine.ThreatEvaluator;
import de.nordakademie.informaticup.pandemicfighter.gameengine.elements.City;
import de.nordakademie.informaticup.pandemicfighter.gameengine.provider.JsonActionProvider;

public class CallElectionsAction extends Action {
    private City city;

    public CallElectionsAction(City city) {
        this.city = city;
        setCharacteristics();
    }

    @Override
    public JsonObject toJson() {
        return JsonActionProvider.callElections(city);
    }

    @Override
    protected double calculateThreatIndicator() {
        return new ThreatEvaluator().calculateCallElection(city);
    }

    @Override
    protected int calculatePoints() {
        return 3;
    }
}
