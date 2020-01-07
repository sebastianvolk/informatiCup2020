package de.nordakademie.informaticup.pandemicfighter.gameengine.elements.events;

import de.nordakademie.informaticup.pandemicfighter.gameengine.elements.Pathogen;

public class MedicationAvailableEvent extends Event {
    private Pathogen pathogen;
    private int sinceRound;

    public MedicationAvailableEvent(Pathogen pathogen, int sinceRound) {
        super("medicationAvailable");
        this.pathogen = pathogen;
        this.sinceRound = sinceRound;
    }

    public Pathogen getPathogen() {
        return pathogen;
    }

    public int getSinceRound() {
        return sinceRound;
    }
}
