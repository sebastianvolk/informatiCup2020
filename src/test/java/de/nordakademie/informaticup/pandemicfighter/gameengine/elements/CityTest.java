package de.nordakademie.informaticup.pandemicfighter.gameengine.elements;

import de.nordakademie.informaticup.pandemicfighter.gameengine.elements.events.*;
import org.hamcrest.core.IsEqual;
import org.hamcrest.core.IsNot;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class CityTest {
    private City city;
    private Pathogen pathogen;
    private Pathogen pathogen2;
    private OutbreakEvent outbreakEvent;
    private ArrayList<Event> events;

    @Before
    public void setUp() throws Exception {
        pathogen = new Pathogen("Hexapox", 1, 1, 1, 1);
        pathogen2 = new Pathogen("Admiral", 1.1, 0.9, 1, 1.1);
        outbreakEvent = new OutbreakEvent(pathogen, 0.3, 4);
        EconomicCrisisEvent economicCrisisEvent = new EconomicCrisisEvent(16);
        events = new ArrayList<>();
        events.add(outbreakEvent);
        events.add(economicCrisisEvent);
        ArrayList<String> conections = new ArrayList<>();
        conections.add("Berlin");
        conections.add("Wien");
        conections.add("Venedig");
        city = new City("Moskau", 320, 230, conections);
        city.setPopulation(120);
        city.setEconomy(1);
        city.setGovernment(0.95);
        city.setHygiene(0.9);
        city.setAwareness(1.1);
        city.setEvents(events);
    }

    @Test
    public void getEventsByType() {
        ArrayList<Event> outbreakEvents = new ArrayList<>();
        outbreakEvents.add(outbreakEvent);
        assertArrayEquals(outbreakEvents.toArray(), city.getEventsByType("outbreak").toArray());
    }

    @Test
    public void getEventsByTypeFalseTest() {
        ArrayList<Event> uprisingEvents = new ArrayList<>();
        UprisingEvent uprisingEvent = new UprisingEvent(23, 3);
        uprisingEvents.add(uprisingEvent);
        Assert.assertThat(uprisingEvents, IsNot.not(IsEqual.equalTo(city.getEventsByType("uprising"))));
    }

    @Test
    public void getEvents() {
        assertArrayEquals(events.toArray(), city.getEvents().toArray());
    }

    @Test
    public void getEventsFalseTest() {
        ArrayList<Event> eventNotinCity = new ArrayList<>();
        AntiVaccinationismEvent antiVaccinationismEvent = new AntiVaccinationismEvent(4);
        Assert.assertThat(eventNotinCity, IsNot.not(IsEqual.equalTo(city.getEvents())));
    }

    @Test
    public void getName() {
        assertEquals("Moskau", city.getName());
    }

    @Test
    public void getNameFalseTest() {
        assertNotEquals("Dresden", city.getName());
    }

    @Test
    public void getLatitude() {
        assertEquals(320, city.getLatitude(),1);
    }

    @Test
    public void getLatitudeFalseTest() {
        assertNotEquals(321, city.getLatitude(),0.5);
    }

    @Test
    public void getLongitude() {
        assertEquals(230, city.getLongitude(),1);
    }

    @Test
    public void getLongitudeFalseTest() {
        assertNotEquals(229, city.getLongitude(),0.5);
    }

    @Test
    public void getPopulation() {
        assertEquals(120, city.getPopulation());
    }

    @Test
    public void getPopulationFalseTest() {
        assertNotEquals(121, city.getPopulation());
    }

    @Test
    public void getConnections() {
        ArrayList<String> connectionsThatShouldBe = new ArrayList<>();
        connectionsThatShouldBe.add("Berlin");
        connectionsThatShouldBe.add("Wien");
        connectionsThatShouldBe.add("Venedig");
        assertArrayEquals(connectionsThatShouldBe.toArray(), city.getConnections().toArray());
    }

    @Test
    public void getConnectionsFalseTest() {
        ArrayList<String> connectionsThatShouldBe = new ArrayList<>();
        connectionsThatShouldBe.add("Berlin");
        connectionsThatShouldBe.add("Hamburg");
        connectionsThatShouldBe.add("Venedig");
        Assert.assertThat(connectionsThatShouldBe, IsNot.not(IsEqual.equalTo(city.getConnections())));
    }

    @Test
    public void getEconomy() {
        assertEquals(1, city.getEconomy(), 0.01);
    }

    @Test
    public void getEconomyFalseTest() {
        assertNotEquals(1.1, city.getEconomy(), 0.01);
    }

    @Test
    public void getGovernment() {
        assertEquals(0.95, city.getGovernment(), 0.01);
    }

    @Test
    public void getGovernmentFalseTest() {
        assertNotEquals(1, city.getAwareness(), 0.01);
    }

    @Test
    public void getHygiene() {
        assertEquals(0.9, city.getHygiene(), 0.01);
    }

    @Test
    public void getHygieneFalseTest() {
        assertNotEquals(0.8, city.getHygiene(), 0.01);
    }

    @Test
    public void getAwareness() {
        assertEquals(1.1, city.getAwareness(), 0.01);
    }

    @Test
    public void getAwarenessFalseTest() {
        assertNotEquals(1, city.getAwareness(), 0.01);
    }

    @Test
    public void getPathogensInCity() {
        ArrayList<Pathogen> pathogensInCity = new ArrayList<>();
        pathogensInCity.add(pathogen);
        assertArrayEquals(pathogensInCity.toArray(), city.getPathogensInCity().toArray());
    }

    @Test
    public void getPathogensInCityFalseTest() {
        ArrayList<Pathogen> pathogensInCity = new ArrayList<>();
        pathogensInCity.add(pathogen2);
        Assert.assertThat(pathogensInCity, IsNot.not(IsEqual.equalTo(city.getPathogensInCity())));
    }

    @Test
    public void hasCityPathogenOutbreak() {
        assertTrue(city.hasCityPathogenOutbreak(pathogen));
    }

    @Test
    public void hasCityPathogenOutbreakFalseTest() {
        assertFalse(city.hasCityPathogenOutbreak(pathogen2));
    }

    @Test
    public void getCityOutBreakEvent() {
        assertEquals(outbreakEvent, city.getCityOutBreakEvent(pathogen));
    }
}
