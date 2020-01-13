package de.nordakademie.informaticup.pandemicfighter.gameengine.factories;

import com.google.gson.JsonObject;
import de.nordakademie.informaticup.pandemicfighter.gameengine.ValueUtility;
import de.nordakademie.informaticup.pandemicfighter.gameengine.elements.Pathogen;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PathogenFactoryTest {
    JsonObject jsonObject;

    @Before
    public void setUp() throws Exception {
        jsonObject = new JsonObject();
        jsonObject.addProperty("name", "Test Pathogen");
        jsonObject.addProperty("infectivity", "+");
        jsonObject.addProperty("mobility", "-");
        jsonObject.addProperty("duration", "-");
        jsonObject.addProperty("lethality", "--");
    }

    @Test
    public void createPathogenTest() {
        Pathogen expectedPathogen = new Pathogen(
                "Test Pathogen",
                ValueUtility.getHighValuePathogen(),
                ValueUtility.getLowValuePathogen(),
                ValueUtility.getLowValuePathogen(),
                ValueUtility.getVeryLowValuePathogen()
        );
        Pathogen pathogen = new PathogenFactory().createPathogen(jsonObject);

        assertEquals(expectedPathogen.getName(), pathogen.getName());
        assertEquals(expectedPathogen.getInfectivity(), pathogen.getInfectivity(), 0.0);
        assertEquals(expectedPathogen.getMobility(), pathogen.getMobility(), 0.0);
        assertEquals(expectedPathogen.getDuration(), pathogen.getDuration(), 0.0);
        assertEquals(expectedPathogen.getLethality(), pathogen.getLethality(), 0.0);
    }
}
