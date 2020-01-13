package de.nordakademie.informaticup.pandemicfighter.gameengine.elements.events;

import de.nordakademie.informaticup.pandemicfighter.gameengine.elements.Pathogen;


public class VaccineDeployedEvent extends Event{
    private Pathogen pathogen;
    private int round;

    public VaccineDeployedEvent(int round, Pathogen pathogen) {
        super("vaccineDeployed");
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
