package de.nordakademie.informaticup.pandemicfighter.gameengine.elements.events;

import de.nordakademie.informaticup.pandemicfighter.gameengine.elements.Pathogen;

public class MedicationDeployedEvent extends Event{
    private Pathogen pathogen;
    private int round;

    public MedicationDeployedEvent(int round) {
        super("medicationDeployed");
        this.round = round;
    }

    public Pathogen getPathogen() {
        return pathogen;
    }

    public int getRound() {
        return round;
    }
}
