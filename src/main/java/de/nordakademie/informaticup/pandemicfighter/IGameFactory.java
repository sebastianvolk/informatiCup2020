package de.nordakademie.informaticup.pandemicfighter;

import com.google.gson.JsonObject;
import de.nordakademie.informaticup.pandemicfighter.gameengine.elements.Game;

public interface IGameFactory {
    Game createGame(JsonObject jsonGame);
}
