package de.nordakademie.informaticup.pandemicfighter.gameengine.elements.events;

public class QuarantineEvent extends Event {
    private int sinceRound;
    private int untilRound;

    public QuarantineEvent(int sinceRound, int untilRound) {
        super("quarantine");
        this.sinceRound = sinceRound;
        this.untilRound = untilRound;
    }

    public int getSinceRound() {
        return sinceRound;
    }

    public int getUntilRound() {
        return untilRound;
    }
}
