package de.nordakademie.informaticup.pandemicfighter.gameengine.actions;

import com.google.gson.JsonObject;
import de.nordakademie.informaticup.pandemicfighter.gameengine.elements.City;
import de.nordakademie.informaticup.pandemicfighter.gameengine.provider.CityProvider;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class PutUnderQuarantineActionTest {
    City city;

    @Before
    public void setUp() throws Exception {
        city = new City("Test City", 0, 0, new ArrayList<>());
    }

    @Test
    public void getPointsOneRoundTest() {
        PutUnderQuarantineAction putUnderQuarantineAction = new PutUnderQuarantineAction(city, 1);
        assertEquals(30, putUnderQuarantineAction.getPoints());
    }

    @Test
    public void getPointsTwoRoundsTest() {
        PutUnderQuarantineAction putUnderQuarantineAction = new PutUnderQuarantineAction(city, 2);
        assertEquals(40, putUnderQuarantineAction.getPoints());
    }

    @Test
    public void getPointsFifeRoundsTest() {
        PutUnderQuarantineAction putUnderQuarantineAction = new PutUnderQuarantineAction(city, 5);
        assertEquals(70, putUnderQuarantineAction.getPoints());
    }

    @Test
    public void toJsonOneRoundTest() {
        JsonObject expectedJsonObject = new JsonObject();
        expectedJsonObject.addProperty("type", "putUnderQuarantine");
        expectedJsonObject.addProperty("city", "Test City");
        expectedJsonObject.addProperty("rounds", 1);
        PutUnderQuarantineAction putUnderQuarantineAction = new PutUnderQuarantineAction(city, 1);

        assertEquals(expectedJsonObject, putUnderQuarantineAction.toJson());
    }

    @Test
    public void toJsonTwoRoundsTest() {
        JsonObject expectedJsonObject = new JsonObject();
        expectedJsonObject.addProperty("type", "putUnderQuarantine");
        expectedJsonObject.addProperty("city", "Test City");
        expectedJsonObject.addProperty("rounds", 2);
        PutUnderQuarantineAction putUnderQuarantineAction = new PutUnderQuarantineAction(city, 2);

        assertEquals(expectedJsonObject, putUnderQuarantineAction.toJson());
    }

    @Test
    public void toJsonFifeRoundsTest() {
        JsonObject expectedJsonObject = new JsonObject();
        expectedJsonObject.addProperty("type", "putUnderQuarantine");
        expectedJsonObject.addProperty("city", "Test City");
        expectedJsonObject.addProperty("rounds", 5);
        PutUnderQuarantineAction putUnderQuarantineAction = new PutUnderQuarantineAction(city, 5);

        assertEquals(expectedJsonObject, putUnderQuarantineAction.toJson());
    }
}