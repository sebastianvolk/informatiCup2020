package de.nordakademie.informaticup.pandemicfighter.gameengine;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import de.nordakademie.informaticup.pandemicfighter.gameengine.elements.Game;
import de.nordakademie.informaticup.pandemicfighter.gameengine.factories.GameFactory;
import de.nordakademie.informaticup.pandemicfighter.gameengine.provider.CityProvider;
import de.nordakademie.informaticup.pandemicfighter.gameengine.provider.GameProvider;
import de.nordakademie.informaticup.pandemicfighter.gameengine.provider.cabinets.MedicationCabinet;
import de.nordakademie.informaticup.pandemicfighter.gameengine.provider.cabinets.VaccineCabinet;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameExecutorTest {
    private GameExecutor gameExecutor;
    private Game game;

    @Before
    public void setUp() throws Exception {
        Gson gson = new Gson();
        game = new GameFactory().createGame(gson.fromJson(
                "{\n" +
                        "    \"outcome\": \"pending\",\n" +
                        "    \"round\": 3,\n" +
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
                        "        },\n" +
                        "        {\n" +
                        "            \"type\": \"pathogenEncountered\",\n" +
                        "            \"pathogen\": {\n" +
                        "                \"name\": \"Admiral Trips\",\n" +
                        "                \"infectivity\": \"+\",\n" +
                        "                \"mobility\": \"o\",\n" +
                        "                \"duration\": \"o\",\n" +
                        "                \"lethality\": \"o\"\n" +
                        "            },\n" +
                        "            \"round\": 1\n" +
                        "        },\n" +
                        "        {\n" +
                        "            \"type\": \"pathogenEncountered\",\n" +
                        "            \"pathogen\": {\n" +
                        "                \"name\": \"Shanty\",\n" +
                        "                \"infectivity\": \"+\",\n" +
                        "                \"mobility\": \"o\",\n" +
                        "                \"duration\": \"o\",\n" +
                        "                \"lethality\": \"o\"\n" +
                        "            },\n" +
                        "            \"round\": 1\n" +
                        "        },\n" +
                        "        {\n" +
                        "            \"type\": \"pathogenEncountered\",\n" +
                        "            \"pathogen\": {\n" +
                        "                \"name\": \"Hexapox\",\n" +
                        "                \"infectivity\": \"+\",\n" +
                        "                \"mobility\": \"o\",\n" +
                        "                \"duration\": \"o\",\n" +
                        "                \"lethality\": \"o\"\n" +
                        "            },\n" +
                        "            \"round\": 1\n" +
                        "        },\n" +
                        "        {\n" +
                        "            \"type\": \"medicationInDevelopment\",\n" +
                        "            \"pathogen\": {\n" +
                        "                \"name\": \"Neurodermantotitis\",\n" +
                        "                \"infectivity\": \"+\",\n" +
                        "                \"mobility\": \"o\",\n" +
                        "                \"duration\": \"o\",\n" +
                        "                \"lethality\": \"o\"\n" +
                        "            },\n" +
                        "            \"sinceRound\": 1,\n" +
                        "            \"untilRound\": 7\n" +
                        "        },\n" +
                        "        {\n" +
                        "            \"type\": \"medicationAvailable\",\n" +
                        "            \"pathogen\": {\n" +
                        "                \"name\": \"Hexapox\",\n" +
                        "                \"infectivity\": \"++\",\n" +
                        "                \"mobility\": \"+\",\n" +
                        "                \"duration\": \"-\",\n" +
                        "                \"lethality\": \"++\"\n" +
                        "            },\n" +
                        "            \"sinceRound\": 7\n" +
                        "        },\n" +
                        "        {\n" +
                        "            \"type\": \"vaccineInDevelopment\",\n" +
                        "            \"pathogen\": {\n" +
                        "                \"name\": \"Admiral Trips\",\n" +
                        "                \"infectivity\": \"+\",\n" +
                        "                \"mobility\": \"o\",\n" +
                        "                \"duration\": \"o\",\n" +
                        "                \"lethality\": \"o\"\n" +
                        "            },\n" +
                        "            \"sinceRound\": 1,\n" +
                        "            \"untilRound\": 7\n" +
                        "        },\n" +
                        "        {\n" +
                        "            \"type\": \"vaccineAvailable\",\n" +
                        "            \"pathogen\": {\n" +
                        "                \"name\": \"Shanty\",\n" +
                        "                \"infectivity\": \"++\",\n" +
                        "                \"mobility\": \"+\",\n" +
                        "                \"duration\": \"-\",\n" +
                        "                \"lethality\": \"++\"\n" +
                        "            },\n" +
                        "            \"sinceRound\": 7\n" +
                        "        }" +
                        "    ]\n" +
                        "}",
                JsonObject.class
        ));
        GameProvider.initialize(game);
        CityProvider.setCities(game.getCities());
        MedicationCabinet.initialize(game);
        VaccineCabinet.initialize(game);
        gameExecutor = new GameExecutor(game);
    }

    @Test
    public void getActionWhilePending() {
        JsonObject expectedAction = new JsonObject();
        expectedAction.addProperty("type", "developVaccine");
        expectedAction.addProperty("pathogen", "Neurodermantotitis");
        assertEquals(expectedAction,gameExecutor.getAction());
    }

    @Test
    public void getActionIfLoss() {
        game.setOutcome("loss");
        JsonObject expectedAction = new JsonObject();
        expectedAction.addProperty("type", "endRound");
        assertEquals(expectedAction,gameExecutor.getAction());
    }

    @Test
    public void getActionIfLossFalseTest() {
        game.setOutcome("loss");
        JsonObject expectedAction = new JsonObject();
        expectedAction.addProperty("type", "developVaccine");
        expectedAction.addProperty("pathogen", "Neurodermantotitis");
        assertNotEquals(expectedAction,gameExecutor.getAction());
    }
}
