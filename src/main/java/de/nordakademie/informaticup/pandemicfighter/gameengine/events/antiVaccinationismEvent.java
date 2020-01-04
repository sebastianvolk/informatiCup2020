package de.nordakademie.informaticup.pandemicfighter.gameengine.events;

public class antiVaccinationismEvent extends Event{
    private int sinceRound;

    public antiVaccinationismEvent(int sinceRound) {
        super("antiVaccinationism");
        this.sinceRound = sinceRound;
    }
}
