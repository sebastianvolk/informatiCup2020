package de.nordakademie.informaticup.pandemicfighter.gameengine.elements.events;

import de.nordakademie.informaticup.pandemicfighter.gameengine.elements.Pathogen;

public class VaccineInDevelopmentEvent extends Event {
    private Pathogen pathogen;
    private int sinceRound;
    private int untilRound;

    public VaccineInDevelopmentEvent(Pathogen pathogen, int sinceRound, int untilRound) {
        super("vaccineInDevelopment");
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
