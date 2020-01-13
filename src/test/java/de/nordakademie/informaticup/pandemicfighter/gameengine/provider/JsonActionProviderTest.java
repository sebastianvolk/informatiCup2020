package de.nordakademie.informaticup.pandemicfighter.gameengine.provider;

import com.google.gson.JsonObject;
import de.nordakademie.informaticup.pandemicfighter.gameengine.elements.City;
import de.nordakademie.informaticup.pandemicfighter.gameengine.elements.Pathogen;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class JsonActionProviderTest {
    City city;
    City city2;
    Pathogen pathogen;

    @Before
    public void setUp() throws Exception {
        city = new City("Test City", 0, 0, new ArrayList<>());
        city2 = new City("Test City2", 0, 0, new ArrayList<>());
        pathogen = new Pathogen("Test Pathogen", 1.2, 1, 1.1, 0.8);
    }

    @Test
    public void endRoundTest() {
        JsonObject expectedJsonObject = new JsonObject();
        expectedJsonObject.addProperty("type", "endRound");

        assertEquals(expectedJsonObject, JsonActionProvider.endRound());
    }

    @Test
    public void putUnderQuarantineTest() {
        JsonObject expectedJsonObject = new JsonObject();
        expectedJsonObject.addProperty("type", "putUnderQuarantine");
        expectedJsonObject.addProperty("city", "Test City");
        expectedJsonObject.addProperty("rounds", 14);

        assertEquals(expectedJsonObject, JsonActionProvider.putUnderQuarantine(city, 14));
    }

    @Test
    public void closeAirportTest() {
        JsonObject expectedJsonObject = new JsonObject();
        expectedJsonObject.addProperty("type", "closeAirport");
        expectedJsonObject.addProperty("city", "Test City");
        expectedJsonObject.addProperty("rounds", 43);

        assertEquals(expectedJsonObject, JsonActionProvider.closeAirport(city, 43));
    }

    @Test
    public void closeConnectionTest() {
        JsonObject expectedJsonObject = new JsonObject();
        expectedJsonObject.addProperty("type", "closeConnection");
        expectedJsonObject.addProperty("fromCity", "Test City");
        expectedJsonObject.addProperty("toCity", "Test City2");
        expectedJsonObject.addProperty("rounds", 33);

        assertEquals(expectedJsonObject, JsonActionProvider.closeConnection(city, city2, 33));
    }

    @Test
    public void developVaccineTest() {
        JsonObject expectedJsonObject = new JsonObject();
        expectedJsonObject.addProperty("type", "developVaccine");
        expectedJsonObject.addProperty("pathogen", "Test Pathogen");

        assertEquals(expectedJsonObject, JsonActionProvider.developVaccine(pathogen));
    }

    @Test
    public void deployVaccineTest() {
        JsonObject expectedJsonObject = new JsonObject();
        expectedJsonObject.addProperty("type", "deployVaccine");
        expectedJsonObject.addProperty("pathogen", "Test Pathogen");
        expectedJsonObject.addProperty("city", "Test City");

        assertEquals(expectedJsonObject, JsonActionProvider.deployVaccine(pathogen, city));
    }

    @Test
    public void developMedicationTest() {
        JsonObject expectedJsonObject = new JsonObject();
        expectedJsonObject.addProperty("type", "developMedication");
        expectedJsonObject.addProperty("pathogen", "Test Pathogen");

        assertEquals(expectedJsonObject, JsonActionProvider.developMedication(pathogen));
    }

    @Test
    public void deployMedicationTest() {
        JsonObject expectedJsonObject = new JsonObject();
        expectedJsonObject.addProperty("type", "deployMedication");
        expectedJsonObject.addProperty("pathogen", "Test Pathogen");
        expectedJsonObject.addProperty("city", "Test City");

        assertEquals(expectedJsonObject, JsonActionProvider.deployMedication(pathogen, city));
    }

    @Test
    public void exertInfluenceTest() {
        JsonObject expectedJsonObject = new JsonObject();
        expectedJsonObject.addProperty("type", "exertInfluence");
        expectedJsonObject.addProperty("city", "Test City");

        assertEquals(expectedJsonObject, JsonActionProvider.exertInfluence(city));
    }

    @Test
    public void callElectionsTest() {
        JsonObject expectedJsonObject = new JsonObject();
        expectedJsonObject.addProperty("type", "callElections");
        expectedJsonObject.addProperty("city", "Test City");

        assertEquals(expectedJsonObject, JsonActionProvider.callElections(city));
    }

    @Test
    public void applyHygienicMeasuresTest() {
        JsonObject expectedJsonObject = new JsonObject();
        expectedJsonObject.addProperty("type", "applyHygienicMeasures");
        expectedJsonObject.addProperty("city", "Test City");

        assertEquals(expectedJsonObject, JsonActionProvider.applyHygienicMeasures(city));
    }

    @Test
    public void launchCampaignTest() {
        JsonObject expectedJsonObject = new JsonObject();
        expectedJsonObject.addProperty("type", "launchCampaign");
        expectedJsonObject.addProperty("city", "Test City");

        assertEquals(expectedJsonObject, JsonActionProvider.launchCampaign(city));
    }
}
