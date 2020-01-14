package de.nordakademie.informaticup.pandemicfighter.gameengine;

import de.nordakademie.informaticup.pandemicfighter.gameengine.elements.City;
import de.nordakademie.informaticup.pandemicfighter.gameengine.elements.Pathogen;
import de.nordakademie.informaticup.pandemicfighter.gameengine.elements.events.*;
import de.nordakademie.informaticup.pandemicfighter.gameengine.provider.CityProvider;
import de.nordakademie.informaticup.pandemicfighter.gameengine.provider.GameProvider;

import java.util.ArrayList;

public class ThreatEvaluator {

    private static final double FACTOR_BOOST_ROUND_THREAT_PUT_UNDER_QUARANTINE = 1.15;
    private static final double FACTOR_BOOST_ROUND_THREAT_CLOSE_AIRPORT = 1.12;
    private static final double FACTOR_BOOST_ROUND_THREAT_CLOSE_CONNECTION = 1.04467;

    private static final double PREVALENCE_FACTOR_BOOSTER = 0.5;

    private static final double FACTOR_END_ROUND = 1.22;

    private static final double FACTOR_DEVELOP_MEDICATION = 1.4;
    private static final double FACTOR_DEPLOY_MEDICATION = 0.85;
    private static final double FACTOR_PUT_UNDER_QUARANTINE = 0.98;
    private static final double FACTOR_CLOSE_AIRPORT = 0.9;
    private static final double FACTOR_CLOSE_CONNECTION = 0.72;
    private static final double FACTOR_DEVELOP_VACCINE = 1.7;
    private static final double FACTOR_DEPLOY_VACCINE = 0.95;
    private static final double FACTOR_EXERT_INFLUENCE = 0.9;
    private static final double FACTOR_CALL_ELECTION = 0.8;
    private static final double FACTOR_APPLY_HYGIENIC_MEASURES = 1.037;
    private static final double FACTOR_LAUNCH_CAMPAIGN = 1.035;

    public double calculateDevelopMedication(Pathogen pathogen) {
        double threat = ThreatIndicator.getPathogenThreatIndicator(pathogen);
        ArrayList<City> cities = CityProvider.getCities();
        int cityWithPathogenOutbreakCount = 0;
        double averageCityThreat = 0;
        for (City city : cities) {
            if (city.hasCityPathogenOutbreak(pathogen)) {
                averageCityThreat += ThreatIndicator.getCityThreatIndicator(city);
                cityWithPathogenOutbreakCount++;
            }

        }
        averageCityThreat = average(averageCityThreat, cityWithPathogenOutbreakCount);
        threat = combineThreats(threat, averageCityThreat);
        threat *= FACTOR_DEVELOP_MEDICATION;
        return threat;
    }

    public double calculateDeployMedication(City city, Pathogen pathogen) {
        double pathogenThreat = ThreatIndicator.getPathogenThreatIndicator(pathogen);
        double cityThreat = ThreatIndicator.getCityThreatIndicator(city);
        boolean cityHasPathogen = false;
        double prevalenceFactor = 1;
        if (city.hasCityPathogenOutbreak(pathogen)) {
            cityHasPathogen = true;
            ArrayList<Event> medicationDeployedEvents = city.getEventsByType("medicationDeployed");
            for (Event medicationDeployedevent : medicationDeployedEvents) {
                MedicationDeployedEvent medicationDeployedEvent = (MedicationDeployedEvent) medicationDeployedevent;
                if (medicationDeployedEvent.getRound() > GameProvider.getGame().getRound() - 4 &&
                        medicationDeployedEvent.getPathogen().getName().equals(pathogen.getName())
                ) {
                    prevalenceFactor *= 0.7;

                }
                OutbreakEvent outbreakEvent = city.getCityOutBreakEvent(pathogen);
                if (outbreakEvent != null) {
                    prevalenceFactor *= (PREVALENCE_FACTOR_BOOSTER + outbreakEvent.getPrevalence());
                }
            }

        }
        double threat = combineThreats(pathogenThreat * prevalenceFactor, cityThreat);
        ArrayList<Event> bioTerrorismEvents = city.getEventsByType("bioTerrorism");
        for (Event event : bioTerrorismEvents) {
            BioTerrorismEvent bioTerrorismEvent = (BioTerrorismEvent) event;
            if (bioTerrorismEvent.getRound() > GameProvider.getGame().getRound() - 3 && pathogen.getName().equals(bioTerrorismEvent.getPathogen().getName())) {
                cityHasPathogen = true;
                threat *= 1.3;

            }

        }
        if (!cityHasPathogen) {
            threat = 0;
        }
        threat *= FACTOR_DEPLOY_MEDICATION;
        return threat;
    }

    public double calculatePutUnderQuarantine(City city, int rounds) {
        double threat = getThreatOfCityAndPathogens(city);
        threat = getThreatBoostedByRounds(threat, rounds, FACTOR_BOOST_ROUND_THREAT_PUT_UNDER_QUARANTINE);
        threat *= FACTOR_PUT_UNDER_QUARANTINE;
        return threat;
    }

    public double calculateCloseAirport(City city, int rounds) {
        double threat = getThreatOfCityAndPathogens(city);
        ArrayList<City> citiesThatHaveAConnectionToThisCity = CityProvider.getCitiesWhichHaveConnectionToGivenCity(city);
        double citiesThatHaveAConnectionToThisCityThreatAverage = 0;
        for (City cityThatHasAConnection : citiesThatHaveAConnectionToThisCity) {
            citiesThatHaveAConnectionToThisCityThreatAverage += getThreatOfCityAndPathogens(cityThatHasAConnection);
        }
        citiesThatHaveAConnectionToThisCityThreatAverage = average(citiesThatHaveAConnectionToThisCityThreatAverage, citiesThatHaveAConnectionToThisCity.size());
        threat = combineThreats(threat, citiesThatHaveAConnectionToThisCityThreatAverage);
        ArrayList<Event> bioTerrorismEvents = city.getEventsByType("bioTerrorism");
        for (Event event : bioTerrorismEvents) {
            BioTerrorismEvent bioTerrorismEvent = (BioTerrorismEvent) event;
            if (bioTerrorismEvent.getRound() > GameProvider.getGame().getRound() - 3) {
                threat *= 1.3;

            }

        }
        threat = getThreatBoostedByRounds(threat, rounds, FACTOR_BOOST_ROUND_THREAT_CLOSE_AIRPORT);
        if (city.getEventsByType("quarantine").size() > 0) {
            threat = 0;
        }
        threat = threat * FACTOR_CLOSE_AIRPORT;
        return threat;
    }

    public double calculateCloseConnection(City fromCity, City toCity, int rounds) {
        double threat = getThreatOfCityAndPathogens(fromCity);
        threat = combineThreats(threat, ThreatIndicator.getCityThreatIndicator(toCity));
        threat = getThreatBoostedByRounds(threat, rounds, FACTOR_BOOST_ROUND_THREAT_CLOSE_CONNECTION);
        if (fromCity.getEventsByType("airportClosed").size() > 0 || fromCity.getEventsByType("quarantine").size() > 0) {
            threat = 0;
        }
        threat *= FACTOR_CLOSE_CONNECTION;
        return threat;
    }

    public double calculateDevelopVaccine(Pathogen pathogen) {
        double threat = ThreatIndicator.getPathogenThreatIndicator(pathogen);
        ArrayList<City> cities = CityProvider.getCities();
        int cityWithPathogenOutbreakCount = 0;
        double averageCityThreat = 0;
        for (City city : cities) {
            ArrayList<Pathogen> pathogensInCity = city.getPathogensInCity();
            if (!pathogensInCity.isEmpty()) {
                for (Pathogen pathogenInCity : pathogensInCity) {
                    if (pathogen.getName().equals(pathogenInCity.getName())) {
                        averageCityThreat += ThreatIndicator.getCityThreatIndicator(city);
                        cityWithPathogenOutbreakCount++;
                    }
                }
            }
        }
        averageCityThreat = average(averageCityThreat, cityWithPathogenOutbreakCount);
        threat *= FACTOR_DEVELOP_VACCINE * averageCityThreat;
        //System.out.println(threat + " calculateDevelopVaccine");
        return threat;
    }

    public double calculateDeployVaccine(City city, Pathogen pathogen) {
        double threat = ThreatIndicator.getCityThreatIndicator(city);
        double pathogenThreat = ThreatIndicator.getPathogenThreatIndicator(pathogen);
        threat = combineThreats(threat, pathogenThreat);
        double prevalenceFactor = 1;
        OutbreakEvent outbreakEvent = city.getCityOutBreakEvent(pathogen);
        if (outbreakEvent != null) {
            prevalenceFactor = PREVALENCE_FACTOR_BOOSTER + 1 - outbreakEvent.getPrevalence();
        }
        ArrayList<City> nearCities = CityProvider.getNearCities(city);
        ArrayList<City> connectedCities = CityProvider.getConnectedCities(city);
        int nearCityCounter = 0;
        int connectedCityCounter = 0;
        for (City nearCity : nearCities) {
            if (nearCity.hasCityPathogenOutbreak(pathogen)) {
                nearCityCounter++;
            }

        }
        for (City connectedCity : connectedCities) {
            if (connectedCity.hasCityPathogenOutbreak(pathogen)) {
                connectedCityCounter++;
            }

        }
        if (nearCityCounter > 4 || connectedCityCounter > 2) {
            threat *= 1.5;
        }
        threat = combineThreats(threat, prevalenceFactor);
        threat *= FACTOR_DEPLOY_VACCINE;
        return threat;
    }

    public double calculateExertInfluence(City city) {
        double threat = randomlyChangeValueThreat(city.getEconomy(), false);
        ArrayList<Event> economicCrisisEvents = city.getEventsByType("economicCrisis");
        for (Event event : economicCrisisEvents) {
            EconomicCrisisEvent economicCrisisEvent = (EconomicCrisisEvent) event;
            if (economicCrisisEvent.getSinceRound() > GameProvider.getGame().getRound() - 3) {
                threat *= 1.3;

            }

        }
        threat *= FACTOR_EXERT_INFLUENCE;
        return threat;
    }

    public double calculateCallElection(City city) {
        double threat = randomlyChangeValueThreat(city.getGovernment(), false);
        ArrayList<Event> uprisingEvents = city.getEventsByType("uprising");
        for (Event event : uprisingEvents) {
            UprisingEvent uprisingEvent = (UprisingEvent) event;
            System.out.println("Uprising: \n\n" + uprisingEvent.getParticipants());
            if (uprisingEvent.getSinceRound() > GameProvider.getGame().getRound() - 5 &&
                    (city.getPopulation() / uprisingEvent.getParticipants()) > 0.4
            ) {
                threat *= 1.5;
            }

        }
        threat *= FACTOR_CALL_ELECTION;
        return threat;
    }

    public double calculateApplyHygienicMeasures(City city) {
        double threat = randomlyChangeValueThreat(city.getHygiene(), true);
        ArrayList<Event> hygienicMeasuresAppliedEvents = city.getEventsByType("hygienicMeasuresApplied");
        for (Event event : hygienicMeasuresAppliedEvents) {
            HygienicMeasuresAppliedEvent hygienicMeasuresAppliedEvent = (HygienicMeasuresAppliedEvent) event;
            if (hygienicMeasuresAppliedEvent.getRound() > GameProvider.getGame().getRound() - 5) {
                threat *= 0.7;

            }

        }
        threat *= FACTOR_APPLY_HYGIENIC_MEASURES;
        return threat;
    }

    public double calculateLaunchCampaign(City city) {
        double threat = randomlyChangeValueThreat(city.getAwareness(), true);
        ArrayList<Event> campaignLaunchedEvents = city.getEventsByType("campaignLaunched");
        for (Event event : campaignLaunchedEvents) {
            CampaignLaunchedEvent campaignLaunchedEvent = (CampaignLaunchedEvent) event;
            if (campaignLaunchedEvent.getRound() > GameProvider.getGame().getRound() - 5) {
                threat *= 0.7;
            }
        }
        ArrayList<Event> antiVaccinationismEvents = city.getEventsByType("antiVaccinationism");
        for (Event event : antiVaccinationismEvents) {
            AntiVaccinationismEvent antiVaccinationismEvent = (AntiVaccinationismEvent) event;
            if (antiVaccinationismEvent.getSinceRound() < GameProvider.getGame().getRound() - 5) {
                threat *= 1.3;
            }

        }
        threat *= FACTOR_LAUNCH_CAMPAIGN;
        return threat;
    }

    public double calculateEndRound() {
        double threat = FACTOR_END_ROUND;
        if (GameProvider.getGame().getPoints() > 25) threat = 0;
        return threat;
    }

    private double randomlyChangeValueThreat(Double value, Boolean justIncrease) {
        double threat = 1;
        if (value == ValueUtility.getVeryHighValueCity() || value == ValueUtility.getHighValueCity()) {
            threat *= 0;

        } else if (value == ValueUtility.getMidValue()) {
            if (justIncrease) {
                threat *= 1.07;
            } else {
                threat *= 1;
            }
        } else if (value == ValueUtility.getLowValueCity()) {
            threat *= 1.1;
        } else if (value == ValueUtility.getVeryLowValueCity()) {
            threat *= 1.2;
        }
        return threat;
    }

    private double getThreatOfCityAndPathogens(City city) {
        ArrayList<Event> outbreakEvents = city.getEventsByType("outbreak");
        double threat = ThreatIndicator.getCityThreatIndicator(city);
        if (!outbreakEvents.isEmpty()) {
            double averagePathogenThreat = 0;
            for (Event event : outbreakEvents) {
                OutbreakEvent outbreakEvent = (OutbreakEvent) event;
                double pathogenThreat = ThreatIndicator.getPathogenThreatIndicator(outbreakEvent.getPathogen());
                double prevalenceFactor = PREVALENCE_FACTOR_BOOSTER + outbreakEvent.getPrevalence();
                averagePathogenThreat += pathogenThreat * prevalenceFactor;
            }
            averagePathogenThreat = average(averagePathogenThreat, outbreakEvents.size());
            threat = combineThreats(threat, averagePathogenThreat);
        }
        return threat;
    }

    private double getThreatBoostedByRounds(double threat, int rounds, double factor) {
        return threat * Math.sqrt(Math.pow(factor, rounds - 1));
    }

    private double combineThreats(double threat1, double threat2) {
        return Math.sqrt(threat1 * threat2);
    }

    private double average(double threat, int count) {
        double average = 1;
        if (count > 0) {
            average = threat / count;
        }
        return average;
    }

}
