package de.nordakademie.informaticup.pandemicfighter.factories;

import com.google.gson.JsonObject;
import de.nordakademie.informaticup.pandemicfighter.gameengine.events.Event;
import de.nordakademie.informaticup.pandemicfighter.gameengine.events.OutbreakEvent;

class EventFactory {
    private static final String KEY_TYPE = "type";

    private static final String KEY_PATHOGEN = "pathogen";
    private static final String KEY_PREVALENCE = "prevalence";
    private static final String KEY_SINCE_ROUND = "sinceRound";

    Event createEvent(JsonObject jsonEvent) {
        Event event = null;
        String eventType = jsonEvent.get(KEY_TYPE).getAsString();
        if ("outbreak".equals(eventType)) {
            event = new OutbreakEvent();
            // TODO: set fields
        } else {
            System.out.println("New event type found!");
            System.out.println(jsonEvent.toString());
        }
        return event;
    }
}
