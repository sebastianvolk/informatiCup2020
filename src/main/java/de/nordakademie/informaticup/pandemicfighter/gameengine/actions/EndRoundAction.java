package de.nordakademie.informaticup.pandemicfighter.gameengine.actions;

import com.google.gson.JsonObject;
import de.nordakademie.informaticup.pandemicfighter.gameengine.provider.JsonActionProvider;

public class EndRoundAction extends Action {
    public EndRoundAction() {
        setCharacteristics();
    }

    @Override
    public JsonObject toJson() {
        return JsonActionProvider.endRound();
    }

    @Override
    protected double calculateThreatIndicator() {
        return 0.4;
    }

    @Override
    protected int calculatePoints() {
        return 0;
    }
}
