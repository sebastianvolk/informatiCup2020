package de.nordakademie.informaticup.pandemicfighter.gameengine.elements.events;

import de.nordakademie.informaticup.pandemicfighter.gameengine.elements.Pathogen;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MedicationAvailableEventTest {
    private MedicationAvailableEvent medicationAvailableEvent;
    private MedicationAvailableEvent medicationAvailableEvent2;
    private MedicationAvailableEvent medicationAvailableEvent3;

    @Before
    public void setUp() throws Exception {
        Pathogen pathogen = new Pathogen("Hexapox", 1, 1, 1, 1);
        Pathogen pathogen2 = new Pathogen("Admiral", 1.1, 0.9, 1, 1.1);
        Pathogen pathogen3 = new Pathogen("Neurodermantitis", 1, 0.9, 1, 0.9);

        medicationAvailableEvent = new MedicationAvailableEvent(pathogen, 3);
        medicationAvailableEvent2 = new MedicationAvailableEvent(pathogen2, 32);
        medicationAvailableEvent3 = new MedicationAvailableEvent(pathogen3, 56);
    }

    @Test
    public void getPathogen() {
        assertEquals("Hexapox", medicationAvailableEvent.getPathogen().getName());
        assertEquals("Admiral", medicationAvailableEvent2.getPathogen().getName());
        assertEquals("Neurodermantitis", medicationAvailableEvent3.getPathogen().getName());
    }

    @Test
    public void getPathogenFalseTest() {
        assertNotEquals("Neurodermantitis", medicationAvailableEvent.getPathogen().getName());
        assertNotEquals("Hexapox", medicationAvailableEvent2.getPathogen().getName());
        assertNotEquals("Admiral", medicationAvailableEvent3.getPathogen().getName());
    }

    @Test
    public void getSinceRound() {
        assertEquals(3, medicationAvailableEvent.getSinceRound());
        assertEquals(32, medicationAvailableEvent2.getSinceRound());
        assertEquals(56, medicationAvailableEvent3.getSinceRound());
    }

    @Test
    public void getSinceRoundFalseTest() {
        assertNotEquals(2, medicationAvailableEvent.getSinceRound());
        assertNotEquals(23, medicationAvailableEvent2.getSinceRound());
        assertNotEquals(66, medicationAvailableEvent3.getSinceRound());
    }

    @Test
    public void getType() {
        assertEquals("medicationAvailable", medicationAvailableEvent.getType());
        assertEquals("medicationAvailable", medicationAvailableEvent2.getType());
        assertEquals("medicationAvailable", medicationAvailableEvent3.getType());
    }

    @Test
    public void getTypeFalseTest() {
        assertNotEquals("medAvailable", medicationAvailableEvent.getType());
        assertNotEquals("medicationAv", medicationAvailableEvent2.getType());
        assertNotEquals("availableMed", medicationAvailableEvent3.getType());
    }
}
