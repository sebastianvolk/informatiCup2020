package de.nordakademie.informaticup.pandemicfighter.gameengine.events;

public class EconomicCrisisEvent extends Event{
    private int sinceRound;

    public EconomicCrisisEvent(int sinceRound) {
        super("economicCrisis");
        this.sinceRound = sinceRound;
    }
}
