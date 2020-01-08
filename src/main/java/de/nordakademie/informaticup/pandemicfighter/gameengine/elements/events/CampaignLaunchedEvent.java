package de.nordakademie.informaticup.pandemicfighter.gameengine.elements.events;

public class CampaignLaunchedEvent extends Event {
    private int round;

    public CampaignLaunchedEvent(int round) {
        super("campaignLaunched");
        this.round = round;
    }

    public int getRound() {
        return round;
    }
}
