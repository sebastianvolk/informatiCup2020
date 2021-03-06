package de.nordakademie.informaticup.pandemicfighter.gameengine.actions;

import com.google.gson.JsonObject;
import de.nordakademie.informaticup.pandemicfighter.gameengine.ThreatEvaluator;
import de.nordakademie.informaticup.pandemicfighter.gameengine.elements.City;
import de.nordakademie.informaticup.pandemicfighter.gameengine.provider.JsonActionProvider;

public class ApplyHygienicMeasuresAction extends Action {
    private City city;

    public ApplyHygienicMeasuresAction(City city) {
        this.city = city;
        setCharacteristics();
    }

    @Override
    public JsonObject toJson() {
        return JsonActionProvider.applyHygienicMeasures(city);
    }

    @Override
    protected double calculateThreatIndicator() {
        return new ThreatEvaluator().calculateApplyHygienicMeasures(city);
    }

    @Override
    protected int calculatePoints() {
        return 3;
    }
}
