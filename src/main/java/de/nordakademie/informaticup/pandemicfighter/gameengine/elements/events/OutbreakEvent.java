package de.nordakademie.informaticup.pandemicfighter.gameengine.elements.events;

import de.nordakademie.informaticup.pandemicfighter.gameengine.elements.Pathogen;

public class OutbreakEvent extends Event {
    private Pathogen pathogen;
    private double prevalence;
    private int sinceRound;

    public OutbreakEvent(Pathogen pathogen, double prevalence, int sinceRound) {
        super("outbreak");
        this.pathogen = pathogen;
        this.prevalence = prevalence;
        this.sinceRound = sinceRound;
    }
}
