package de.nordakademie.informaticup.pandemicfighter.gameengine.provider;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import de.nordakademie.informaticup.pandemicfighter.gameengine.elements.Game;
import de.nordakademie.informaticup.pandemicfighter.gameengine.factories.GameFactory;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameProviderTest {
    private Game game;

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
        GameProvider.initialize(game);
    }

    @Test
    public void getGameTest() {
        assertEquals(game, GameProvider.getGame());
    }
}