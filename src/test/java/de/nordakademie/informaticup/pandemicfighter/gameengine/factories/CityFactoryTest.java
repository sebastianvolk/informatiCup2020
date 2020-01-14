package de.nordakademie.informaticup.pandemicfighter.gameengine.factories;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import de.nordakademie.informaticup.pandemicfighter.gameengine.ValueUtility;
import de.nordakademie.informaticup.pandemicfighter.gameengine.elements.City;
import de.nordakademie.informaticup.pandemicfighter.gameengine.elements.events.Event;
import de.nordakademie.informaticup.pandemicfighter.gameengine.provider.JsonActionProvider;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class CityFactoryTest {
    private JsonObject jsonObject;

    @Before
    public void setUp() throws Exception {
        jsonObject = new JsonObject();
        jsonObject.addProperty("name", "Hamburg");
        jsonObject.addProperty("latitude", 53.548450);
        jsonObject.addProperty("longitude", 9.978514);
        jsonObject.addProperty("population", 1882);
        JsonArray connections = new JsonArray();
        connections.add("Berlin");
        connections.add("Köln");
        connections.add("München");
        jsonObject.add("connections", connections);
        jsonObject.addProperty("economy", "++");
        jsonObject.addProperty("government", "+");
        jsonObject.addProperty("hygiene", "++");
        jsonObject.addProperty("awareness", "+");
        JsonArray events = new JsonArray();
        Gson gson = new Gson();
        events.add(gson.fromJson("{\n" +
                "                    \"type\": \"outbreak\",\n" +
                "                    \"pathogen\": {\n" +
                "                        \"name\": \"N5-10\",\n" +
                "                        \"infectivity\": \"+\",\n" +
                "                        \"mobility\": \"++\",\n" +
                "                        \"duration\": \"o\",\n" +
                "                        \"lethality\": \"+\"\n" +
                "                    },\n" +
                "                    \"prevalence\": 0.6606022584692597,\n" +
                "                    \"sinceRound\": 1\n" +
                "                }", JsonObject.class));
        jsonObject.add("events", events);
    }

    @Test
    public void createCityTest() {
        ArrayList<String> connections = new ArrayList<>();
        connections.add("Berlin");
        connections.add("Köln");
        connections.add("München");
        City expectedCity = new City("Hamburg", 53.548450, 9.978514, connections);
        expectedCity.setPopulation(1882);
        expectedCity.setEconomy(ValueUtility.getVeryHighValueCity());
        expectedCity.setGovernment(ValueUtility.getHighValueCity());
        expectedCity.setHygiene(ValueUtility.getVeryHighValueCity());
        expectedCity.setAwareness(ValueUtility.getHighValueCity());

        City city = new CityFactory().createCity(jsonObject);

        assertEquals(expectedCity.getName(), city.getName());
        assertEquals(expectedCity.getLatitude(), city.getLatitude(), 0.0);
        assertEquals(expectedCity.getLongitude(), city.getLongitude(), 0.0);
        assertEquals(expectedCity.getPopulation(), city.getPopulation());
        assertArrayEquals(expectedCity.getConnections().toArray(), city.getConnections().toArray());
        assertEquals(expectedCity.getEconomy(), city.getEconomy(), 0.0);
        assertEquals(expectedCity.getGovernment(), city.getGovernment(), 0.0);
        assertEquals(expectedCity.getHygiene(), city.getHygiene(), 0.0);
        assertEquals(expectedCity.getAwareness(), city.getAwareness(), 0.0);
        assertEquals(1, city.getEvents().size());
    }
}
