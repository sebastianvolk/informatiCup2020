package de.nordakademie.informaticup.pandemicfighter.gameengine.elements.events;

import de.nordakademie.informaticup.pandemicfighter.gameengine.elements.Pathogen;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class VaccineInDevelopmentEventTest {
    private VaccineInDevelopmentEvent vaccineInDevelopmentEvent;
    private VaccineInDevelopmentEvent vaccineInDevelopmentEvent2;
    private VaccineInDevelopmentEvent vaccineInDevelopmentEvent3;

    @Before
    public void setUp() throws Exception {
        Pathogen pathogen = new Pathogen("Hexapox", 1, 1, 1, 1);
        Pathogen pathogen2 = new Pathogen("Admiral", 1.1, 0.9, 1, 1.1);
        Pathogen pathogen3 = new Pathogen("Neurodermantitis", 1, 0.9, 1, 0.9);

        vaccineInDevelopmentEvent = new VaccineInDevelopmentEvent(pathogen, 3, 6);
        vaccineInDevelopmentEvent2 = new VaccineInDevelopmentEvent(pathogen2, 32, 36);
        vaccineInDevelopmentEvent3 = new VaccineInDevelopmentEvent(pathogen3, 56, 59);
    }

    @Test
    public void getPathogen() {
        assertEquals("Hexapox", vaccineInDevelopmentEvent.getPathogen().getName());
        assertEquals("Admiral", vaccineInDevelopmentEvent2.getPathogen().getName());
        assertEquals("Neurodermantitis", vaccineInDevelopmentEvent3.getPathogen().getName());
    }

    @Test
    public void getPathogenFalseTest() {
        assertNotEquals("Neurodermantitis", vaccineInDevelopmentEvent.getPathogen().getName());
        assertNotEquals("Hexapox", vaccineInDevelopmentEvent2.getPathogen().getName());
        assertNotEquals("Admiral", vaccineInDevelopmentEvent3.getPathogen().getName());
    }

    @Test
    public void getSinceRound() {
        assertEquals(3, vaccineInDevelopmentEvent.getSinceRound());
        assertEquals(32, vaccineInDevelopmentEvent2.getSinceRound());
        assertEquals(56, vaccineInDevelopmentEvent3.getSinceRound());
    }

    @Test
    public void getSinceRoundFalseTest() {
        assertNotEquals(2, vaccineInDevelopmentEvent.getSinceRound());
        assertNotEquals(23, vaccineInDevelopmentEvent2.getSinceRound());
        assertNotEquals(66, vaccineInDevelopmentEvent3.getSinceRound());
    }

    @Test
    public void getUntilRound() {
        assertEquals(6, vaccineInDevelopmentEvent.getUntilRound());
        assertEquals(36, vaccineInDevelopmentEvent2.getUntilRound());
        assertEquals(59, vaccineInDevelopmentEvent3.getUntilRound());
    }

    @Test
    public void getUntilRoundFalseTest() {
        assertNotEquals(3, vaccineInDevelopmentEvent.getUntilRound());
        assertNotEquals(49, vaccineInDevelopmentEvent2.getUntilRound());
        assertNotEquals(51, vaccineInDevelopmentEvent3.getUntilRound());
    }
}