package de.nordakademie.informaticup.pandemicfighter.gameengine.elements.events;

public class AirportClosedEvent extends Event {
    private int sinceRound;
    private int untilRound;

    public AirportClosedEvent(int sinceRound, int untilRound) {
        super("airportClosed");
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
