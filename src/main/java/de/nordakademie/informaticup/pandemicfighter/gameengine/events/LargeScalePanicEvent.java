package de.nordakademie.informaticup.pandemicfighter.gameengine.events;

public class LargeScalePanicEvent extends Event{
    private int sinceRound;

    public LargeScalePanicEvent(int sinceRound) {
        super("largeScalePanic");
        this.sinceRound = sinceRound;
    }
}
