package de.nordakademie.informaticup.pandemicfighter.gameengine.factories;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import de.nordakademie.informaticup.pandemicfighter.gameengine.elements.Game;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameFactoryTest {
    private JsonObject jsonObject;

    @Before
    public void setUp() throws Exception {
        Gson gson = new Gson();
        jsonObject = new JsonObject();
        jsonObject.addProperty("round", 7);
        jsonObject.addProperty("outcome", "pending");
        jsonObject.addProperty("points", 20);
        JsonObject cityJsonObject = gson.fromJson(
                "{\n" + "\"Hamburg\": {\n" +
                        "            \"name\": \"Hamburg\",\n" +
                        "            \"latitude\": 53.54845,\n" +
                        "            \"longitude\": 9.978514,\n" +
                        "            \"population\": 1822,\n" +
                        "            \"connections\": [\n" +
                        "                \"Kinshasa\",\n" +
                        "                \"Luanda\",\n" +
                        "                \"澳門\",\n" +
                        "                \"Wien\",\n" +
                        "                \"Boston\"\n" +
                        "            ],\n" +
                        "            \"economy\": \"++\",\n" +
                        "            \"government\": \"+\",\n" +
                        "            \"hygiene\": \"+\",\n" +
                        "            \"awareness\": \"+\"\n" +
                        "        }\n}",
                JsonObject.class
        );
        jsonObject.add("cities", cityJsonObject);
        JsonArray events = new JsonArray();
        events.add(gson.fromJson("{\n" +
                "            \"type\": \"pathogenEncountered\",\n" +
                "            \"pathogen\": {\n" +
                "                \"name\": \"N5-10\",\n" +
                "                \"infectivity\": \"+\",\n" +
                "                \"mobility\": \"++\",\n" +
                "                \"duration\": \"o\",\n" +
                "                \"lethality\": \"+\"\n" +
                "            },\n" +
                "            \"round\": 1\n" +
                "        }", JsonObject.class));
        jsonObject.add("events", events);
    }

    @Test
    public void createGameTest() {
        Game game = new GameFactory().createGame(jsonObject);

        assertEquals(7, game.getRound());
        assertEquals("pending", game.getOutcome());
        assertEquals(20, game.getPoints());
        assertEquals(1, game.getCities().size());
        assertEquals(1, game.getEvents().size());
    }
}
