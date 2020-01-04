package de.nordakademie.informaticup.pandemicfighter.gameengine.events;

import de.nordakademie.informaticup.pandemicfighter.gameengine.Pathogen;

public class bioTerrorismEvent extends Event {
    private Pathogen pathogen;
    private int round;

    public bioTerrorismEvent(int round) {
        super("bioTerrorism");
        this.round = round;
    }
}
