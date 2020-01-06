package de.nordakademie.informaticup.pandemicfighter.gameengine.provider.cabinets;

import de.nordakademie.informaticup.pandemicfighter.gameengine.Game;

public class VaccineCabinet {
    private static int currentRound;
/*
    private static ArrayList<MedicationInDevelopmentEvent> medicationInDevelopmentEvents;
*/

    public static void initialize(Game game) {
        currentRound = game.getRound();
        /*for (Event event : game.getEvents()) {
            if ("medicationInDevelopment".equals(event.getType())) {
                MedicationInDevelopmentEvent medicationInDevelopmentEvent = (MedicationInDevelopmentEvent) event;
                medicationInDevelopmentEvents.add(medicationInDevelopmentEvent);
            }
        }*/
    }

    /*public static ArrayList<Pathogen> getDevelopedVaccines() {
        return null;
    }

    public static ArrayList<Pathogen> getVaccinesInDevelopment() {
        return null;
    }*/
}
