package de.nordakademie.informaticup.pandemicfighter.gameengine.elements.events;

public class UprisingEvent extends Event {
    private int participants;
    private int sinceRound;

    public UprisingEvent(int participants, int sinceRound) {
        super("uprising");
        this.participants = participants;
        this.sinceRound = sinceRound;
    }

    public int getParticipants() {
        return participants;
    }

    public int getSinceRound() {
        return sinceRound;
    }
}
