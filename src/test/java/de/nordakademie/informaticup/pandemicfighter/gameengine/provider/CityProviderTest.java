package de.nordakademie.informaticup.pandemicfighter.gameengine.provider;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import de.nordakademie.informaticup.pandemicfighter.gameengine.elements.City;
import de.nordakademie.informaticup.pandemicfighter.gameengine.elements.Game;
import de.nordakademie.informaticup.pandemicfighter.gameengine.factories.GameFactory;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class CityProviderTest {
    private Game game;
    private City hamburg;
    private City luebeck;
    private City honolulu;

    @Before
    public void setUp() throws Exception {
        Gson gson = new Gson();
        game = new GameFactory().createGame(gson.fromJson(
                "{\n" +
                        "    \"outcome\": \"pending\",\n" +
                        "    \"round\": 1,\n" +
                        "    \"points\": 40,\n" +
                        "    \"cities\": {\n" +
                        "        \"Hamburg\": {\n" +
                        "            \"name\": \"Hamburg\",\n" +
                        "            \"latitude\": 53.54845,\n" +
                        "            \"longitude\": 9.978514,\n" +
                        "            \"population\": 1822,\n" +
                        "            \"connections\": [\n" +
                        "                \"Honolulu\",\n" +
                        "                \"Lübeck\"\n" +
                        "            ],\n" +
                        "            \"economy\": \"++\",\n" +
                        "            \"government\": \"+\",\n" +
                        "            \"hygiene\": \"+\",\n" +
                        "            \"awareness\": \"+\"\n" +
                        "        },\n" +
                        "        \"Honolulu\": {\n" +
                        "            \"name\": \"Honolulu\",\n" +
                        "            \"latitude\": 21.305311,\n" +
                        "            \"longitude\": -157.857097,\n" +
                        "            \"population\": 993,\n" +
                        "            \"connections\": [\n" +
                        "                \"Hamburg\"\n" +
                        "            ],\n" +
                        "            \"economy\": \"++\",\n" +
                        "            \"government\": \"++\",\n" +
                        "            \"hygiene\": \"++\",\n" +
                        "            \"awareness\": \"++\"\n" +
                        "        },\n" +
                        "        \"Lübeck\": {\n" +
                        "            \"name\": \"Lübeck\",\n" +
                        "            \"latitude\": 53.8654673,\n" +
                        "            \"longitude\": 10.6865593,\n" +
                        "            \"population\": 217,\n" +
                        "            \"connections\": [\n" +
                        "                \"Honolulu\"\n" +
                        "            ],\n" +
                        "            \"economy\": \"o\",\n" +
                        "            \"government\": \"+\",\n" +
                        "            \"hygiene\": \"o\",\n" +
                        "            \"awareness\": \"o\"\n" +
                        "        }\n" +
                        "    },\n" +
                        "    \"events\": [\n" +
                        "        {\n" +
                        "            \"type\": \"pathogenEncountered\",\n" +
                        "            \"pathogen\": {\n" +
                        "                \"name\": \"Neurodermantotitis\",\n" +
                        "                \"infectivity\": \"+\",\n" +
                        "                \"mobility\": \"o\",\n" +
                        "                \"duration\": \"o\",\n" +
                        "                \"lethality\": \"o\"\n" +
                        "            },\n" +
                        "            \"round\": 1\n" +
                        "        }\n" +
                        "    ]\n" +
                        "}",
                JsonObject.class
        ));
        CityProvider.setCities(game.getCities());
        ArrayList<City> cities = game.getCities();
        for (City city : cities) {
            if (city.getName().equals("Hamburg")) {
                hamburg = city;
            } else if (city.getName().equals("Lübeck")) {
                luebeck = city;
            } else if (city.getName().equals("Honolulu")) {
                honolulu = city;
            }
        }
    }

    @Test
    public void getCitiesTest() {
        ArrayList<City> cities = CityProvider.getCities();

        assertArrayEquals(game.getCities().toArray(), cities.toArray());
    }

    @Test
    public void getConnectedCitiesOneTest() {
        ArrayList<City> connectedCities = CityProvider.getConnectedCities(luebeck);

        assertEquals(1, connectedCities.size());
        assertTrue(connectedCities.contains(honolulu));
    }

    @Test
    public void getConnectedCitiesTwoTest() {
        ArrayList<City> connectedCities = CityProvider.getConnectedCities(hamburg);

        assertEquals(2, connectedCities.size());
        assertTrue(connectedCities.contains(luebeck));
        assertTrue(connectedCities.contains(honolulu));
    }

    @Test
    public void getCitiesWhichHaveConnectionToGivenCityOneTest() {
        ArrayList<City> citiesWhichHaveConnectionToGivenCity = CityProvider.getCitiesWhichHaveConnectionToGivenCity(luebeck);

        assertEquals(1, citiesWhichHaveConnectionToGivenCity.size());
        assertTrue(citiesWhichHaveConnectionToGivenCity.contains(hamburg));
    }

    @Test
    public void getCitiesWhichHaveConnectionToGivenCityTwoTest() {
        ArrayList<City> citiesWhichHaveConnectionToGivenCity = CityProvider.getCitiesWhichHaveConnectionToGivenCity(honolulu);

        assertEquals(2, citiesWhichHaveConnectionToGivenCity.size());
        assertTrue(citiesWhichHaveConnectionToGivenCity.contains(hamburg));
        assertTrue(citiesWhichHaveConnectionToGivenCity.contains(luebeck));
    }

    @Test
    public void getNearCitiesZeroTest() {
        ArrayList<City> nearCities = CityProvider.getNearCities(honolulu);

        assertTrue(nearCities.isEmpty());
    }

    @Test
    public void getNearCitiesHamburgTest() {
        ArrayList<City> nearCities = CityProvider.getNearCities(hamburg);

        assertEquals(1, nearCities.size());
        assertTrue(nearCities.contains(luebeck));
    }

    @Test
    public void getNearCitiesLuebeckTest() {
        ArrayList<City> nearCities = CityProvider.getNearCities(luebeck);

        assertEquals(1, nearCities.size());
        assertTrue(nearCities.contains(hamburg));
    }
}