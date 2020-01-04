package de.nordakademie.informaticup.pandemicfighter.gameengine.events;

import de.nordakademie.informaticup.pandemicfighter.gameengine.Pathogen;

public class PathogenEncounteredEvent extends Event{
    private Pathogen pathogen;
    private int round;

    public PathogenEncounteredEvent(Pathogen pathogen, int round) {
        super("pathogenEncountered");
        this.pathogen = pathogen;
        this.round = round;
    }
}
