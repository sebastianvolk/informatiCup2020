package de.nordakademie.informaticup.pandemicfighter.gameengine.actions;

import com.google.gson.JsonObject;
import de.nordakademie.informaticup.pandemicfighter.gameengine.elements.City;
import de.nordakademie.informaticup.pandemicfighter.gameengine.elements.Pathogen;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class DeployMedicationActionTest {
    DeployMedicationAction deployMedicationAction;

    @Before
    public void setUp() throws Exception {
        Pathogen pathogen = new Pathogen("Test Pathogen", 1.2, 1, 1.1, 0.8);
        City city = new City("Test City", 0, 0, new ArrayList<>());
        deployMedicationAction = new DeployMedicationAction(pathogen, city);
    }

    @Test
    public void getPointsTest() {
        assertEquals(10, deployMedicationAction.getPoints());
    }

    @Test
    public void toJsonTest() {
        JsonObject expectedJsonObject = new JsonObject();
        expectedJsonObject.addProperty("type", "deployMedication");
        expectedJsonObject.addProperty("pathogen", "Test Pathogen");
        expectedJsonObject.addProperty("city", "Test City");

        assertEquals(expectedJsonObject, deployMedicationAction.toJson());
    }
}
