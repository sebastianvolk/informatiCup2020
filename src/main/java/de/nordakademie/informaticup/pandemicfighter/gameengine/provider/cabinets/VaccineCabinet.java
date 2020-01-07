package de.nordakademie.informaticup.pandemicfighter.gameengine.provider.cabinets;

import de.nordakademie.informaticup.pandemicfighter.gameengine.Game;
import de.nordakademie.informaticup.pandemicfighter.gameengine.elements.events.Event;
import de.nordakademie.informaticup.pandemicfighter.gameengine.elements.events.VaccineAvailableEvent;
import de.nordakademie.informaticup.pandemicfighter.gameengine.elements.events.VaccineInDevelopmentEvent;

import java.util.ArrayList;

public class VaccineCabinet {
    private static int currentRound;
    private static ArrayList<VaccineInDevelopmentEvent> vaccineInDevelopmentEvents;
    private static ArrayList<VaccineAvailableEvent> vaccineAvailableEvents;

    public static void initialize(Game game) {
        currentRound = game.getRound();
        vaccineInDevelopmentEvents = new ArrayList<>();
        vaccineAvailableEvents = new ArrayList<>();
        for (Event event : game.getEvents()) {
            if ("vaccineInDevelopment".equals(event.getType())) {
                VaccineInDevelopmentEvent vaccineInDevelopmentEvent = (VaccineInDevelopmentEvent) event;
                vaccineInDevelopmentEvents.add(vaccineInDevelopmentEvent);
            } else if ("vaccineAvailable".equals(event.getType())) {
                VaccineAvailableEvent vaccineAvailableEvent = (VaccineAvailableEvent) event;
                vaccineAvailableEvents.add(vaccineAvailableEvent);
            }
        }
    }

    public static int roundsUntilVaccineIsAvailable(String pathogenName) {
        int rounds = -1;
        boolean found = false;
        for (int i = 0; i < vaccineInDevelopmentEvents.size() && !found; i++) {
            VaccineInDevelopmentEvent vaccineInDevelopmentEvent = vaccineInDevelopmentEvents.get(i);
            if (vaccineInDevelopmentEvent.getPathogen().getName().equals(pathogenName)) {
                rounds = vaccineInDevelopmentEvent.getUntilRound() - currentRound;
                found = true;
            }
        }
        for (int i = 0; i < vaccineAvailableEvents.size() && !found; i++) {
            VaccineAvailableEvent vaccineAvailableEvent = vaccineAvailableEvents.get(i);
            if (vaccineAvailableEvent.getPathogen().getName().equals(pathogenName)) {
                rounds = 0;
                found = true;
            }
        }
        return rounds;
    }

    /*public static ArrayList<Pathogen> getDevelopedVaccines() {
        return null;
    }

    public static ArrayList<Pathogen> getVaccinesInDevelopment() {
        return null;
    }*/
}
