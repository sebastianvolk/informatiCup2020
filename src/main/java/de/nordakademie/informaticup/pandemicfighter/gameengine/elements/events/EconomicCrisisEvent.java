package de.nordakademie.informaticup.pandemicfighter.gameengine.elements.events;

public class EconomicCrisisEvent extends Event{
    private int sinceRound;

    public EconomicCrisisEvent(int sinceRound) {
        super("economicCrisis");
        this.sinceRound = sinceRound;
    }
}
