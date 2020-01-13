package de.nordakademie.informaticup.pandemicfighter.gameengine.actions;

import com.google.gson.JsonObject;
import de.nordakademie.informaticup.pandemicfighter.gameengine.elements.City;
import de.nordakademie.informaticup.pandemicfighter.gameengine.provider.CityProvider;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class CloseAirportActionTest {
    City city;

    @Before
    public void setUp() throws Exception {
        city = new City("Test City", 0, 0, new ArrayList<>());
    }

    @Test
    public void getPointsOneRoundTest() {
        CloseAirportAction closeAirportAction = new CloseAirportAction(city, 1);
        assertEquals(20, closeAirportAction.getPoints());
    }

    @Test
    public void getPointsTwoRoundsTest() {
        CloseAirportAction closeAirportAction = new CloseAirportAction(city, 2);
        assertEquals(25, closeAirportAction.getPoints());
    }

    @Test
    public void getPointsFifeRoundsTest() {
        CloseAirportAction closeAirportAction = new CloseAirportAction(city, 5);
        assertEquals(40, closeAirportAction.getPoints());
    }

    @Test
    public void toJsonOneRoundTest() {
        JsonObject expectedJsonObject = new JsonObject();
        expectedJsonObject.addProperty("type", "closeAirport");
        expectedJsonObject.addProperty("city", "Test City");
        expectedJsonObject.addProperty("rounds", 1);
        CloseAirportAction closeAirportAction = new CloseAirportAction(city, 1);

        assertEquals(expectedJsonObject, closeAirportAction.toJson());
    }

    @Test
    public void toJsonTwoRoundsTest() {
        JsonObject expectedJsonObject = new JsonObject();
        expectedJsonObject.addProperty("type", "closeAirport");
        expectedJsonObject.addProperty("city", "Test City");
        expectedJsonObject.addProperty("rounds", 2);
        CloseAirportAction closeAirportAction = new CloseAirportAction(city, 2);

        assertEquals(expectedJsonObject, closeAirportAction.toJson());
    }

    @Test
    public void toJsonFifeRoundsTest() {
        JsonObject expectedJsonObject = new JsonObject();
        expectedJsonObject.addProperty("type", "closeAirport");
        expectedJsonObject.addProperty("city", "Test City");
        expectedJsonObject.addProperty("rounds", 5);
        CloseAirportAction closeAirportAction = new CloseAirportAction(city, 5);

        assertEquals(expectedJsonObject, closeAirportAction.toJson());
    }
}
