package de.nordakademie.informaticup.pandemicfighter.gameengine.elements.events;

import de.nordakademie.informaticup.pandemicfighter.gameengine.elements.Pathogen;

public class VaccineAvailableEvent extends Event {
    private Pathogen pathogen;
    private int sinceRound;

    public VaccineAvailableEvent(Pathogen pathogen, int sinceRound) {
        super("vaccineAvailable");
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
