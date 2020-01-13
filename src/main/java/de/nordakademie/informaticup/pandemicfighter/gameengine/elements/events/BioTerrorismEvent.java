package de.nordakademie.informaticup.pandemicfighter.gameengine.elements.events;

import de.nordakademie.informaticup.pandemicfighter.gameengine.elements.Pathogen;

public class BioTerrorismEvent extends Event {
    private Pathogen pathogen;
    private int round;

    public BioTerrorismEvent(int round, Pathogen pathogen) {
        super("bioTerrorism");
        this.round = round;
        this.pathogen = pathogen;
    }

    public Pathogen getPathogen() {
        return pathogen;
    }

    public int getRound() {
        return round;
    }
}
