package de.nordakademie.informaticup.pandemicfighter.gameengine.actions;

import com.google.gson.JsonObject;
import de.nordakademie.informaticup.pandemicfighter.gameengine.elements.Pathogen;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DevelopVaccineActionTest {
    DevelopVaccineAction developVaccineAction;

    @Before
    public void setUp() throws Exception {
        Pathogen pathogen = new Pathogen("Test Pathogen", 1.2, 1, 1.1, 0.8);
        developVaccineAction = new DevelopVaccineAction(pathogen);
    }

    @Test
    public void getPointsTest() {
        assertEquals(40, developVaccineAction.getPoints());
    }

    @Test
    public void toJsonTest() {
        JsonObject expectedJsonObject = new JsonObject();
        expectedJsonObject.addProperty("type", "developVaccine");
        expectedJsonObject.addProperty("pathogen", "Test Pathogen");

        assertEquals(expectedJsonObject, developVaccineAction.toJson());
    }
}
