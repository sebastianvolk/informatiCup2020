package de.nordakademie.informaticup.pandemicfighter.gameengine.actions;

import com.google.gson.JsonObject;
import de.nordakademie.informaticup.pandemicfighter.gameengine.elements.City;
import de.nordakademie.informaticup.pandemicfighter.gameengine.provider.JsonActionProvider;

public class CallElectionsAction extends Action {
    private City city;

    public CallElectionsAction(City city) {
        this.city = city;
        setPoints();
    }

    @Override
    public JsonObject toJson() {
        return JsonActionProvider.callElections(city);
    }

    @Override
    protected double calculateThreatIndicator() {
        return 1;
    }

    @Override
    protected int calculatePoints() {
        return 3;
    }
}
