package de.nordakademie.informaticup.pandemicfighter.gameengine.elements.events;

import de.nordakademie.informaticup.pandemicfighter.gameengine.elements.Pathogen;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PathogenEncounteredEventTest {
    private PathogenEncounteredEvent pathogenEncounteredEvent;
    private PathogenEncounteredEvent pathogenEncounteredEvent2;
    private PathogenEncounteredEvent pathogenEncounteredEvent3;

    @Before
    public void setUp() throws Exception {
        Pathogen pathogen = new Pathogen("Hexapox", 1, 1, 1, 1);
        Pathogen pathogen2 = new Pathogen("Admiral", 1.1, 0.9, 1, 1.1);
        Pathogen pathogen3 = new Pathogen("Neurodermantits", 1, 0.9, 1, 0.9);

        pathogenEncounteredEvent = new PathogenEncounteredEvent(pathogen, 4);
        pathogenEncounteredEvent2 = new PathogenEncounteredEvent(pathogen2, 78);
        pathogenEncounteredEvent3 = new PathogenEncounteredEvent(pathogen3, 20);
    }

    @Test
    public void getPathogen() {
        assertEquals("Hexapox", pathogenEncounteredEvent.getPathogen().getName());
        assertEquals("Admiral", pathogenEncounteredEvent2.getPathogen().getName());
        assertEquals("Neurodermantits", pathogenEncounteredEvent3.getPathogen().getName());
    }

    @Test
    public void getPathogenFalseTest() {
        assertNotEquals("Admiral", pathogenEncounteredEvent.getPathogen().getName());
        assertNotEquals("Neurodermantitis", pathogenEncounteredEvent2.getPathogen().getName());
        assertNotEquals("Hexapox", pathogenEncounteredEvent3.getPathogen().getName());
    }

    @Test
    public void getRound() {
        assertEquals(4, pathogenEncounteredEvent.getRound());
        assertEquals(78, pathogenEncounteredEvent2.getRound());
        assertEquals(20, pathogenEncounteredEvent3.getRound());
    }

    @Test
    public void getRoundFalseTest() {
        assertNotEquals(5, pathogenEncounteredEvent.getRound());
        assertNotEquals(87, pathogenEncounteredEvent2.getRound());
        assertNotEquals(200, pathogenEncounteredEvent3.getRound());
    }
}