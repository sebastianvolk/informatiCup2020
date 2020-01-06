package de.nordakademie.informaticup.pandemicfighter.factories;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import de.nordakademie.informaticup.pandemicfighter.gameengine.elements.events.Event;

import java.util.ArrayList;

abstract class ObjectWithEventsFactory {
    ArrayList<Event> createEvents(JsonArray jsonEvents) {
        EventFactory eventFactory = new EventFactory();
        ArrayList<Event> events = new ArrayList<>();
        for (JsonElement jsonEventElement : jsonEvents) {
            JsonObject jsonEvent = jsonEventElement.getAsJsonObject();
            Event event = eventFactory.createEvent(jsonEvent);
            if (event != null) {
                events.add(event);
            }
        }
        return events;
    }
}
