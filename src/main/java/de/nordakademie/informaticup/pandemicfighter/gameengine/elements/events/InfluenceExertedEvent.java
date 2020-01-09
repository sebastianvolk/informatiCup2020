package de.nordakademie.informaticup.pandemicfighter.gameengine.elements.events;

public class InfluenceExertedEvent extends Event {
    private int round;

    public InfluenceExertedEvent(int round) {
        super("influenceExerted");
        this.round = round;
    }

    public int getRound() {
        return round;
    }
}
