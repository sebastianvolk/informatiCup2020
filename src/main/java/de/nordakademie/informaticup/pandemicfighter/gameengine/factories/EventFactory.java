package de.nordakademie.informaticup.pandemicfighter.gameengine.factories;

import com.google.gson.JsonObject;
import de.nordakademie.informaticup.pandemicfighter.gameengine.elements.Pathogen;
import de.nordakademie.informaticup.pandemicfighter.gameengine.elements.events.*;

class EventFactory {
    private static final String KEY_TYPE = "type";
    private static final String KEY_PATHOGEN = "pathogen";
    private static final String KEY_PREVALENCE = "prevalence";
    private static final String KEY_SINCE_ROUND = "sinceRound";
    private static final String KEY_UNTIL_ROUND = "untilRound";
    private static final String KEY_ROUND = "round";
    private static final String KEY_PARTICIPANTS = "participants";
    private static final String KEY_CITY = "city";

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
            event = new PathogenEncounteredEvent(
                    pathogen,
                    jsonEvent.get(KEY_ROUND).getAsInt()
            );
        } else if ("uprising".equals(eventType)) {
            event = new UprisingEvent(
                    jsonEvent.get(KEY_PARTICIPANTS).getAsInt(),
                    jsonEvent.get(KEY_SINCE_ROUND).getAsInt()
            );
        } else if ("medicationInDevelopment".equals(eventType)) {
            Pathogen pathogen = getPathogen(jsonEvent.get(KEY_PATHOGEN).getAsJsonObject());
            event = new MedicationInDevelopmentEvent(
                    pathogen,
                    jsonEvent.get(KEY_SINCE_ROUND).getAsInt(),
                    jsonEvent.get(KEY_UNTIL_ROUND).getAsInt()
            );
        } else if ("medicationAvailable".equals(eventType)) {
            Pathogen pathogen = getPathogen(jsonEvent.get(KEY_PATHOGEN).getAsJsonObject());
            event = new MedicationAvailableEvent(
                    pathogen,
                    jsonEvent.get(KEY_SINCE_ROUND).getAsInt()
            );
        } else if ("vaccineInDevelopment".equals(eventType)) {
            Pathogen pathogen = getPathogen(jsonEvent.get(KEY_PATHOGEN).getAsJsonObject());
            event = new VaccineInDevelopmentEvent(
                    pathogen,
                    jsonEvent.get(KEY_SINCE_ROUND).getAsInt(),
                    jsonEvent.get(KEY_UNTIL_ROUND).getAsInt()
            );
        } else if ("vaccineAvailable".equals(eventType)) {
            Pathogen pathogen = getPathogen(jsonEvent.get(KEY_PATHOGEN).getAsJsonObject());
            event = new VaccineAvailableEvent(
                    pathogen,
                    jsonEvent.get(KEY_SINCE_ROUND).getAsInt()
            );
        } else if ("airportClosed".equals(eventType)) {
            event = new AirportClosedEvent(
                    jsonEvent.get(KEY_SINCE_ROUND).getAsInt(),
                    jsonEvent.get(KEY_UNTIL_ROUND).getAsInt()
            );
        } else if ("connectionClosed".equals(eventType)) {
            event = new ConnectionClosedEvent(
                    jsonEvent.get(KEY_CITY).getAsString(),
                    jsonEvent.get(KEY_SINCE_ROUND).getAsInt(),
                    jsonEvent.get(KEY_UNTIL_ROUND).getAsInt()
            );
        } else if ("influenceExerted".equals(eventType)) {
            event = new InfluenceExertedEvent(
                    jsonEvent.get(KEY_ROUND).getAsInt()
            );
        } else if ("electionsCalled".equals(eventType)) {
            event = new ElectionsCalledEvent(
                    jsonEvent.get(KEY_ROUND).getAsInt()
            );
        } else if ("hygienicMeasuresApplied".equals(eventType)) {
            event = new HygienicMeasuresAppliedEvent(
                    jsonEvent.get(KEY_ROUND).getAsInt()
            );
        } else if ("quarantine".equals(eventType)) {
            event = new QuarantineEvent(
                    jsonEvent.get(KEY_SINCE_ROUND).getAsInt(),
                    jsonEvent.get(KEY_UNTIL_ROUND).getAsInt()
            );
        } else if ("campaignLaunched".equals(eventType)) {
            event = new CampaignLaunchedEvent(
                    jsonEvent.get(KEY_ROUND).getAsInt()
            );
        } else if ("medicationDeployed".equals(eventType)) {
            event = new MedicationDeployedEvent(
                    jsonEvent.get(KEY_ROUND).getAsInt()
            );
        }else if ("vaccineDeployed".equals(eventType)) {
            Pathogen pathogen = getPathogen(jsonEvent.get(KEY_PATHOGEN).getAsJsonObject());
            event = new VaccineDeployedEvent(
                    jsonEvent.get(KEY_ROUND).getAsInt(),
                    pathogen
            );
        }else {
            System.out.println("New event type found!");
            System.out.println(jsonEvent.toString());
        }
        return event;
    }

    private Pathogen getPathogen(JsonObject jsonPathogen) {
        return new PathogenFactory().createPathogen(jsonPathogen);
    }
}
