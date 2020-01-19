package de.nordakademie.informaticup.pandemicfighter.gameengine.elements.events;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ElectionsCalledEventTest {
    private ElectionsCalledEvent electionsCalledEvent;
    private ElectionsCalledEvent electionsCalledEvent2;
    private ElectionsCalledEvent electionsCalledEvent3;

    @Before
    public void setUp() throws Exception {
        electionsCalledEvent = new ElectionsCalledEvent(4);
        electionsCalledEvent2 = new ElectionsCalledEvent(104);
        electionsCalledEvent3 = new ElectionsCalledEvent(67);
    }

    @Test
    public void getRound() {
        assertEquals(4, electionsCalledEvent.getRound());
        assertEquals(104, electionsCalledEvent2.getRound());
        assertEquals(67, electionsCalledEvent3.getRound());
    }

    @Test
    public void getRoundFalseTest() {
        assertNotEquals(5, electionsCalledEvent.getRound());
        assertNotEquals(14, electionsCalledEvent2.getRound());
        assertNotEquals(77, electionsCalledEvent3.getRound());
    }

    @Test
    public void getType() {
        assertEquals("electionsCalled", electionsCalledEvent.getType());
        assertEquals("electionsCalled", electionsCalledEvent2.getType());
        assertEquals("electionsCalled", electionsCalledEvent3.getType());
    }

    @Test
    public void getTypeFalseTest() {
        assertNotEquals("elektionsCalled", electionsCalledEvent.getType());
        assertNotEquals("electionsKalled", electionsCalledEvent2.getType());
        assertNotEquals("electionsCall", electionsCalledEvent3.getType());
    }
}
