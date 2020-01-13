package de.nordakademie.informaticup.pandemicfighter.gameengine.elements.events;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class QuarantineEventTest {
    private QuarantineEvent quarantineEvent;
    private QuarantineEvent quarantineEvent2;
    private QuarantineEvent quarantineEvent3;

    @Before
    public void setUp() throws Exception {
        quarantineEvent = new QuarantineEvent(3,8);
        quarantineEvent2 = new QuarantineEvent(52,54);
        quarantineEvent3 = new QuarantineEvent(67,72);
    }

    @Test
    public void getSinceRound() {
        assertEquals(3, quarantineEvent.getSinceRound());
        assertEquals(52, quarantineEvent2.getSinceRound());
        assertEquals(67, quarantineEvent3.getSinceRound());
    }

    @Test
    public void getSinceRoundFalseTest() {
        assertNotEquals(8, quarantineEvent.getSinceRound());
        assertNotEquals(25, quarantineEvent2.getSinceRound());
        assertNotEquals(43, quarantineEvent3.getSinceRound());
    }

    @Test
    public void getUntilRound() {
        assertEquals(8, quarantineEvent.getUntilRound());
        assertEquals(54, quarantineEvent2.getUntilRound());
        assertEquals(72, quarantineEvent3.getUntilRound());
    }

    @Test
    public void getUntilRoundFalseTest() {
        assertNotEquals(9, quarantineEvent.getUntilRound());
        assertNotEquals(52, quarantineEvent2.getUntilRound());
        assertNotEquals(16, quarantineEvent3.getUntilRound());
    }
}