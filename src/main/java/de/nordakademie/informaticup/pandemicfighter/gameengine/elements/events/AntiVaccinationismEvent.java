package de.nordakademie.informaticup.pandemicfighter.gameengine.elements.events;

public class AntiVaccinationismEvent extends Event{
    private int sinceRound;

    public AntiVaccinationismEvent(int sinceRound) {
        super("antiVaccinationism");
        this.sinceRound = sinceRound;
    }

    public int getSinceRound() {
        return sinceRound;
    }
}
