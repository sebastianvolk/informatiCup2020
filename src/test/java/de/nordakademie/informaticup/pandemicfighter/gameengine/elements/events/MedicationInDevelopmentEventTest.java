package de.nordakademie.informaticup.pandemicfighter.gameengine.elements.events;

import de.nordakademie.informaticup.pandemicfighter.gameengine.elements.Pathogen;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MedicationInDevelopmentEventTest {
    private MedicationInDevelopmentEvent medicationInDevelopmentEvent;
    private MedicationInDevelopmentEvent medicationInDevelopmentEvent2;
    private MedicationInDevelopmentEvent medicationInDevelopmentEvent3;

    @Before
    public void setUp() throws Exception {
        Pathogen pathogen = new Pathogen("Hexapox", 1, 1, 1, 1);
        Pathogen pathogen2 = new Pathogen("Admiral", 1.1, 0.9, 1, 1.1);
        Pathogen pathogen3 = new Pathogen("Neurodermantitis", 1, 0.9, 1, 0.9);

        medicationInDevelopmentEvent = new MedicationInDevelopmentEvent(pathogen, 3, 6);
        medicationInDevelopmentEvent2 = new MedicationInDevelopmentEvent(pathogen2, 32, 36);
        medicationInDevelopmentEvent3 = new MedicationInDevelopmentEvent(pathogen3, 56, 59);
    }

    @Test
    public void getPathogen() {
        assertEquals("Hexapox", medicationInDevelopmentEvent.getPathogen().getName());
        assertEquals("Admiral", medicationInDevelopmentEvent2.getPathogen().getName());
        assertEquals("Neurodermantitis", medicationInDevelopmentEvent3.getPathogen().getName());
    }

    @Test
    public void getPathogenFalseTest() {
        assertNotEquals("Neurodermantitis", medicationInDevelopmentEvent.getPathogen().getName());
        assertNotEquals("Hexapox", medicationInDevelopmentEvent2.getPathogen().getName());
        assertNotEquals("Admiral", medicationInDevelopmentEvent3.getPathogen().getName());
    }

    @Test
    public void getSinceRound() {
        assertEquals(3, medicationInDevelopmentEvent.getSinceRound());
        assertEquals(32, medicationInDevelopmentEvent2.getSinceRound());
        assertEquals(56, medicationInDevelopmentEvent3.getSinceRound());
    }

    @Test
    public void getSinceRoundFalseTest() {
        assertNotEquals(2, medicationInDevelopmentEvent.getSinceRound());
        assertNotEquals(23, medicationInDevelopmentEvent2.getSinceRound());
        assertNotEquals(66, medicationInDevelopmentEvent3.getSinceRound());
    }

    @Test
    public void getUntilRound() {
        assertEquals(6, medicationInDevelopmentEvent.getUntilRound());
        assertEquals(36, medicationInDevelopmentEvent2.getUntilRound());
        assertEquals(59, medicationInDevelopmentEvent3.getUntilRound());
    }

    @Test
    public void getUntilRoundFalseTest() {
        assertNotEquals(3, medicationInDevelopmentEvent.getUntilRound());
        assertNotEquals(49, medicationInDevelopmentEvent2.getUntilRound());
        assertNotEquals(51, medicationInDevelopmentEvent3.getUntilRound());
    }

    @Test
    public void getType() {
        assertEquals("medicationInDevelopment", medicationInDevelopmentEvent.getType());
        assertEquals("medicationInDevelopment", medicationInDevelopmentEvent2.getType());
        assertEquals("medicationInDevelopment", medicationInDevelopmentEvent3.getType());
    }

    @Test
    public void getTypeFalseTest() {
        assertNotEquals("medInDevelopment", medicationInDevelopmentEvent.getType());
        assertNotEquals("medicationDevelopment", medicationInDevelopmentEvent2.getType());
        assertNotEquals("medicationDeveloped", medicationInDevelopmentEvent3.getType());
    }
}