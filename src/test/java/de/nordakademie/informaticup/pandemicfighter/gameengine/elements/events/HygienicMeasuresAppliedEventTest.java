package de.nordakademie.informaticup.pandemicfighter.gameengine.elements.events;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class HygienicMeasuresAppliedEventTest {
    private HygienicMeasuresAppliedEvent hygienicMeasuresAppliedEvent;
    private HygienicMeasuresAppliedEvent hygienicMeasuresAppliedEvent2;
    private HygienicMeasuresAppliedEvent hygienicMeasuresAppliedEvent3;

    @Before
    public void setUp() throws Exception {
        hygienicMeasuresAppliedEvent = new HygienicMeasuresAppliedEvent(6);
        hygienicMeasuresAppliedEvent2 = new HygienicMeasuresAppliedEvent(87);
        hygienicMeasuresAppliedEvent3 = new HygienicMeasuresAppliedEvent(45);
    }

    @Test
    public void getRound() {
        assertEquals(6, hygienicMeasuresAppliedEvent.getRound());
        assertEquals(87, hygienicMeasuresAppliedEvent2.getRound());
        assertEquals(45, hygienicMeasuresAppliedEvent3.getRound());
    }

    @Test
    public void getRoundFalseTest() {
        assertNotEquals(5, hygienicMeasuresAppliedEvent.getRound());
        assertNotEquals(78, hygienicMeasuresAppliedEvent2.getRound());
        assertNotEquals(55, hygienicMeasuresAppliedEvent3.getRound());
    }

    @Test
    public void getType() {
        assertEquals("hygienicMeasuresApplied", hygienicMeasuresAppliedEvent.getType());
        assertEquals("hygienicMeasuresApplied", hygienicMeasuresAppliedEvent2.getType());
        assertEquals("hygienicMeasuresApplied", hygienicMeasuresAppliedEvent3.getType());
    }

    @Test
    public void getTypeFalseTest() {
        assertNotEquals("hygMeasuresApplied", hygienicMeasuresAppliedEvent.getType());
        assertNotEquals("hygienicMeasureApplied", hygienicMeasuresAppliedEvent2.getType());
        assertNotEquals("hygienicApplied", hygienicMeasuresAppliedEvent3.getType());
    }
}
