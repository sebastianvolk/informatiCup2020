package de.nordakademie.informaticup.pandemicfighter.gameengine;

import com.google.gson.JsonObject;
import de.nordakademie.informaticup.pandemicfighter.gameengine.provider.CityProvider;
import de.nordakademie.informaticup.pandemicfighter.gameengine.provider.cabinets.MedicationCabinet;
import de.nordakademie.informaticup.pandemicfighter.gameengine.provider.cabinets.VaccineCabinet;

public class GameExecutor {
    private Game game;
    private JsonObject action = new JsonObject();

    public GameExecutor(Game game) {
        this.game = game;
    }

    public GameExecutor playRound(){
        initializeProvider();
        ActionSelector actionSelector = new ActionSelector(game);
        action = actionSelector.getAction();
        return this;
    }

    private void initializeProvider() {
        CityProvider.setCities(game.getCities());
        MedicationCabinet.initialize(game);
        VaccineCabinet.initialize(game);
    }

    public JsonObject getAction() {
        return action;
    }
}
