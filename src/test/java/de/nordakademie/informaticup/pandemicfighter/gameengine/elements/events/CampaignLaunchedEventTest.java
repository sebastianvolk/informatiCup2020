package de.nordakademie.informaticup.pandemicfighter.gameengine.elements.events;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CampaignLaunchedEventTest {
    private CampaignLaunchedEvent campaignLaunchedEvent;
    private CampaignLaunchedEvent campaignLaunchedEvent2;
    private CampaignLaunchedEvent campaignLaunchedEvent3;

    @Before
    public void setUp() throws Exception {
        campaignLaunchedEvent = new CampaignLaunchedEvent(5);
        campaignLaunchedEvent2 = new CampaignLaunchedEvent(63);
        campaignLaunchedEvent3 = new CampaignLaunchedEvent(90);
    }

    @Test
    public void getRound() {
        assertEquals(5, campaignLaunchedEvent.getRound());
        assertEquals(63, campaignLaunchedEvent2.getRound());
        assertEquals(90, campaignLaunchedEvent3.getRound());
    }

    @Test
    public void getRoundFalseTest() {
        assertNotEquals(6, campaignLaunchedEvent.getRound());
        assertNotEquals(630, campaignLaunchedEvent2.getRound());
        assertNotEquals(94, campaignLaunchedEvent3.getRound());
    }
}