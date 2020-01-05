package de.nordakademie.informaticup.pandemicfighter.factories;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import de.nordakademie.informaticup.pandemicfighter.gameengine.elements.City;

import java.util.ArrayList;

class CityFactory extends ObjectWithEventsFactory {
    private static final String KEY_NAME = "name";
    private static final String KEY_LATITUDE = "latitude";
    private static final String KEY_LONGITUDE = "longitude";
    private static final String KEY_POPULATION = "population";
    private static final String KEY_CONNECTIONS = "connections";
    private static final String KEY_EVENTS = "events";
    private static final String KEY_ECONOMY = "economy";
    private static final String KEY_GOVERNMENT = "government";
    private static final String KEY_HYGIENE = "hygiene";
    private static final String KEY_AWARENESS = "awareness";

    City createCity(JsonObject jsonCity) {
        City city = new City(
                jsonCity.get(KEY_NAME).getAsString(),
                jsonCity.get(KEY_LATITUDE).getAsDouble(),
                jsonCity.get(KEY_LONGITUDE).getAsDouble(),
                createConnections(jsonCity.get(KEY_CONNECTIONS).getAsJsonArray())
        );
        city.setPopulation(jsonCity.get(KEY_POPULATION).getAsInt());

        if (jsonCity.has(KEY_EVENTS)) {
            city.setEvents(createEvents(jsonCity.get(KEY_EVENTS).getAsJsonArray()));
        }

        city.setEconomy(jsonCity.get(KEY_ECONOMY).getAsString());
        city.setGovernment(jsonCity.get(KEY_GOVERNMENT).getAsString());
        city.setHygiene(jsonCity.get(KEY_HYGIENE).getAsString());
        city.setAwareness(jsonCity.get(KEY_AWARENESS).getAsString());
        return city;
    }

    private ArrayList<String> createConnections(JsonArray jsonConnections) {
        return new Gson().fromJson(
                jsonConnections,
                new TypeToken<ArrayList<String>>() {}.getType()
        );
    }
}
