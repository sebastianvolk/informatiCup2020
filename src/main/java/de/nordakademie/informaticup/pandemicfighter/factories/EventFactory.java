package de.nordakademie.informaticup.pandemicfighter.factories;

import com.google.gson.JsonObject;
import de.nordakademie.informaticup.pandemicfighter.gameengine.elements.Pathogen;
import de.nordakademie.informaticup.pandemicfighter.gameengine.elements.events.*;

class EventFactory {
    private static final String KEY_TYPE = "type";
    private static final String KEY_PATHOGEN = "pathogen";
    private static final String KEY_PREVALENCE = "prevalence";
    private static final String KEY_SINCE_ROUND = "sinceRound";
    private static final String KEY_ROUND = "round";
    private static final String KEY_PARTICIPANTS = "participants";

    Event createEvent(JsonObject jsonEvent) {
        Event event = null;
        String eventType = jsonEvent.get(KEY_TYPE).getAsString();
        if ("outbreak".equals(eventType)) {
            Pathogen pathogen = getPathogen(jsonEvent.get(KEY_PATHOGEN).getAsJsonObject());
            event = new OutbreakEvent(
                    pathogen,
                    jsonEvent.get(KEY_PREVALENCE).getAsDouble(),
                    jsonEvent.get(KEY_SINCE_ROUND).getAsInt()
            );

        } else if ("antiVaccinationism".equals(eventType)) {
            event = new AntiVaccinationismEvent(jsonEvent.get(KEY_SINCE_ROUND).getAsInt());

        } else if ("bioTerrorism".equals(eventType)) {
            event = new BioTerrorismEvent(jsonEvent.get(KEY_ROUND).getAsInt());

        } else if ("economicCrisis".equals(eventType)) {
            event = new EconomicCrisisEvent(jsonEvent.get(KEY_SINCE_ROUND).getAsInt());

        } else if ("largeScalePanic".equals(eventType)) {
            event = new LargeScalePanicEvent(jsonEvent.get(KEY_SINCE_ROUND).getAsInt());

        } else if ("pathogenEncountered".equals(eventType)) {
            Pathogen pathogen = getPathogen(jsonEvent.get(KEY_PATHOGEN).getAsJsonObject());
            event = new PathogenEncounteredEvent(pathogen, jsonEvent.get(KEY_ROUND).getAsInt());

        } else if ("uprising".equals(eventType)) {
            event = new UprisingEvent(jsonEvent.get(KEY_PARTICIPANTS).getAsInt(),
                    jsonEvent.get(KEY_SINCE_ROUND).getAsInt());

        } else {
            System.out.println("New event type found!");
            System.out.println(jsonEvent.toString());
        }
        return event;
    }

    private Pathogen getPathogen(JsonObject jsonPathogen) {
        return new PathogenFactory().createPathogen(jsonPathogen);
    }
}
