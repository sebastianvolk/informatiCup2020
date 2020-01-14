package de.nordakademie.informaticup.pandemicfighter.gameengine.actions;

import com.google.gson.JsonObject;
import de.nordakademie.informaticup.pandemicfighter.gameengine.elements.Game;
import de.nordakademie.informaticup.pandemicfighter.gameengine.provider.GameProvider;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class EndRoundActionTest {
    private EndRoundAction endRoundAction;

    @Before
    public void setUp() throws Exception {
        Game game = new Game();
        GameProvider.initialize(game);
        endRoundAction = new EndRoundAction();
    }

    @Test
    public void getPointsTest() {
        assertEquals(0, endRoundAction.getPoints());
    }

    @Test
    public void toJsonTest() {
        JsonObject expectedJsonObject = new JsonObject();
        expectedJsonObject.addProperty("type", "endRound");

        assertEquals(expectedJsonObject, endRoundAction.toJson());
    }
}
