package de.nordakademie.informaticup.pandemicfighter.gameengine.elements.events;

import de.nordakademie.informaticup.pandemicfighter.gameengine.elements.Pathogen;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BioTerrorismEventTest {

    private BioTerrorismEvent bioTerrorismEvent;
    private BioTerrorismEvent bioTerrorismEvent2;
    private BioTerrorismEvent bioTerrorismEvent3;

    @Before
    public void setUp() throws Exception {
        Pathogen pathogen = new Pathogen("Hexapox", 1, 1, 1, 1);
        Pathogen pathogen2 = new Pathogen("Admiral", 1.1, 0.9, 1, 1.1);
        Pathogen pathogen3 = new Pathogen("Neurodermantits", 1, 0.9, 1, 0.9);

        bioTerrorismEvent = new BioTerrorismEvent(pathogen, 3);
        bioTerrorismEvent2 = new BioTerrorismEvent(pathogen2, 25);
        bioTerrorismEvent3 = new BioTerrorismEvent(pathogen3, 67);
    }

    @Test
    public void getPathogen() {
        assertEquals("Hexapox", bioTerrorismEvent.getPathogen().getName());
        assertEquals("Admiral", bioTerrorismEvent2.getPathogen().getName());
        assertEquals("Neurodermantits", bioTerrorismEvent3.getPathogen().getName());
    }

    @Test
    public void getPathogenFalseTest() {
        assertNotEquals("Neurodermantits", bioTerrorismEvent.getPathogen().getName());
        assertNotEquals("Hexapox", bioTerrorismEvent2.getPathogen().getName());
        assertNotEquals("Admiral", bioTerrorismEvent3.getPathogen().getName());
    }

    @Test
    public void getRound() {
        assertEquals(3, bioTerrorismEvent.getRound());
        assertEquals(25, bioTerrorismEvent2.getRound());
        assertEquals(67, bioTerrorismEvent3.getRound());
    }

    @Test
    public void getRoundFalseTest() {
        assertNotEquals(2, bioTerrorismEvent.getRound());
        assertNotEquals(260, bioTerrorismEvent2.getRound());
        assertNotEquals(43, bioTerrorismEvent3.getRound());
    }

    @Test
    public void getType() {
        assertEquals("bioTerrorism", bioTerrorismEvent.getType());
        assertEquals("bioTerrorism", bioTerrorismEvent2.getType());
        assertEquals("bioTerrorism", bioTerrorismEvent3.getType());
    }

    @Test
    public void getTypeFalseTest() {
        assertNotEquals("bioTerror", bioTerrorismEvent.getType());
        assertNotEquals("bioTerorism", bioTerrorismEvent2.getType());
        assertNotEquals("bT", bioTerrorismEvent3.getType());
    }
}