package de.nordakademie.informaticup.pandemicfighter.gameengine.factories;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import de.nordakademie.informaticup.pandemicfighter.gameengine.elements.events.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class EventFactoryTest {
    EventFactory eventFactory;
    JsonObject pathogenJsonObject;

    @Before
    public void setUp() throws Exception {
        Gson gson = new Gson();
        eventFactory = new EventFactory();
        pathogenJsonObject = gson.fromJson(
                "{\n" +
                        "  \"name\": \"Test Pathogen\",\n" +
                        "  \"infectivity\": \"+\",\n" +
                        "  \"mobility\": \"++\",\n" +
                        "  \"duration\": \"o\",\n" +
                        "  \"lethality\": \"+\"\n" +
                        "}",
                JsonObject.class
        );
    }

    @Test
    public void createAirportClosedEventTest() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("type", "airportClosed");
        jsonObject.addProperty("sinceRound", 5);
        jsonObject.addProperty("untilRound", 12);

        Event event = eventFactory.createEvent(jsonObject);
        assertEquals(AirportClosedEvent.class, event.getClass());
        AirportClosedEvent airportClosedEvent = (AirportClosedEvent) event;
        assertEquals(5, airportClosedEvent.getSinceRound());
        assertEquals(12, airportClosedEvent.getUntilRound());
    }

    @Test
    public void createAntiVaccinationismEventTest() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("type", "antiVaccinationism");
        jsonObject.addProperty("sinceRound", 11);

        Event event = eventFactory.createEvent(jsonObject);
        assertEquals(AntiVaccinationismEvent.class, event.getClass());
        AntiVaccinationismEvent antiVaccinationismEvent = (AntiVaccinationismEvent) event;
        assertEquals(11, antiVaccinationismEvent.getSinceRound());
    }

    @Test
    public void createBioTerrorismEventTest() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("type", "bioTerrorism");
        jsonObject.add("pathogen", pathogenJsonObject);
        jsonObject.addProperty("round", 15);

        Event event = eventFactory.createEvent(jsonObject);
        assertEquals(BioTerrorismEvent.class, event.getClass());
        BioTerrorismEvent bioTerrorismEvent = (BioTerrorismEvent) event;
        assertEquals("Test Pathogen", bioTerrorismEvent.getPathogen().getName());
        assertEquals(15, bioTerrorismEvent.getRound());
    }

    @Test
    public void createCampaignLaunchedEventTest() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("type", "campaignLaunched");
        jsonObject.addProperty("round", 6);

        Event event = eventFactory.createEvent(jsonObject);
        assertEquals(CampaignLaunchedEvent.class, event.getClass());
        CampaignLaunchedEvent campaignLaunchedEvent = (CampaignLaunchedEvent) event;
        assertEquals(6, campaignLaunchedEvent.getRound());
    }

    @Test
    public void createConnectionClosedEventTest() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("type", "connectionClosed");
        jsonObject.addProperty("city", "Hamburg");
        jsonObject.addProperty("sinceRound", 6);
        jsonObject.addProperty("untilRound", 9);

        Event event = eventFactory.createEvent(jsonObject);
        assertEquals(ConnectionClosedEvent.class, event.getClass());
        ConnectionClosedEvent connectionClosedEvent = (ConnectionClosedEvent) event;
        assertEquals("Hamburg", connectionClosedEvent.getCity());
        assertEquals(6, connectionClosedEvent.getSinceRound());
        assertEquals(9, connectionClosedEvent.getUntilRound());
    }

    @Test
    public void createEconomicCrisisEventTest() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("type", "economicCrisis");
        jsonObject.addProperty("sinceRound", 7);

        Event event = eventFactory.createEvent(jsonObject);
        assertEquals(EconomicCrisisEvent.class, event.getClass());
        EconomicCrisisEvent economicCrisisEvent = (EconomicCrisisEvent) event;
        assertEquals(7, economicCrisisEvent.getSinceRound());
    }

    @Test
    public void createElectionsCalledEventTest() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("type", "electionsCalled");
        jsonObject.addProperty("round", 8);

        Event event = eventFactory.createEvent(jsonObject);
        assertEquals(ElectionsCalledEvent.class, event.getClass());
        ElectionsCalledEvent electionsCalledEvent = (ElectionsCalledEvent) event;
        assertEquals(8, electionsCalledEvent.getRound());
    }

    @Test
    public void createHygienicMeasuresAppliedEventTest() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("type", "hygienicMeasuresApplied");
        jsonObject.addProperty("round", 9);

        Event event = eventFactory.createEvent(jsonObject);
        assertEquals(HygienicMeasuresAppliedEvent.class, event.getClass());
        HygienicMeasuresAppliedEvent hygienicMeasuresAppliedEvent = (HygienicMeasuresAppliedEvent) event;
        assertEquals(9, hygienicMeasuresAppliedEvent.getRound());
    }

    @Test
    public void createInfluenceExertedEventTest() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("type", "influenceExerted");
        jsonObject.addProperty("round", 10);

        Event event = eventFactory.createEvent(jsonObject);
        assertEquals(InfluenceExertedEvent.class, event.getClass());
        InfluenceExertedEvent influenceExertedEvent = (InfluenceExertedEvent) event;
        assertEquals(10, influenceExertedEvent.getRound());
    }

    @Test
    public void createLargeScalePanicEventTest() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("type", "largeScalePanic");
        jsonObject.addProperty("sinceRound", 11);

        Event event = eventFactory.createEvent(jsonObject);
        assertEquals(LargeScalePanicEvent.class, event.getClass());
        LargeScalePanicEvent largeScalePanicEvent = (LargeScalePanicEvent) event;
        assertEquals(11, largeScalePanicEvent.getSinceRound());
    }

    @Test
    public void createMedicationAvailableEventTest() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("type", "medicationAvailable");
        jsonObject.add("pathogen", pathogenJsonObject);
        jsonObject.addProperty("sinceRound", 13);

        Event event = eventFactory.createEvent(jsonObject);
        assertEquals(MedicationAvailableEvent.class, event.getClass());
        MedicationAvailableEvent medicationAvailableEvent = (MedicationAvailableEvent) event;
        assertEquals("Test Pathogen", medicationAvailableEvent.getPathogen().getName());
        assertEquals(13, medicationAvailableEvent.getSinceRound());
    }

    @Test
    public void createMedicationDeployedEventTest() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("type", "medicationDeployed");
        jsonObject.add("pathogen", pathogenJsonObject);
        jsonObject.addProperty("round", 14);

        Event event = eventFactory.createEvent(jsonObject);
        assertEquals(MedicationDeployedEvent.class, event.getClass());
        MedicationDeployedEvent medicationAvailableEvent = (MedicationDeployedEvent) event;
        assertEquals("Test Pathogen", medicationAvailableEvent.getPathogen().getName());
        assertEquals(14, medicationAvailableEvent.getRound());
    }

    @Test
    public void createMedicationInDevelopmentEventTest() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("type", "medicationInDevelopment");
        jsonObject.add("pathogen", pathogenJsonObject);
        jsonObject.addProperty("sinceRound", 14);
        jsonObject.addProperty("untilRound", 15);

        Event event = eventFactory.createEvent(jsonObject);
        assertEquals(MedicationInDevelopmentEvent.class, event.getClass());
        MedicationInDevelopmentEvent medicationInDevelopmentEvent = (MedicationInDevelopmentEvent) event;
        assertEquals("Test Pathogen", medicationInDevelopmentEvent.getPathogen().getName());
        assertEquals(14, medicationInDevelopmentEvent.getSinceRound());
        assertEquals(15, medicationInDevelopmentEvent.getUntilRound());
    }

    @Test
    public void createOutbreakEventTest() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("type", "outbreak");
        jsonObject.add("pathogen", pathogenJsonObject);
        jsonObject.addProperty("prevalence", 0.75);
        jsonObject.addProperty("sinceRound", 17);

        Event event = eventFactory.createEvent(jsonObject);
        assertEquals(OutbreakEvent.class, event.getClass());
        OutbreakEvent medicationInDevelopmentEvent = (OutbreakEvent) event;
        assertEquals("Test Pathogen", medicationInDevelopmentEvent.getPathogen().getName());
        assertEquals(0.75, medicationInDevelopmentEvent.getPrevalence(), 0.0);
        assertEquals(17, medicationInDevelopmentEvent.getSinceRound());
    }

    @Test
    public void createPathogenEncounteredEventTest() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("type", "pathogenEncountered");
        jsonObject.add("pathogen", pathogenJsonObject);
        jsonObject.addProperty("round", 19);

        Event event = eventFactory.createEvent(jsonObject);
        assertEquals(PathogenEncounteredEvent.class, event.getClass());
        PathogenEncounteredEvent pathogenEncounteredEvent = (PathogenEncounteredEvent) event;
        assertEquals("Test Pathogen", pathogenEncounteredEvent.getPathogen().getName());
        assertEquals(19, pathogenEncounteredEvent.getRound());
    }

    @Test
    public void createQuarantineEventTest() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("type", "quarantine");
        jsonObject.addProperty("sinceRound", 20);
        jsonObject.addProperty("untilRound", 22);

        Event event = eventFactory.createEvent(jsonObject);
        assertEquals(QuarantineEvent.class, event.getClass());
        QuarantineEvent quarantineEvent = (QuarantineEvent) event;
        assertEquals(20, quarantineEvent.getSinceRound());
        assertEquals(22, quarantineEvent.getUntilRound());
    }

    @Test
    public void createUprisingEventTest() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("type", "uprising");
        jsonObject.addProperty("participants", 2000);
        jsonObject.addProperty("sinceRound", 23);

        Event event = eventFactory.createEvent(jsonObject);
        assertEquals(UprisingEvent.class, event.getClass());
        UprisingEvent uprisingEvent = (UprisingEvent) event;
        assertEquals(2000, uprisingEvent.getParticipants());
        assertEquals(23, uprisingEvent.getSinceRound());
    }

    @Test
    public void createVaccineAvailableEventTest() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("type", "vaccineAvailable");
        jsonObject.add("pathogen", pathogenJsonObject);
        jsonObject.addProperty("sinceRound", 13);

        Event event = eventFactory.createEvent(jsonObject);
        assertEquals(VaccineAvailableEvent.class, event.getClass());
        VaccineAvailableEvent vaccineAvailableEvent = (VaccineAvailableEvent) event;
        assertEquals("Test Pathogen", vaccineAvailableEvent.getPathogen().getName());
        assertEquals(13, vaccineAvailableEvent.getSinceRound());
    }

    @Test
    public void createVaccineDeployedEventTest() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("type", "vaccineDeployed");
        jsonObject.add("pathogen", pathogenJsonObject);
        jsonObject.addProperty("round", 14);

        Event event = eventFactory.createEvent(jsonObject);
        assertEquals(VaccineDeployedEvent.class, event.getClass());
        VaccineDeployedEvent vaccineDeployedEvent = (VaccineDeployedEvent) event;
        assertEquals("Test Pathogen", vaccineDeployedEvent.getPathogen().getName());
        assertEquals(14, vaccineDeployedEvent.getRound());
    }

    @Test
    public void createVaccineInDevelopmentEventTest() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("type", "vaccineInDevelopment");
        jsonObject.add("pathogen", pathogenJsonObject);
        jsonObject.addProperty("sinceRound", 14);
        jsonObject.addProperty("untilRound", 15);

        Event event = eventFactory.createEvent(jsonObject);
        assertEquals(VaccineInDevelopmentEvent.class, event.getClass());
        VaccineInDevelopmentEvent vaccineInDevelopmentEvent = (VaccineInDevelopmentEvent) event;
        assertEquals("Test Pathogen", vaccineInDevelopmentEvent.getPathogen().getName());
        assertEquals(14, vaccineInDevelopmentEvent.getSinceRound());
        assertEquals(15, vaccineInDevelopmentEvent.getUntilRound());
    }
}
