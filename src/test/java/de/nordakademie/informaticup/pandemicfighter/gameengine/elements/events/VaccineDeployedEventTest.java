package de.nordakademie.informaticup.pandemicfighter.gameengine.elements.events;

import de.nordakademie.informaticup.pandemicfighter.gameengine.elements.Pathogen;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class VaccineDeployedEventTest {
    private VaccineDeployedEvent vaccineDeployedEvent;
    private VaccineDeployedEvent vaccineDeployedEvent2;
    private VaccineDeployedEvent vaccineDeployedEvent3;

    @Before
    public void setUp() throws Exception {
        Pathogen pathogen = new Pathogen("Hexapox", 1, 1, 1, 1);
        Pathogen pathogen2 = new Pathogen("Admiral", 1.1, 0.9, 1, 1.1);
        Pathogen pathogen3 = new Pathogen("Neurodermantitis", 1, 0.9, 1, 0.9);

        vaccineDeployedEvent = new VaccineDeployedEvent(3, pathogen);
        vaccineDeployedEvent2 = new VaccineDeployedEvent(45, pathogen2);
        vaccineDeployedEvent3 = new VaccineDeployedEvent(78, pathogen3);
    }

    @Test
    public void getPathogen() {
        assertEquals("Hexapox",vaccineDeployedEvent.getPathogen());
        assertEquals("Admiral",vaccineDeployedEvent2.getPathogen());
        assertEquals("Neurodermantitis",vaccineDeployedEvent3.getPathogen());
    }

    @Test
    public void getPathogenFalseTest() {
        assertNotEquals("Neurodermantitis",vaccineDeployedEvent.getPathogen());
        assertNotEquals("Hexapox",vaccineDeployedEvent2.getPathogen());
        assertNotEquals("Admiral",vaccineDeployedEvent3.getPathogen());
    }

    @Test
    public void getRound() {
        assertEquals(3, vaccineDeployedEvent.getRound());
        assertEquals(45, vaccineDeployedEvent2.getRound());
        assertEquals(78, vaccineDeployedEvent3.getRound());
    }

    @Test
    public void getRoundFalseTest() {
        assertNotEquals(4, vaccineDeployedEvent.getRound());
        assertNotEquals(56, vaccineDeployedEvent2.getRound());
        assertNotEquals(20, vaccineDeployedEvent3.getRound());
    }

    @Test
    public void getType() {
        assertEquals("vaccineDeployed", vaccineDeployedEvent.getType());
        assertEquals("vaccineDeployed", vaccineDeployedEvent2.getType());
        assertEquals("vaccineDeployed", vaccineDeployedEvent3.getType());
    }

    @Test
    public void getTypeFalseTest() {
        assertNotEquals("vaccineDeploy", vaccineDeployedEvent.getType());
        assertNotEquals("vaccDeployed", vaccineDeployedEvent2.getType());
        assertNotEquals("deployVaccine", vaccineDeployedEvent3.getType());
    }
}