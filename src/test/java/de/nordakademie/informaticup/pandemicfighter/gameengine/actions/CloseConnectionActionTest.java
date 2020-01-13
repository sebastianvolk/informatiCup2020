package de.nordakademie.informaticup.pandemicfighter.gameengine.actions;

import com.google.gson.JsonObject;
import de.nordakademie.informaticup.pandemicfighter.gameengine.elements.City;
import de.nordakademie.informaticup.pandemicfighter.gameengine.provider.CityProvider;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class CloseConnectionActionTest {
    City fromCity;
    City toCity;

    @Before
    public void setUp() throws Exception {
        fromCity = new City("Test fromCity", 0, 0, new ArrayList<>());
        toCity = new City("Test toCity", 0, 0, new ArrayList<>());
    }

    @Test
    public void getPointsOneRoundTest() {
        CloseConnectionAction closeConnectionAction = new CloseConnectionAction(fromCity, toCity, 1);
        assertEquals(6, closeConnectionAction.getPoints());
    }

    @Test
    public void getPointsTwoRoundsTest() {
        CloseConnectionAction closeConnectionAction = new CloseConnectionAction(fromCity, toCity, 2);
        assertEquals(9, closeConnectionAction.getPoints());
    }

    @Test
    public void getPointsFifeRoundsTest() {
        CloseConnectionAction closeConnectionAction = new CloseConnectionAction(fromCity, toCity, 5);
        assertEquals(18, closeConnectionAction.getPoints());
    }

    @Test
    public void toJsonOneRoundTest() {
        JsonObject expectedJsonObject = new JsonObject();
        expectedJsonObject.addProperty("type", "closeConnection");
        expectedJsonObject.addProperty("fromCity", "Test fromCity");
        expectedJsonObject.addProperty("toCity", "Test toCity");
        expectedJsonObject.addProperty("rounds", 1);
        CloseConnectionAction closeConnectionAction = new CloseConnectionAction(fromCity, toCity, 1);

        assertEquals(expectedJsonObject, closeConnectionAction.toJson());
    }

    @Test
    public void toJsonTwoRoundsTest() {
        JsonObject expectedJsonObject = new JsonObject();
        expectedJsonObject.addProperty("type", "closeConnection");
        expectedJsonObject.addProperty("fromCity", "Test fromCity");
        expectedJsonObject.addProperty("toCity", "Test toCity");
        expectedJsonObject.addProperty("rounds", 2);
        CloseConnectionAction closeConnectionAction = new CloseConnectionAction(fromCity, toCity, 2);

        assertEquals(expectedJsonObject, closeConnectionAction.toJson());
    }

    @Test
    public void toJsonFifeRoundsTest() {
        JsonObject expectedJsonObject = new JsonObject();
        expectedJsonObject.addProperty("type", "closeConnection");
        expectedJsonObject.addProperty("fromCity", "Test fromCity");
        expectedJsonObject.addProperty("toCity", "Test toCity");
        expectedJsonObject.addProperty("rounds", 5);
        CloseConnectionAction closeConnectionAction = new CloseConnectionAction(fromCity, toCity, 5);

        assertEquals(expectedJsonObject, closeConnectionAction.toJson());
    }
}