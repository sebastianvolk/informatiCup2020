package de.nordakademie.informaticup.pandemicfighter.gameengine;

import de.nordakademie.informaticup.pandemicfighter.gameengine.elements.City;
import de.nordakademie.informaticup.pandemicfighter.gameengine.elements.Game;
import de.nordakademie.informaticup.pandemicfighter.gameengine.elements.Pathogen;
import de.nordakademie.informaticup.pandemicfighter.gameengine.elements.events.*;
import de.nordakademie.informaticup.pandemicfighter.gameengine.provider.CityProvider;
import de.nordakademie.informaticup.pandemicfighter.gameengine.provider.GameProvider;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ThreatEvaluatorTest {
    private City cityWithPathogenOutbreak;
    private City cityWithoutPathogenOutbreak;
    private City cityWithPharmaceuticalDeployed;
    private City cityWithAirportClosed;
    private City cityWithConnectionToInfectedCities;
    private City cityUnderQuarantine;
    private City cityWithBioTerrorism;
    private City cityWithEconomicCrisis;
    private City cityWithUprising;
    private City cityWithHygienicMeasuresApplied;
    private City cityWithCampaignLaunched;
    private City cityWithAntiVaccinationism;
    private Pathogen pathogen;
    private ThreatEvaluator threatEvaluator;

    @Before
    public void setUp() throws Exception {
        threatEvaluator = new ThreatEvaluator();

        pathogen = mock(Pathogen.class);
        when(pathogen.getName()).thenReturn("Test Pathogen");
        when(pathogen.getDuration()).thenReturn(1.0);
        when(pathogen.getInfectivity()).thenReturn(1.1);
        when(pathogen.getLethality()).thenReturn(1.2);
        when(pathogen.getMobility()).thenReturn(1.2);

        cityWithoutPathogenOutbreak = mock(City.class);
        when(cityWithoutPathogenOutbreak.hasCityPathogenOutbreak(pathogen)).thenReturn(false);
        when(cityWithoutPathogenOutbreak.getName()).thenReturn("Test City");
        when(cityWithoutPathogenOutbreak.getAwareness()).thenReturn(0.9);
        when(cityWithoutPathogenOutbreak.getEconomy()).thenReturn(1.1);
        when(cityWithoutPathogenOutbreak.getGovernment()).thenReturn(1.0);
        when(cityWithoutPathogenOutbreak.getHygiene()).thenReturn(0.8);

        OutbreakEvent outbreakEvent = mock(OutbreakEvent.class);
        when(outbreakEvent.getPathogen()).thenReturn(pathogen);
        when(outbreakEvent.getPrevalence()).thenReturn(0.8);

        ArrayList<Event> outbreakEvents = new ArrayList<>();
        outbreakEvents.add(outbreakEvent);

        cityWithPathogenOutbreak = mock(City.class);
        when(cityWithPathogenOutbreak.hasCityPathogenOutbreak(pathogen)).thenReturn(true);
        when(cityWithPathogenOutbreak.getName()).thenReturn("Test City1");
        when(cityWithPathogenOutbreak.getAwareness()).thenReturn(0.9);
        when(cityWithPathogenOutbreak.getEconomy()).thenReturn(1.1);
        when(cityWithPathogenOutbreak.getGovernment()).thenReturn(1.0);
        when(cityWithPathogenOutbreak.getHygiene()).thenReturn(0.8);
        when(cityWithPathogenOutbreak.getEventsByType("outbreak")).thenReturn(outbreakEvents);
        when(cityWithPathogenOutbreak.getCityOutBreakEvent(pathogen)).thenReturn(outbreakEvent);

        MedicationDeployedEvent medicationDeployedEvent = mock(MedicationDeployedEvent.class);
        when(medicationDeployedEvent.getPathogen()).thenReturn(pathogen);
        when(medicationDeployedEvent.getRound()).thenReturn(4);

        ArrayList<Event> medicationDeployedEvents = new ArrayList<>();
        medicationDeployedEvents.add(medicationDeployedEvent);

        VaccineDeployedEvent vaccineDeployedEvent = mock(VaccineDeployedEvent.class);
        when(vaccineDeployedEvent.getPathogen()).thenReturn(pathogen);
        when(vaccineDeployedEvent.getRound()).thenReturn(4);

        ArrayList<Event> vaccineDeployedEvents = new ArrayList<>();
        vaccineDeployedEvents.add(vaccineDeployedEvent);

        cityWithPharmaceuticalDeployed = mock(City.class);
        when(cityWithPharmaceuticalDeployed.hasCityPathogenOutbreak(pathogen)).thenReturn(true);
        when(cityWithPharmaceuticalDeployed.getName()).thenReturn("Test City2");
        when(cityWithPharmaceuticalDeployed.getAwareness()).thenReturn(1.0);
        when(cityWithPharmaceuticalDeployed.getEconomy()).thenReturn(0.8);
        when(cityWithPharmaceuticalDeployed.getGovernment()).thenReturn(1.1);
        when(cityWithPharmaceuticalDeployed.getHygiene()).thenReturn(1.2);
        when(cityWithPharmaceuticalDeployed.getEventsByType("medicationDeployed")).thenReturn(medicationDeployedEvents);
        when(cityWithPharmaceuticalDeployed.getEventsByType("vaccineDeployed")).thenReturn(vaccineDeployedEvents);
        when(cityWithPharmaceuticalDeployed.getEventsByType("outbreak")).thenReturn(outbreakEvents);

        BioTerrorismEvent bioTerrorismEvent = mock(BioTerrorismEvent.class);
        when(bioTerrorismEvent.getPathogen()).thenReturn(pathogen);
        when(bioTerrorismEvent.getRound()).thenReturn(5);

        ArrayList<Event> bioTerrorismEvents = new ArrayList<>();
        bioTerrorismEvents.add(bioTerrorismEvent);

        cityWithBioTerrorism = mock(City.class);
        when(cityWithBioTerrorism.hasCityPathogenOutbreak(pathogen)).thenReturn(true);
        when(cityWithBioTerrorism.getName()).thenReturn("Test City3");
        when(cityWithBioTerrorism.getAwareness()).thenReturn(1.0);
        when(cityWithBioTerrorism.getEconomy()).thenReturn(0.8);
        when(cityWithBioTerrorism.getGovernment()).thenReturn(1.1);
        when(cityWithBioTerrorism.getHygiene()).thenReturn(1.2);
        when(cityWithBioTerrorism.getEventsByType("bioTerrorism")).thenReturn(bioTerrorismEvents);
        when(cityWithBioTerrorism.getEventsByType("outbreak")).thenReturn(outbreakEvents);

        QuarantineEvent quarantineEvent = mock(QuarantineEvent.class);
        when(quarantineEvent.getSinceRound()).thenReturn(4);
        when(quarantineEvent.getUntilRound()).thenReturn(6);

        ArrayList<Event> quarantineEvents = new ArrayList<>();
        quarantineEvents.add(quarantineEvent);

        cityUnderQuarantine = mock(City.class);
        when(cityUnderQuarantine.hasCityPathogenOutbreak(pathogen)).thenReturn(true);
        when(cityUnderQuarantine.getName()).thenReturn("Test City4");
        when(cityUnderQuarantine.getAwareness()).thenReturn(1.0);
        when(cityUnderQuarantine.getEconomy()).thenReturn(0.8);
        when(cityUnderQuarantine.getGovernment()).thenReturn(1.1);
        when(cityUnderQuarantine.getHygiene()).thenReturn(1.2);
        when(cityUnderQuarantine.getEventsByType("quarantine")).thenReturn(quarantineEvents);
        when(cityUnderQuarantine.getEventsByType("outbreak")).thenReturn(outbreakEvents);

        AirportClosedEvent airportClosedEvent = mock(AirportClosedEvent.class);
        when(airportClosedEvent.getSinceRound()).thenReturn(4);
        when(airportClosedEvent.getUntilRound()).thenReturn(6);

        ArrayList<Event> airportClosedEvents = new ArrayList<>();
        airportClosedEvents.add(airportClosedEvent);

        cityWithAirportClosed = mock(City.class);
        when(cityWithAirportClosed.hasCityPathogenOutbreak(pathogen)).thenReturn(true);
        when(cityWithAirportClosed.getAwareness()).thenReturn(1.0);
        when(cityWithAirportClosed.getEconomy()).thenReturn(0.8);
        when(cityWithAirportClosed.getGovernment()).thenReturn(1.1);
        when(cityWithAirportClosed.getHygiene()).thenReturn(1.2);
        when(cityWithAirportClosed.getEventsByType("airportClosed")).thenReturn(airportClosedEvents);
        when(cityWithAirportClosed.getEventsByType("outbreak")).thenReturn(outbreakEvents);

        ArrayList<String> cityConnections = new ArrayList<>();
        cityConnections.add("Test City1");
        cityConnections.add("Test City2");
        cityConnections.add("Test City3");
        cityConnections.add("Test City4");

        cityWithConnectionToInfectedCities = mock(City.class);
        when(cityWithConnectionToInfectedCities.hasCityPathogenOutbreak(pathogen)).thenReturn(false);
        when(cityWithConnectionToInfectedCities.getName()).thenReturn("Test City5");
        when(cityWithConnectionToInfectedCities.getAwareness()).thenReturn(0.9);
        when(cityWithConnectionToInfectedCities.getEconomy()).thenReturn(1.1);
        when(cityWithConnectionToInfectedCities.getGovernment()).thenReturn(1.0);
        when(cityWithConnectionToInfectedCities.getHygiene()).thenReturn(0.8);
        when(cityWithConnectionToInfectedCities.getConnections()).thenReturn(cityConnections);

        EconomicCrisisEvent economicCrisisEvent = mock(EconomicCrisisEvent.class);
        when(economicCrisisEvent.getSinceRound()).thenReturn(5);

        ArrayList<Event> economicCrisisEvents = new ArrayList<>();
        economicCrisisEvents.add(economicCrisisEvent);

        cityWithEconomicCrisis = mock(City.class);
        when(cityWithEconomicCrisis.getAwareness()).thenReturn(0.9);
        when(cityWithEconomicCrisis.getEconomy()).thenReturn(1.1);
        when(cityWithEconomicCrisis.getGovernment()).thenReturn(1.0);
        when(cityWithEconomicCrisis.getHygiene()).thenReturn(0.8);
        when(cityWithEconomicCrisis.getEventsByType("economicCrisis")).thenReturn(economicCrisisEvents);

        UprisingEvent uprisingEvent = mock(UprisingEvent.class);
        when(uprisingEvent.getSinceRound()).thenReturn(4);
        when(uprisingEvent.getParticipants()).thenReturn(41);

        ArrayList<Event> uprisingEvents = new ArrayList<>();
        uprisingEvents.add(uprisingEvent);

        cityWithUprising = mock(City.class);
        when(cityWithUprising.getAwareness()).thenReturn(0.9);
        when(cityWithUprising.getEconomy()).thenReturn(1.1);
        when(cityWithUprising.getGovernment()).thenReturn(1.0);
        when(cityWithUprising.getHygiene()).thenReturn(0.8);
        when(cityWithUprising.getPopulation()).thenReturn(100);
        when(cityWithUprising.getEventsByType("uprising")).thenReturn(uprisingEvents);

        HygienicMeasuresAppliedEvent hygienicMeasuresAppliedEvent = mock(HygienicMeasuresAppliedEvent.class);
        when(hygienicMeasuresAppliedEvent.getRound()).thenReturn(4);

        ArrayList<Event> hygienicMeasuresAppliedEvents = new ArrayList<>();
        hygienicMeasuresAppliedEvents.add(hygienicMeasuresAppliedEvent);

        cityWithHygienicMeasuresApplied = mock(City.class);
        when(cityWithHygienicMeasuresApplied.getAwareness()).thenReturn(0.9);
        when(cityWithHygienicMeasuresApplied.getEconomy()).thenReturn(1.1);
        when(cityWithHygienicMeasuresApplied.getGovernment()).thenReturn(1.0);
        when(cityWithHygienicMeasuresApplied.getHygiene()).thenReturn(1.2);
        when(cityWithHygienicMeasuresApplied.getEventsByType("hygienicMeasuresApplied")).thenReturn(hygienicMeasuresAppliedEvents);

        CampaignLaunchedEvent campaignLaunchedEvent = mock(CampaignLaunchedEvent.class);
        when(campaignLaunchedEvent.getRound()).thenReturn(5);

        ArrayList<Event> campaignLaunchedEvents = new ArrayList<>();
        campaignLaunchedEvents.add(campaignLaunchedEvent);

        cityWithCampaignLaunched = mock(City.class);
        when(cityWithCampaignLaunched.getAwareness()).thenReturn(1.0);
        when(cityWithCampaignLaunched.getEconomy()).thenReturn(0.8);
        when(cityWithCampaignLaunched.getGovernment()).thenReturn(1.1);
        when(cityWithCampaignLaunched.getHygiene()).thenReturn(1.2);
        when(cityWithCampaignLaunched.getEventsByType("campaignLaunched")).thenReturn(campaignLaunchedEvents);

        AntiVaccinationismEvent antiVaccinationismEvent = mock(AntiVaccinationismEvent.class);
        when(antiVaccinationismEvent.getSinceRound()).thenReturn(1);

        ArrayList<Event> antiVaccinationismEvents = new ArrayList<>();
        antiVaccinationismEvents.add(antiVaccinationismEvent);

        cityWithAntiVaccinationism = mock(City.class);
        when(cityWithAntiVaccinationism.getAwareness()).thenReturn(1.0);
        when(cityWithAntiVaccinationism.getEconomy()).thenReturn(0.8);
        when(cityWithAntiVaccinationism.getGovernment()).thenReturn(1.1);
        when(cityWithAntiVaccinationism.getHygiene()).thenReturn(1.2);
        when(cityWithAntiVaccinationism.getEventsByType("antiVaccinationism")).thenReturn(antiVaccinationismEvents);

        Game game = mock(Game.class);
        when(game.getRound()).thenReturn(7);

        GameProvider.initialize(game);

        ArrayList<City> cities = new ArrayList<>();
        cities.add(cityWithPathogenOutbreak);
        cities.add(cityWithoutPathogenOutbreak);
        cities.add(cityWithConnectionToInfectedCities);
        cities.add(cityWithPharmaceuticalDeployed);
        cities.add(cityWithBioTerrorism);
        cities.add(cityUnderQuarantine);
        CityProvider.setCities(cities);
    }

    @Test
    public void calculateDevelopMedicationTest() {
        assertEquals(2, threatEvaluator.calculateDevelopMedication(pathogen), 0.05);
    }

    @Test
    public void calculateDeployMedicationWithoutOutbreakTest() {
        assertEquals(0, threatEvaluator.calculateDeployMedication(cityWithoutPathogenOutbreak, pathogen), 0.0);
    }

    @Test
    public void calculateDeployMedicationWithOutbreakTest() {
        assertEquals(1.25, threatEvaluator.calculateDeployMedication(cityWithPathogenOutbreak, pathogen), 0.05);
    }

    @Test
    public void calculateDeployMedicationWithMedicationDeployedTest() {
        assertEquals(1.05, threatEvaluator.calculateDeployMedication(cityWithPharmaceuticalDeployed, pathogen), 0.05);
    }

    @Test
    public void calculateDeployMedicationWithBioTerrorismTest() {
        assertEquals(1.6, threatEvaluator.calculateDeployMedication(cityWithBioTerrorism, pathogen), 0.05);
    }

    @Test
    public void calculatePutUnderQuarantineTest() {
        assertEquals(1.4, threatEvaluator.calculatePutUnderQuarantine(cityWithPathogenOutbreak, 1), 0.05);
    }

    @Test
    public void calculatePutUnderQuarantineNoOutbreakTest() {
        assertEquals(0, threatEvaluator.calculatePutUnderQuarantine(cityWithoutPathogenOutbreak, 1), 0.0);
    }

    @Test
    public void calculatePutUnderQuarantineMoreRoundsTest() {
        assertEquals(1.5, threatEvaluator.calculatePutUnderQuarantine(cityWithPathogenOutbreak, 2), 0.05);
    }

    @Test
    public void calculateCloseAirportTest() {
        assertEquals(1, threatEvaluator.calculateCloseAirport(cityWithPathogenOutbreak, 1), 0.05);
    }

    @Test
    public void calculateCloseAirportMoreRoundsTest() {
        assertEquals(1.1, threatEvaluator.calculateCloseAirport(cityWithPathogenOutbreak, 2), 0.05);
    }

    @Test
    public void calculateCloseAirportWithBioTerrorismTest() {
        assertEquals(1.4, threatEvaluator.calculateCloseAirport(cityWithBioTerrorism, 1), 0.05);
    }

    @Test
    public void calculateCloseAirportWithCityUnderQuarantineTest() {
        assertEquals(0, threatEvaluator.calculateCloseAirport(cityUnderQuarantine, 1), 0.0);
    }

    @Test
    public void calculateCloseConnectionTest() {
        assertEquals(0.8, threatEvaluator.calculateCloseConnection(cityWithPathogenOutbreak, cityWithoutPathogenOutbreak, 1), 0.05);
    }

    @Test
    public void calculateCloseConnectionNoOutbreakTest() {
        assertEquals(0, threatEvaluator.calculateCloseConnection(cityWithoutPathogenOutbreak, cityWithPathogenOutbreak, 1), 0.0);
    }

    @Test
    public void calculateCloseConnectionMoreRoundsTest() {
        assertEquals(0.8, threatEvaluator.calculateCloseConnection(cityWithPathogenOutbreak, cityWithoutPathogenOutbreak, 2), 0.05);
    }

    @Test
    public void calculateCloseConnectionAirportClosedTest() {
        assertEquals(0, threatEvaluator.calculateCloseConnection(cityWithAirportClosed, cityWithoutPathogenOutbreak, 1), 0.0);
    }

    @Test
    public void calculateCloseConnectionUnderQuarantineTest() {
        assertEquals(0, threatEvaluator.calculateCloseConnection(cityUnderQuarantine, cityWithoutPathogenOutbreak, 1), 0.0);
    }

    @Test
    public void calculateDevelopVaccineTest() {
        assertEquals(3.1, threatEvaluator.calculateDevelopVaccine(pathogen), 0.05);
    }

    @Test
    public void calculateDeployVaccineWithoutOutbreakTest() {
        assertEquals(1.1, threatEvaluator.calculateDeployVaccine(cityWithoutPathogenOutbreak, pathogen), 0.05);
    }

    @Test
    public void calculateDeployVaccineWithOutbreakTest() {
        assertEquals(0.9, threatEvaluator.calculateDeployVaccine(cityWithPathogenOutbreak, pathogen), 0.05);
    }

    @Test
    public void calculateDeployVaccineWithConnectedCityWithPathogenTest() {
        assertEquals(1.3, threatEvaluator.calculateDeployVaccine(cityWithConnectionToInfectedCities, pathogen), 0.05);
    }

    @Test
    public void calculateExertInfluenceTest() {
        assertEquals(1, threatEvaluator.calculateExertInfluence(cityWithoutPathogenOutbreak), 0.05);
    }

    @Test
    public void calculateExertInfluenceWithEconomicCrisisTest() {
        assertEquals(1.3, threatEvaluator.calculateExertInfluence(cityWithEconomicCrisis), 0.05);
    }

    @Test
    public void calculateCallElectionTest() {
        assertEquals(0.8, threatEvaluator.calculateCallElection(cityWithoutPathogenOutbreak), 0.05);
    }

    @Test
    public void calculateCallElectionWithUprisingTest() {
        assertEquals(1.2, threatEvaluator.calculateCallElection(cityWithUprising), 0.05);
    }

    @Test
    public void calculateApplyHygienicMeasuresNotNeededTest() {
        assertEquals(0, threatEvaluator.calculateApplyHygienicMeasures(cityWithoutPathogenOutbreak), 0.0);
    }

    @Test
    public void calculateApplyHygienicMeasuresTest() {
        assertEquals(1.2, threatEvaluator.calculateApplyHygienicMeasures(cityWithPharmaceuticalDeployed), 0.05);
    }

    @Test
    public void calculateApplyHygienicMeasuresWithHygienicMeasuresAppliedTest() {
        assertEquals(0.9, threatEvaluator.calculateApplyHygienicMeasures(cityWithHygienicMeasuresApplied), 0.05);
    }

    @Test
    public void calculateLaunchCampaignNotNeededTest() {
        assertEquals(0, threatEvaluator.calculateLaunchCampaign(cityWithoutPathogenOutbreak), 0.0);
    }

    @Test
    public void calculateLaunchCampaignTest() {
        assertEquals(1.1, threatEvaluator.calculateLaunchCampaign(cityWithPharmaceuticalDeployed), 0.05);
    }

    @Test
    public void calculateLaunchCampaignWithCampaignLaunchedTest() {
        assertEquals(0.8, threatEvaluator.calculateLaunchCampaign(cityWithCampaignLaunched), 0.05);
    }

    @Test
    public void calculateLaunchCampaignWithAntiVaccinationismTest() {
        assertEquals(1.4, threatEvaluator.calculateLaunchCampaign(cityWithAntiVaccinationism), 0.05);
    }

    @Test
    public void calculateEndRoundTest() {
        assertEquals(1.22, threatEvaluator.calculateEndRound(), 0.0);
    }
}
