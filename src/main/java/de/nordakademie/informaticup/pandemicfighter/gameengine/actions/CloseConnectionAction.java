package de.nordakademie.informaticup.pandemicfighter.gameengine.actions;

import com.google.gson.JsonObject;
import de.nordakademie.informaticup.pandemicfighter.gameengine.ThreatEvaluator;
import de.nordakademie.informaticup.pandemicfighter.gameengine.elements.City;
import de.nordakademie.informaticup.pandemicfighter.gameengine.provider.JsonActionProvider;

public class CloseConnectionAction extends Action {
    private City fromCity;
    private City toCity;
    private int rounds;

    public CloseConnectionAction(City fromCity, City toCity, int rounds) {
        this.fromCity = fromCity;
        this.toCity = toCity;
        this.rounds = rounds;
        setCharacteristics();
    }

    @Override
    public JsonObject toJson() {
        return JsonActionProvider.closeConnection(fromCity, toCity, rounds);
    }

    @Override
    protected double calculateThreatIndicator() {
        return new ThreatEvaluator().calculateCloseConnection(fromCity, toCity);
    }

    @Override
    protected int calculatePoints() {
        return 3 * rounds + 3;
    }
}
