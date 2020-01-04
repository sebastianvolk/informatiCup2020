package de.nordakademie.informaticup.pandemicfighter.gameengine.events;

public class AntiVaccinationismEvent extends Event{
    private int sinceRound;

    public AntiVaccinationismEvent(int sinceRound) {
        super("antiVaccinationism");
        this.sinceRound = sinceRound;
    }
}
