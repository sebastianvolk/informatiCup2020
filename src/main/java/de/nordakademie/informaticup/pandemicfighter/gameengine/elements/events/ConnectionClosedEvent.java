package de.nordakademie.informaticup.pandemicfighter.gameengine.elements.events;

public class ConnectionClosedEvent extends Event {
    private String city;
    private int sinceRound;
    private int untilRound;

    public ConnectionClosedEvent(String city, int sinceRound, int untilRound) {
        super("connectionClosed");
        this.city = city;
        this.sinceRound = sinceRound;
        this.untilRound = untilRound;
    }

    public String getCity() {
        return city;
    }

    public int getSinceRound() {
        return sinceRound;
    }

    public int getUntilRound() {
        return untilRound;
    }
}
