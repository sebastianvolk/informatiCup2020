package de.nordakademie.informaticup.pandemicfighter.gameengine.elements.events;

import de.nordakademie.informaticup.pandemicfighter.gameengine.elements.Pathogen;

public class MedicationInDevelopmentEvent extends Event {
    private Pathogen pathogen;
    private int sinceRound;
    private int untilRound;

    public MedicationInDevelopmentEvent(Pathogen pathogen, int sinceRound, int untilRound) {
        super("medicationInDevelopment");
        this.pathogen = pathogen;
        this.sinceRound = sinceRound;
        this.untilRound = untilRound;
    }

    public Pathogen getPathogen() {
        return pathogen;
    }

    public int getSinceRound() {
        return sinceRound;
    }

    public int getUntilRound() {
        return untilRound;
    }
}
