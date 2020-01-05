package de.nordakademie.informaticup.pandemicfighter.gameengine.elements.events;

import de.nordakademie.informaticup.pandemicfighter.gameengine.elements.Pathogen;

public class PathogenEncounteredEvent extends Event{
    private Pathogen pathogen;
    private int round;

    public PathogenEncounteredEvent(Pathogen pathogen, int round) {
        super("pathogenEncountered");
        this.pathogen = pathogen;
        this.round = round;
    }
}
