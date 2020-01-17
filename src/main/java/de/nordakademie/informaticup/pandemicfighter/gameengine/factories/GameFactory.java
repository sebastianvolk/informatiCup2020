package de.nordakademie.informaticup.pandemicfighter.gameengine.factories;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import de.nordakademie.informaticup.pandemicfighter.gameengine.elements.City;
import de.nordakademie.informaticup.pandemicfighter.gameengine.elements.Game;

import java.util.ArrayList;
import java.util.Map;

public class GameFactory extends ObjectWithEventsFactory {
    private static final String KEY_OUTCOME = "outcome";
    private static final String KEY_ROUND = "round";
    private static final String KEY_POINTS = "points";
    private static final String KEY_CITIES = "cities";
    private static final String KEY_EVENTS = "events";
    private static final String KEY_ERROR = "error";

    public Game createGame(JsonObject jsonGame) {
        Game game = new Game();
        try {
            game.setOutcome(jsonGame.get(KEY_OUTCOME).getAsString());
            game.setRound(jsonGame.get(KEY_ROUND).getAsInt());
            game.setPoints(jsonGame.get(KEY_POINTS).getAsInt());
            game.setCities(createCities(jsonGame.get(KEY_CITIES).getAsJsonObject()));
            if (jsonGame.has(KEY_EVENTS)) {
                game.setEvents(createEvents(jsonGame.get(KEY_EVENTS).getAsJsonArray()));
            }
            if (jsonGame.has(KEY_ERROR)) {
                System.out.println(jsonGame.get(KEY_ERROR).getAsString());
            }
            game.setWorldAveragePopulation(getAverageWorldPopulation(game.getCities()));
        } catch (NullPointerException e) {
            System.out.println("Not all required fields were set.");
            e.printStackTrace();
        }
        return game;
    }

    private ArrayList<City> createCities(JsonObject jsonCities) {
        CityFactory cityFactory = new CityFactory();
        ArrayList<City> cities = new ArrayList<>();
        for (Map.Entry<String, JsonElement> cityEntry : jsonCities.entrySet()) {
            JsonObject jsonCity = cityEntry.getValue().getAsJsonObject();
            cities.add(cityFactory.createCity(jsonCity));
        }
        return cities;
    }

    private double getAverageWorldPopulation(ArrayList<City> cities) {
        double worldP = 0;
        for (City city : cities) {
            worldP += city.getPopulation();

        }
        return worldP / cities.size();
    }
}
