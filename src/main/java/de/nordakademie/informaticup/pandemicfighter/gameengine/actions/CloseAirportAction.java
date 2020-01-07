package de.nordakademie.informaticup.pandemicfighter.gameengine.actions;

import com.google.gson.JsonObject;
import de.nordakademie.informaticup.pandemicfighter.gameengine.elements.City;
import de.nordakademie.informaticup.pandemicfighter.gameengine.provider.JsonActionProvider;

public class CloseAirportAction extends Action {
    private City city;
    private int rounds;

    public CloseAirportAction(City city, int rounds) {
        this.city = city;
        this.rounds = rounds;
        setPoints();
    }

    @Override
    public JsonObject toJson() {
        return JsonActionProvider.closeAirport(city, rounds);
    }

    @Override
    protected double calculateThreatIndicator() {
        return 1;
    }

    @Override
    protected int calculatePoints() {
        return 5 * rounds + 15;
    }
}
