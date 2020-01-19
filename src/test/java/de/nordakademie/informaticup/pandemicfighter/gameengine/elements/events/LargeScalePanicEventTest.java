package de.nordakademie.informaticup.pandemicfighter.gameengine.elements.events;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LargeScalePanicEventTest {
    private LargeScalePanicEvent largeScalePanicEvent;
    private LargeScalePanicEvent largeScalePanicEvent2;
    private LargeScalePanicEvent largeScalePanicEvent3;

    @Before
    public void setUp() throws Exception {
        largeScalePanicEvent = new LargeScalePanicEvent(6);
        largeScalePanicEvent2 = new LargeScalePanicEvent(56);
        largeScalePanicEvent3 = new LargeScalePanicEvent(78);
    }

    @Test
    public void getSinceRound() {
        assertEquals(6, largeScalePanicEvent.getSinceRound());
        assertEquals(56, largeScalePanicEvent2.getSinceRound());
        assertEquals(78, largeScalePanicEvent3.getSinceRound());
    }

    @Test
    public void getSinceRoundFalseTest() {
        assertNotEquals(60, largeScalePanicEvent.getSinceRound());
        assertNotEquals(65, largeScalePanicEvent2.getSinceRound());
        assertNotEquals(79, largeScalePanicEvent3.getSinceRound());
    }

    @Test
    public void getType() {
        assertEquals("largeScalePanic", largeScalePanicEvent.getType());
        assertEquals("largeScalePanic", largeScalePanicEvent.getType());
        assertEquals("largeScalePanic", largeScalePanicEvent.getType());
    }

    @Test
    public void getTypeFalseTest() {
        assertNotEquals("largeScalePanik", largeScalePanicEvent.getType());
        assertNotEquals("ScalePanicLarge", largeScalePanicEvent.getType());
        assertNotEquals("largePanic", largeScalePanicEvent.getType());
    }
}
