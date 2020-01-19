package de.nordakademie.informaticup.pandemicfighter.gameengine.elements.events;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AntiVaccinationismEventTest {
    private AntiVaccinationismEvent antiVaccinationismEvent;
    private AntiVaccinationismEvent antiVaccinationismEvent2;
    private AntiVaccinationismEvent antiVaccinationismEvent3;

    @Before
    public void setUp() throws Exception {
        antiVaccinationismEvent = new AntiVaccinationismEvent(2);
        antiVaccinationismEvent2 = new AntiVaccinationismEvent(13);
        antiVaccinationismEvent3 = new AntiVaccinationismEvent(56);
    }

    @Test
    public void getSinceRound() {
        assertEquals(2, antiVaccinationismEvent.getSinceRound());
        assertEquals(13, antiVaccinationismEvent2.getSinceRound());
        assertEquals(56, antiVaccinationismEvent3.getSinceRound());
    }

    @Test
    public void getSinceRoundFalseTest() {
        assertNotEquals(1, antiVaccinationismEvent.getSinceRound());
        assertNotEquals(12, antiVaccinationismEvent2.getSinceRound());
        assertNotEquals(66, antiVaccinationismEvent3.getSinceRound());
    }

    @Test
    public void getType() {
        assertEquals("antiVaccinationism", antiVaccinationismEvent.getType());
        assertEquals("antiVaccinationism", antiVaccinationismEvent2.getType());
        assertEquals("antiVaccinationism", antiVaccinationismEvent3.getType());
    }

    @Test
    public void getTypeFalseTest() {
        assertNotEquals("antiVacc", antiVaccinationismEvent.getType());
        assertNotEquals("Vaccinationism", antiVaccinationismEvent2.getType());
        assertNotEquals("VaccinationismAnti", antiVaccinationismEvent3.getType());
    }
}
