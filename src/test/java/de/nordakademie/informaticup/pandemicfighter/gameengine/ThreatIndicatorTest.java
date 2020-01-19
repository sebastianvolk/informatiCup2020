package de.nordakademie.informaticup.pandemicfighter.gameengine;

import de.nordakademie.informaticup.pandemicfighter.gameengine.elements.City;
import de.nordakademie.informaticup.pandemicfighter.gameengine.elements.Pathogen;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class ThreatIndicatorTest {
    private Pathogen pathogen;
    private Pathogen pathogen2;
    private Pathogen pathogen3;

    private City city;
    private City city2;
    private City city3;

    @Before
    public void setUp() throws Exception {
        pathogen = new Pathogen("Hexapox", 1, 1, 1, 1);
        pathogen2 = new Pathogen("Admiral", 1.1, 0.9, 1, 1.1);
        pathogen3 = new Pathogen("Neurodermantitis", 1, 0.9, 1, 0.9);

        ArrayList<City> cities = new ArrayList<>();
        city = new City("München", 200, 399, new ArrayList<>());
        city2 = new City("Moskau", 250, 499, new ArrayList<>());
        city3 = new City("Berlin", 300, 599, new ArrayList<>());

        city.setEconomy(1);
        city.setGovernment(1);
        city.setHygiene(1);
        city.setAwareness(1);

        city2.setEconomy(0.9);
        city2.setGovernment(0.9);
        city2.setHygiene(0.9);
        city2.setAwareness(0.9);

        city3.setEconomy(1.1);
        city3.setGovernment(0.9);
        city3.setHygiene(1);
        city3.setAwareness(1.1);
    }

    @Test
    public void getPathogenThreatIndicator() {
        assertEquals(1.134, ThreatIndicator.getPathogenThreatIndicator(pathogen), 0.02);
        assertEquals(1.234926, ThreatIndicator.getPathogenThreatIndicator(pathogen2), 0.02);
        assertEquals(0.91852, ThreatIndicator.getPathogenThreatIndicator(pathogen3), 0.02);
    }

    @Test
    public void getPathogenThreatIndicatorFalseTest() {
        assertNotEquals(1.2, ThreatIndicator.getPathogenThreatIndicator(pathogen), 0.02);
        assertNotEquals(1, ThreatIndicator.getPathogenThreatIndicator(pathogen2), 0.02);
        assertNotEquals(0.5, ThreatIndicator.getPathogenThreatIndicator(pathogen3), 0.02);
    }

    @Test
    public void getCityThreatIndicator() {
        assertEquals(1.134, ThreatIndicator.getCityThreatIndicator(city), 0.02);
        assertEquals(0.7440174, ThreatIndicator.getCityThreatIndicator(city2), 0.02);
        assertEquals(1.234926, ThreatIndicator.getCityThreatIndicator(city3), 0.02);
    }

    @Test
    public void getCityThreatIndicatorFalseTest() {
        assertNotEquals(1.2, ThreatIndicator.getCityThreatIndicator(city), 0.02);
        assertNotEquals(0.5, ThreatIndicator.getCityThreatIndicator(city2), 0.02);
        assertNotEquals(1, ThreatIndicator.getCityThreatIndicator(city3), 0.02);
    }
}
