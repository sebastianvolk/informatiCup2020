package de.nordakademie.informaticup.pandemicfighter.gameengine.elements.events;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AirportClosedEventTest {
    private AirportClosedEvent airportClosedEvent;
    private AirportClosedEvent airportClosedEvent2;
    private AirportClosedEvent airportClosedEvent3;

    @Before
    public void setUp() throws Exception {
        airportClosedEvent = new AirportClosedEvent(10, 16);
        airportClosedEvent2 = new AirportClosedEvent(7, 8);
        airportClosedEvent3 = new AirportClosedEvent(46, 60);
    }

    @Test
    public void getSinceRound() {
        assertEquals(10, airportClosedEvent.getSinceRound());
        assertEquals(7, airportClosedEvent2.getSinceRound());
        assertEquals(46, airportClosedEvent3.getSinceRound());
    }

    @Test
    public void getSinceRoundFalseTest() {
        assertNotEquals(2, airportClosedEvent.getSinceRound());
        assertNotEquals(70, airportClosedEvent2.getSinceRound());
        assertNotEquals(4, airportClosedEvent3.getSinceRound());
    }

    @Test
    public void getUntilRound() {
        assertEquals(16, airportClosedEvent.getUntilRound());
        assertEquals(8, airportClosedEvent2.getUntilRound());
        assertEquals(60, airportClosedEvent3.getUntilRound());
    }

    @Test
    public void getUntilRoundFalseTest() {
        assertNotEquals(160, airportClosedEvent.getUntilRound());
        assertNotEquals(12, airportClosedEvent2.getUntilRound());
        assertNotEquals(6, airportClosedEvent3.getUntilRound());
    }
}