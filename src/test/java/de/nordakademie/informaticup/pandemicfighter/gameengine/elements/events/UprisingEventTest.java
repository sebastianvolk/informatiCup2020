package de.nordakademie.informaticup.pandemicfighter.gameengine.elements.events;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UprisingEventTest {
    private UprisingEvent uprisingEvent;
    private UprisingEvent uprisingEvent2;
    private UprisingEvent uprisingEvent3;

    @Before
    public void setUp() throws Exception {
        uprisingEvent = new UprisingEvent(20, 4);
        uprisingEvent2 = new UprisingEvent(45, 76);
        uprisingEvent3 = new UprisingEvent(7, 90);
    }

    @Test
    public void getParticipants() {
        assertEquals(20, uprisingEvent.getParticipants());
        assertEquals(45, uprisingEvent2.getParticipants());
        assertEquals(7, uprisingEvent3.getParticipants());
    }

    @Test
    public void getParticipantsFalseTest() {
        assertNotEquals(45, uprisingEvent.getParticipants());
        assertNotEquals(54, uprisingEvent2.getParticipants());
        assertNotEquals(6, uprisingEvent3.getParticipants());
    }

    @Test
    public void getSinceRound(){
        assertEquals(4, uprisingEvent.getSinceRound());
        assertEquals(76, uprisingEvent2.getSinceRound());
        assertEquals(90, uprisingEvent3.getSinceRound());
    }

    @Test
    public void getSinceRoundFalseTest(){
        assertNotEquals(5, uprisingEvent.getSinceRound());
        assertNotEquals(67, uprisingEvent2.getSinceRound());
        assertNotEquals(88, uprisingEvent3.getSinceRound());
    }

    @Test
    public void getType() {
        assertEquals("uprising", uprisingEvent.getType());
        assertEquals("uprising", uprisingEvent2.getType());
        assertEquals("uprising", uprisingEvent3.getType());
    }

    @Test
    public void getTypeFalseTest() {
        assertNotEquals("up", uprisingEvent.getType());
        assertNotEquals("rising", uprisingEvent2.getType());
        assertNotEquals("ur", uprisingEvent3.getType());
    }
}