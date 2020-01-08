package de.nordakademie.informaticup.pandemicfighter.gameengine.elements.events;

public class ElectionsCalledEvent extends Event {
    private int round;

    public ElectionsCalledEvent(int round) {
        super("electionsCalled");
        this.round = round;
    }

    public int getRound() {
        return round;
    }
}
