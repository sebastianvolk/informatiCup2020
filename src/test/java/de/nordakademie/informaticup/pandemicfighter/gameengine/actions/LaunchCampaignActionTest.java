package de.nordakademie.informaticup.pandemicfighter.gameengine.actions;

import com.google.gson.JsonObject;
import de.nordakademie.informaticup.pandemicfighter.gameengine.elements.City;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class LaunchCampaignActionTest {
    LaunchCampaignAction launchCampaignAction;

    @Before
    public void setUp() throws Exception {
        City city = new City("Test City", 0, 0, new ArrayList<>());
        launchCampaignAction = new LaunchCampaignAction(city);
    }

    @Test
    public void getPointsTest() {
        assertEquals(3, launchCampaignAction.getPoints());
    }

    @Test
    public void toJsonTest() {
        JsonObject expectedJsonObject = new JsonObject();
        expectedJsonObject.addProperty("type", "launchCampaign");
        expectedJsonObject.addProperty("city", "Test City");

        assertEquals(expectedJsonObject, launchCampaignAction.toJson());
    }
}
