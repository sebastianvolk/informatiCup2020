package de.nordakademie.informaticup.pandemicfighter.gameengine.elements;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PathogenTest {
    private Pathogen pathogen;

    @Before
    public void setUp() throws Exception {
        pathogen = new Pathogen("Hexapox", 1, 0.9, 1.1, 1);
    }

    @Test
    public void getName() {
        assertEquals("Hexapox", pathogen.getName());
    }

    @Test
    public void getNameFalseTest() {
        assertNotEquals("Hexadox", pathogen.getName());
    }

    @Test
    public void getInfectivity() {
        assertEquals(1, pathogen.getInfectivity(), 0.01);
    }

    @Test
    public void getInfectivityFalseTest() {
        assertNotEquals(1.05, pathogen.getInfectivity(), 0.01);
    }

    @Test
    public void getMobility() {
        assertEquals(0.9, pathogen.getMobility(), 0.01);
    }

    @Test
    public void getMobilityFalseTest() {
        assertNotEquals(1, pathogen.getMobility(), 0.01);
    }

    @Test
    public void getDuration() {
        assertEquals(1.1, pathogen.getDuration(), 0.01);
    }

    @Test
    public void getDurationFalseTest() {
        assertNotEquals(1, pathogen.getDuration(), 0.01);
    }

    @Test
    public void getLethality() {
        assertEquals(1, pathogen.getLethality(), 0.01);
    }

    @Test
    public void getLethalityFalseTest() {
        assertNotEquals(0.9, pathogen.getLethality(), 0.01);
    }
}
