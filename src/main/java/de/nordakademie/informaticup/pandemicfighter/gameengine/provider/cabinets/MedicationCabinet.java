package de.nordakademie.informaticup.pandemicfighter.gameengine.provider.cabinets;

import de.nordakademie.informaticup.pandemicfighter.gameengine.elements.Game;
import de.nordakademie.informaticup.pandemicfighter.gameengine.elements.events.Event;
import de.nordakademie.informaticup.pandemicfighter.gameengine.elements.events.MedicationAvailableEvent;
import de.nordakademie.informaticup.pandemicfighter.gameengine.elements.events.MedicationInDevelopmentEvent;

import java.util.ArrayList;

public class MedicationCabinet {
    private static int currentRound;
    private static ArrayList<MedicationInDevelopmentEvent> medicationInDevelopmentEvents;
    private static ArrayList<MedicationAvailableEvent> medicationAvailableEvents;

    public static void initialize(Game game) {
        currentRound = game.getRound();
        medicationInDevelopmentEvents = new ArrayList<>();
        medicationAvailableEvents = new ArrayList<>();
        for (Event event : game.getEvents()) {
            if ("medicationInDevelopment".equals(event.getType())) {
                MedicationInDevelopmentEvent medicationInDevelopmentEvent = (MedicationInDevelopmentEvent) event;
                medicationInDevelopmentEvents.add(medicationInDevelopmentEvent);
            } else if ("medicationAvailable".equals(event.getType())) {
                MedicationAvailableEvent medicationAvailableEvent = (MedicationAvailableEvent) event;
                medicationAvailableEvents.add(medicationAvailableEvent);
            }
        }
    }

    public static int roundsUntilMedicationIsAvailable(String pathogenName) {
        int rounds = -1;
        boolean found = false;
        for (int i = 0; i < medicationInDevelopmentEvents.size() && !found; i++) {
            MedicationInDevelopmentEvent medicationInDevelopmentEvent = medicationInDevelopmentEvents.get(i);
            if (medicationInDevelopmentEvent.getPathogen().getName().equals(pathogenName)) {
                rounds = medicationInDevelopmentEvent.getUntilRound() - currentRound;
                found = true;
            }
        }
        for (int i = 0; i < medicationAvailableEvents.size() && !found; i++) {
            MedicationAvailableEvent medicationAvailableEvent = medicationAvailableEvents.get(i);
            if (medicationAvailableEvent.getPathogen().getName().equals(pathogenName)) {
                rounds = 0;
                found = true;
            }
        }
        return rounds;
    }

    /*public static ArrayList<Pathogen> getDevelopedMedications() {
        return null;
    }

    public static ArrayList<Pathogen> getMedicationsInDevelopment() {
        return null;
    }*/
}
