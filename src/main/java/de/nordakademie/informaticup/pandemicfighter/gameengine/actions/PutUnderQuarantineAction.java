package de.nordakademie.informaticup.pandemicfighter.gameengine.actions;

import com.google.gson.JsonObject;
import de.nordakademie.informaticup.pandemicfighter.gameengine.ThreatEvaluator;
import de.nordakademie.informaticup.pandemicfighter.gameengine.elements.City;
import de.nordakademie.informaticup.pandemicfighter.gameengine.provider.JsonActionProvider;

public class PutUnderQuarantineAction extends Action {
    private City city;
    private int rounds;

    public PutUnderQuarantineAction(City city, int rounds) {
        this.city = city;
        this.rounds = rounds;
        setCharacteristics();
    }

    @Override
    public JsonObject toJson() {
        return JsonActionProvider.putUnderQuarantine(city, rounds);
    }

    @Override
    protected double calculateThreatIndicator() {
        return new ThreatEvaluator().calculatePutUnderQuarantine(city, rounds);
    }

    @Override
    protected int calculatePoints() {
        return 10 * rounds + 20;
    }
}
