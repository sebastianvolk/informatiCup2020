package de.nordakademie.informaticup.pandemicfighter.gameengine.elements.events;

import de.nordakademie.informaticup.pandemicfighter.gameengine.elements.Pathogen;

public class MedicationDeployedEvent extends Event{
    private Pathogen pathogen;
    private int round;

    public MedicationDeployedEvent(Pathogen pathogen, int round) {
        super("medicationDeployed");
        this.pathogen = pathogen;
        this.round = round;
    }

    public Pathogen getPathogen() {
        return pathogen;
    }

    public int getRound() {
        return round;
    }
}
