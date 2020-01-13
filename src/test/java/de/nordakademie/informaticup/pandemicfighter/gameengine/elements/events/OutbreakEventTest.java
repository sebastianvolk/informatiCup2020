package de.nordakademie.informaticup.pandemicfighter.gameengine.elements.events;

import de.nordakademie.informaticup.pandemicfighter.gameengine.elements.Pathogen;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class OutbreakEventTest {
    private OutbreakEvent outbreakEvent;
    private OutbreakEvent outbreakEvent2;
    private OutbreakEvent outbreakEvent3;

    @Before
    public void setUp() throws Exception {
        Pathogen pathogen = new Pathogen("Hexapox", 1, 1, 1, 1);
        Pathogen pathogen2 = new Pathogen("Admiral", 1.1, 0.9, 1, 1.1);
        Pathogen pathogen3 = new Pathogen("Neurodermantits", 1, 0.9, 1, 0.9);

        outbreakEvent = new OutbreakEvent(pathogen, 0.65, 4);
        outbreakEvent2 = new OutbreakEvent(pathogen2, 0.2, 50);
        outbreakEvent3 = new OutbreakEvent(pathogen3, 0.09, 78);
    }

    @Test
    public void getPathogen() {
        assertEquals("Hexapox", outbreakEvent.getPathogen().getName());
        assertEquals("Admiral", outbreakEvent2.getPathogen().getName());
        assertEquals("Neurodermantits", outbreakEvent3.getPathogen().getName());
    }

    @Test
    public void getPathogenFalseTest() {
        assertNotEquals("Neurodermantits", outbreakEvent.getPathogen().getName());
        assertNotEquals("Hexapox", outbreakEvent2.getPathogen().getName());
        assertNotEquals("Admiral", outbreakEvent3.getPathogen().getName());
    }

    @Test
    public void getPrevalence() {
        assertEquals(0.65, outbreakEvent.getPrevalence(), 0.1);
        assertEquals(0.2, outbreakEvent2.getPrevalence(), 0.1);
        assertEquals(0.09, outbreakEvent3.getPrevalence(), 0.1);
    }

    @Test
    public void getPrevalenceFalseTest() {
        assertNotEquals(0.7, outbreakEvent.getPrevalence());
        assertNotEquals(0.1, outbreakEvent2.getPrevalence());
        assertNotEquals(0, outbreakEvent3.getPrevalence());
    }

    @Test
    public void getSinceRound() {
        assertEquals(4, outbreakEvent.getSinceRound());
        assertEquals(50, outbreakEvent2.getSinceRound());
        assertEquals(78, outbreakEvent3.getSinceRound());
    }

    @Test
    public void getSinceRoundFalseTest() {
        assertNotEquals(3, outbreakEvent.getSinceRound());
        assertNotEquals(52, outbreakEvent2.getSinceRound());
        assertNotEquals(87, outbreakEvent3.getSinceRound());
    }

    @Test
    public void getType() {
        assertEquals("outbreak", outbreakEvent.getType());
        assertEquals("outbreak", outbreakEvent2.getType());
        assertEquals("outbreak", outbreakEvent3.getType());
    }

    @Test
    public void getTypeFalseTest() {
        assertNotEquals("ob", outbreakEvent.getType());
        assertNotEquals("breakout", outbreakEvent2.getType());
        assertNotEquals("outbreakEvent", outbreakEvent3.getType());
    }
}