package de.nordakademie.informaticup.pandemicfighter.gameengine.elements.events;

public class HygienicMeasuresAppliedEvent extends Event {
    private int round;

    public HygienicMeasuresAppliedEvent(int round) {
        super("hygienicMeasuresApplied");
        this.round = round;
    }

    public int getRound() {
        return round;
    }
}
