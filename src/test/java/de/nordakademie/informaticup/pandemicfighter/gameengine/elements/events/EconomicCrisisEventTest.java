package de.nordakademie.informaticup.pandemicfighter.gameengine.elements.events;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class EconomicCrisisEventTest {
    private EconomicCrisisEvent economicCrisisEvent;
    private EconomicCrisisEvent economicCrisisEvent2;
    private EconomicCrisisEvent economicCrisisEvent3;

    @Before
    public void setUp() throws Exception {
        economicCrisisEvent = new EconomicCrisisEvent(3);
        economicCrisisEvent2 = new EconomicCrisisEvent(60);
        economicCrisisEvent3 = new EconomicCrisisEvent(89);
    }

    @Test
    public void getSinceRound() {
        assertEquals(3, economicCrisisEvent.getSinceRound());
        assertEquals(60, economicCrisisEvent2.getSinceRound());
        assertEquals(89, economicCrisisEvent3.getSinceRound());
    }

    @Test
    public void getSinceRoundFalseTest() {
        assertNotEquals(4, economicCrisisEvent.getSinceRound());
        assertNotEquals(600, economicCrisisEvent2.getSinceRound());
        assertNotEquals(80, economicCrisisEvent3.getSinceRound());
    }
}