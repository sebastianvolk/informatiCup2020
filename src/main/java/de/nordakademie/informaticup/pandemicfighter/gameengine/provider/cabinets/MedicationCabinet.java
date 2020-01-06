package de.nordakademie.informaticup.pandemicfighter.gameengine.provider.cabinets;

import de.nordakademie.informaticup.pandemicfighter.gameengine.Game;
import de.nordakademie.informaticup.pandemicfighter.gameengine.elements.events.Event;
import de.nordakademie.informaticup.pandemicfighter.gameengine.elements.events.MedicationInDevelopmentEvent;

import java.util.ArrayList;

public class MedicationCabinet {
    private static int currentRound;
    private static ArrayList<MedicationInDevelopmentEvent> medicationInDevelopmentEvents;

    public static void initialize(Game game) {
        currentRound = game.getRound();
        medicationInDevelopmentEvents = new ArrayList<>();
        for (Event event : game.getEvents()) {
            if ("medicationInDevelopment".equals(event.getType())) {
                MedicationInDevelopmentEvent medicationInDevelopmentEvent = (MedicationInDevelopmentEvent) event;
                medicationInDevelopmentEvents.add(medicationInDevelopmentEvent);
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
        return rounds;
    }

    /*public static ArrayList<Pathogen> getDevelopedMedications() {
        return null;
    }

    public static ArrayList<Pathogen> getMedicationsInDevelopment() {
        return null;
    }*/
}
