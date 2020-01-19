package de.nordakademie.informaticup.pandemicfighter.gameengine.elements.events;

import de.nordakademie.informaticup.pandemicfighter.gameengine.elements.Pathogen;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class VaccineAvailableEventTest {
    private VaccineAvailableEvent vaccineAvailableEvent;
    private VaccineAvailableEvent vaccineAvailableEvent2;
    private VaccineAvailableEvent vaccineAvailableEvent3;

    @Before
    public void setUp() throws Exception {
        Pathogen pathogen = new Pathogen("Hexapox", 1, 1, 1, 1);
        Pathogen pathogen2 = new Pathogen("Admiral", 1.1, 0.9, 1, 1.1);
        Pathogen pathogen3 = new Pathogen("Neurodermantitis", 1, 0.9, 1, 0.9);

        vaccineAvailableEvent = new VaccineAvailableEvent(pathogen, 3);
        vaccineAvailableEvent2 = new VaccineAvailableEvent(pathogen2, 65);
        vaccineAvailableEvent3 = new VaccineAvailableEvent(pathogen3, 25);
    }

    @Test
    public void getPathogen() {
        assertEquals("Hexapox",vaccineAvailableEvent.getPathogen().getName());
        assertEquals("Admiral",vaccineAvailableEvent2.getPathogen().getName());
        assertEquals("Neurodermantitis",vaccineAvailableEvent3.getPathogen().getName());
    }

    @Test
    public void getPathogenFalseTest() {
        assertNotEquals("Neurodermantitis",vaccineAvailableEvent.getPathogen().getName());
        assertNotEquals("Hexapox",vaccineAvailableEvent2.getPathogen().getName());
        assertNotEquals("Admiral",vaccineAvailableEvent3.getPathogen().getName());
    }

    @Test
    public void getSinceRound() {
        assertEquals(3, vaccineAvailableEvent.getSinceRound());
        assertEquals(65, vaccineAvailableEvent2.getSinceRound());
        assertEquals(25, vaccineAvailableEvent3.getSinceRound());
    }

    @Test
    public void getSinceRoundFalseTest() {
        assertNotEquals(4, vaccineAvailableEvent.getSinceRound());
        assertNotEquals(56, vaccineAvailableEvent2.getSinceRound());
        assertNotEquals(20, vaccineAvailableEvent3.getSinceRound());
    }

    @Test
    public void getType() {
        assertEquals("vaccineAvailable", vaccineAvailableEvent.getType());
        assertEquals("vaccineAvailable", vaccineAvailableEvent2.getType());
        assertEquals("vaccineAvailable", vaccineAvailableEvent3.getType());
    }

    @Test
    public void getTypeFalseTest() {
        assertNotEquals("vaccAvailable", vaccineAvailableEvent.getType());
        assertNotEquals("availableVaccine", vaccineAvailableEvent2.getType());
        assertNotEquals("availableVacc", vaccineAvailableEvent3.getType());
    }
}
