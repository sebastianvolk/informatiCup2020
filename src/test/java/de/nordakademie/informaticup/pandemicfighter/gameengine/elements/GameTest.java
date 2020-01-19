package de.nordakademie.informaticup.pandemicfighter.gameengine.elements;

import de.nordakademie.informaticup.pandemicfighter.gameengine.elements.events.Event;
import de.nordakademie.informaticup.pandemicfighter.gameengine.elements.events.MedicationAvailableEvent;
import de.nordakademie.informaticup.pandemicfighter.gameengine.elements.events.VaccineAvailableEvent;
import de.nordakademie.informaticup.pandemicfighter.gameengine.elements.events.VaccineInDevelopmentEvent;
import org.hamcrest.core.IsEqual;
import org.hamcrest.core.IsNot;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class GameTest {
    private Game game;
    private ArrayList<Event> events;
    private Pathogen pathogen;
    private VaccineInDevelopmentEvent vaccineInDevelopmentEvent;
    private ArrayList<City> cities;

    @Before
    public void setUp() throws Exception {
        game = new Game();
        game.setOutcome("pending");
        game.setPoints(20);
        game.setRound(3);
        cities = new ArrayList<>();
        City city1 = new City("Moskau", 2, 3, new ArrayList<String>());
        City city2 = new City("Berlin", 5, 6, new ArrayList<String>());
        cities.add(city1);
        cities.add(city2);
        game.setCities(cities);
        game.setWorldAveragePopulation(122.45);
        pathogen = new Pathogen("Hexapox", 1, 1, 1, 1);
        events = new ArrayList<>();
        MedicationAvailableEvent medicationAvailableEvent = new MedicationAvailableEvent(pathogen, 4);
        vaccineInDevelopmentEvent = new VaccineInDevelopmentEvent(pathogen, 4, 11);
        events.add(medicationAvailableEvent);
        events.add(vaccineInDevelopmentEvent);
        game.setEvents(events);
    }

    @Test
    public void getEventsByType() {
        ArrayList<Event> vaccineInDevelopmentEventsInCity = new ArrayList<>();
        vaccineInDevelopmentEventsInCity.add(vaccineInDevelopmentEvent);
        assertArrayEquals(vaccineInDevelopmentEventsInCity.toArray(), game.getEventsByType("vaccineInDevelopment").toArray());
    }

    @Test
    public void getEventsByTypeFalseTest() {
        ArrayList<Event> vaccineAvailableEventsInCity = new ArrayList<>();
        VaccineAvailableEvent vaccineAvailableEvent = new VaccineAvailableEvent(pathogen,11);
        vaccineAvailableEventsInCity.add(vaccineAvailableEvent);
        Assert.assertThat(vaccineAvailableEventsInCity, IsNot.not(IsEqual.equalTo(game.getEventsByType("vaccineAvailable"))));
    }

    @Test
    public void getEvents() {
        assertArrayEquals(events.toArray(), game.getEvents().toArray());
    }

    @Test
    public void getEventsFalseTest() {
        ArrayList<Event> eventsInCity = new ArrayList<>();
        MedicationAvailableEvent medicationAvailableEvent = new MedicationAvailableEvent(pathogen, 4);
        eventsInCity.add(medicationAvailableEvent);
        Assert.assertThat(eventsInCity, IsNot.not(IsEqual.equalTo(game.getEvents())));
    }

    @Test
    public void getOutcome() {
        assertEquals("pending", game.getOutcome());
    }

    @Test
    public void getOutcomeFalseTest() {
        assertNotEquals("loss", game.getOutcome());
    }

    @Test
    public void getRound() {
        assertEquals(3, game.getRound());
    }

    @Test
    public void getRoundFalseTest() {
        assertNotEquals(2, game.getRound());
    }

    @Test
    public void getPoints() {
        assertEquals(20, game.getPoints());
    }

    @Test
    public void getPointsFalseTest() {
        assertNotEquals(22, game.getPoints());
    }

    @Test
    public void getCities() {
        assertArrayEquals(cities.toArray(), game.getCities().toArray());
    }

    @Test
    public void getWorldAveragePopulation() {
        assertEquals(122.45, game.getWorldAveragePopulation(), 0.0);
    }
}
