package de.nordakademie.informaticup.pandemicfighter.gameengine.elements.events;

import de.nordakademie.informaticup.pandemicfighter.gameengine.elements.Pathogen;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MedicationDeployedEventTest {
    private MedicationDeployedEvent medicationDeployedEvent;
    private MedicationDeployedEvent medicationDeployedEvent2;
    private MedicationDeployedEvent medicationDeployedEvent3;

    @Before
    public void setUp() throws Exception {
        Pathogen pathogen = new Pathogen("Hexapox", 1, 1, 1, 1);
        Pathogen pathogen2 = new Pathogen("Admiral", 1.1, 0.9, 1, 1.1);
        Pathogen pathogen3 = new Pathogen("Neurodermantitis", 1, 0.9, 1, 0.9);

        medicationDeployedEvent = new MedicationDeployedEvent(pathogen, 3);
        medicationDeployedEvent2 = new MedicationDeployedEvent(pathogen2, 32);
        medicationDeployedEvent3 = new MedicationDeployedEvent(pathogen3, 56);
    }

    @Test
    public void getPathogen() {
        assertEquals("Hexapox", medicationDeployedEvent.getPathogen().getName());
        assertEquals("Admiral", medicationDeployedEvent2.getPathogen().getName());
        assertEquals("Neurodermantitis", medicationDeployedEvent3.getPathogen().getName());
    }

    @Test
    public void getPathogenFalseTest() {
        assertNotEquals("Neurodermantitis", medicationDeployedEvent.getPathogen().getName());
        assertNotEquals("Hexapox", medicationDeployedEvent2.getPathogen().getName());
        assertNotEquals("Admiral", medicationDeployedEvent3.getPathogen().getName());
    }

    @Test
    public void getRound() {
        assertEquals(3, medicationDeployedEvent.getRound());
        assertEquals(32, medicationDeployedEvent2.getRound());
        assertEquals(56, medicationDeployedEvent3.getRound());
    }

    @Test
    public void getRoundFalseTest() {
        assertNotEquals(2, medicationDeployedEvent.getRound());
        assertNotEquals(23, medicationDeployedEvent2.getRound());
        assertNotEquals(66, medicationDeployedEvent3.getRound());
    }

    @Test
    public void getType() {
        assertEquals("medicationDeployed", medicationDeployedEvent.getType());
        assertEquals("medicationDeployed", medicationDeployedEvent2.getType());
        assertEquals("medicationDeployed", medicationDeployedEvent3.getType());
    }

    @Test
    public void getTypeFalseTest() {
        assertNotEquals("medDeployed", medicationDeployedEvent.getType());
        assertNotEquals("medicationDeploy", medicationDeployedEvent2.getType());
        assertNotEquals("deployedMed", medicationDeployedEvent3.getType());
    }
}
