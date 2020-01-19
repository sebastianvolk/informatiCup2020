package de.nordakademie.informaticup.pandemicfighter.gameengine.elements.events;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ConnectionClosedEventTest {
    private ConnectionClosedEvent connectionClosedEvent;
    private ConnectionClosedEvent connectionClosedEvent2;
    private ConnectionClosedEvent connectionClosedEvent3;

    @Before
    public void setUp() throws Exception {
        connectionClosedEvent = new ConnectionClosedEvent("Berlin", 8, 9);
        connectionClosedEvent2 = new ConnectionClosedEvent("Moskau", 78, 80);
        connectionClosedEvent3 = new ConnectionClosedEvent("München", 50, 69);
    }

    @Test
    public void getCity() {
        assertEquals("Berlin", connectionClosedEvent.getCity());
        assertEquals("Moskau", connectionClosedEvent2.getCity());
        assertEquals("München", connectionClosedEvent3.getCity());
    }

    @Test
    public void getCityFalseTest() {
        assertNotEquals("Las Vegas", connectionClosedEvent.getCity());
        assertNotEquals("Sevilla", connectionClosedEvent2.getCity());
        assertNotEquals("Madrid", connectionClosedEvent3.getCity());
    }

    @Test
    public void getSinceRound() {
        assertEquals(8, connectionClosedEvent.getSinceRound());
        assertEquals(78, connectionClosedEvent2.getSinceRound());
        assertEquals(50, connectionClosedEvent3.getSinceRound());
    }

    @Test
    public void getSinceRoundFalseTest() {
        assertNotEquals(3, connectionClosedEvent.getSinceRound());
        assertNotEquals(77, connectionClosedEvent2.getSinceRound());
        assertNotEquals(69, connectionClosedEvent3.getSinceRound());
    }

    @Test
    public void getUntilRound() {
        assertEquals(9, connectionClosedEvent.getUntilRound());
        assertEquals(80, connectionClosedEvent2.getUntilRound());
        assertEquals(69, connectionClosedEvent3.getUntilRound());
    }

    @Test
    public void getUntilRoundFalseTest() {
        assertNotEquals(10, connectionClosedEvent.getUntilRound());
        assertNotEquals(78, connectionClosedEvent2.getUntilRound());
        assertNotEquals(100, connectionClosedEvent3.getUntilRound());
    }

    @Test
    public void getType() {
        assertEquals("connectionClosed", connectionClosedEvent.getType());
        assertEquals("connectionClosed", connectionClosedEvent2.getType());
        assertEquals("connectionClosed", connectionClosedEvent3.getType());
    }

    @Test
    public void getTypeFalseTest() {
        assertNotEquals("connectionClose", connectionClosedEvent.getType());
        assertNotEquals("connClosed", connectionClosedEvent2.getType());
        assertNotEquals("closeConnection", connectionClosedEvent3.getType());
    }
}
