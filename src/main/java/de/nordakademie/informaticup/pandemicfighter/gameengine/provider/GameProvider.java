package de.nordakademie.informaticup.pandemicfighter.gameengine.provider;

import de.nordakademie.informaticup.pandemicfighter.gameengine.elements.Game;

public class GameProvider {
    private static Game game;

    public static void initialize(Game game) {
        GameProvider.game = game;
    }

    public static Game getGame() {
        return game;
    }
}
