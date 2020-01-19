package de.nordakademie.informaticup.pandemicfighter.gameengine.elements.events;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class InfluenceExertedEventTest {
    private InfluenceExertedEvent influenceExertedEvent;
    private InfluenceExertedEvent influenceExertedEvent2;
    private InfluenceExertedEvent influenceExertedEvent3;

    @Before
    public void setUp() throws Exception {
        influenceExertedEvent = new InfluenceExertedEvent(9);
        influenceExertedEvent2 = new InfluenceExertedEvent(46);
        influenceExertedEvent3 = new InfluenceExertedEvent(14);
    }

    @Test
    public void getRound() {
        assertEquals(9, influenceExertedEvent.getRound());
        assertEquals(46, influenceExertedEvent2.getRound());
        assertEquals(14, influenceExertedEvent3.getRound());
    }

    @Test
    public void getRoundFalseTest() {
        assertNotEquals(90, influenceExertedEvent.getRound());
        assertNotEquals(64, influenceExertedEvent2.getRound());
        assertNotEquals(15, influenceExertedEvent3.getRound());
    }

    @Test
    public void getType() {
        assertEquals("influenceExerted", influenceExertedEvent.getType());
        assertEquals("influenceExerted", influenceExertedEvent.getType());
        assertEquals("influenceExerted", influenceExertedEvent.getType());
    }

    @Test
    public void getTypeFalseTest() {
        assertNotEquals("influenceExertet", influenceExertedEvent.getType());
        assertNotEquals("infExerted", influenceExertedEvent.getType());
        assertNotEquals("exertInfluence", influenceExertedEvent.getType());
    }
}
