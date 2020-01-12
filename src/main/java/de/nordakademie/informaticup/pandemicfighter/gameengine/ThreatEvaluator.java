package de.nordakademie.informaticup.pandemicfighter.gameengine;

import de.nordakademie.informaticup.pandemicfighter.gameengine.elements.City;
import de.nordakademie.informaticup.pandemicfighter.gameengine.elements.Pathogen;
import de.nordakademie.informaticup.pandemicfighter.gameengine.elements.events.Event;
import de.nordakademie.informaticup.pandemicfighter.gameengine.elements.events.MedicationDeployedEvent;
import de.nordakademie.informaticup.pandemicfighter.gameengine.elements.events.OutbreakEvent;
import de.nordakademie.informaticup.pandemicfighter.gameengine.provider.CityProvider;
import de.nordakademie.informaticup.pandemicfighter.gameengine.provider.GameProvider;

import java.util.ArrayList;

public class ThreatEvaluator {
    private static final double FACTOR_OUTBREAK = 1.5;
    private static final double FACTOR_REACHABLE_CITY = 1.2;
    private static final double FACTOR_AIRPORT_REACHABLE_CITY = 1.02;
    private static final double FACTOR_NEAR_CITY_HAS_PATHOGEN = 1.1;
    private static final double FACTOR_IMPACT_OF_PREVALENCE = 1.3;
    private static final double FACTOR_TOO_MANY_REACHABLE_CITIES_ALREADY_INFECTED = 0.8;
    private static final double FACTOR_MEDICATION_ALREADY_DEPLOYED = 0.6;

    private static final double THREAT_THRESHOLD = 3;
    private static final double FACTOR_BOOST_ROUND_THREAT_PUT_UNDER_QUARANTINE = 1.15;
    private static final double FACTOR_BOOST_ROUND_THREAT_CLOSE_AIRPORT = 1.09;
    private static final double FACTOR_BOOST_ROUND_THREAT_CLOSE_CONNECTION = 1.06467;

    private static final double PREVALENCE_FACTOR_BOOSTER = 0.5;

    private static final double PERCENTAGE_INFECTED_WORLD_POPULATION = 0.3;
    private static final double FACTOR_TOO_MANY_INFECTED = 1.5;
    private static final double FACTOR_END_ROUND = 1.22;
    //private static final double FACTOR_END_ROUND = 3; DA HAT ER GESPART

    private static final double FACTOR_DEVELOP_MEDICATION = 1.4;
    private static final double FACTOR_DEPLOY_MEDICATION = 0.85;
    private static final double FACTOR_PUT_UNDER_QUARANTINE = 0.98;
    private static final double FACTOR_CLOSE_AIRPORT = 0.9;
    private static final double FACTOR_CLOSE_CONNECTION = 0.72;
    private static final double FACTOR_DEVELOP_VACCINE = 1.7;
    private static final double FACTOR_DEPLOY_VACCINE = 0.9;
    private static final double FACTOR_EXERT_INFLUENCE = 1.0315;
    private static final double FACTOR_CALL_ELECTION = 1.031;
    private static final double FACTOR_APPLY_HYGIENIC_MEASURES = 1.0314;
    private static final double FACTOR_LAUNCH_CAMPAIGN = 1.0313;

    public double calculateDevelopMedication(Pathogen pathogen) {
        double threat = ThreatIndicator.getPathogenThreatIndicator(pathogen);
        ArrayList<City> cities = CityProvider.getCities();
        int cityWithPathogenOutbreakCount = 0;
        double averageCityThreat = 0;
        for (City city : cities) {
            ArrayList<Event> events = city.getEventsByType("outbreak");
            for (Event event : events) {
                OutbreakEvent outbreakEvent = (OutbreakEvent) event;
                if (pathogen.getName().equals(outbreakEvent.getPathogen().getName())) {
                    averageCityThreat += ThreatIndicator.getCityThreatIndicator(city);
                    cityWithPathogenOutbreakCount++;
                }
            }
        }
        averageCityThreat = average(averageCityThreat, cityWithPathogenOutbreakCount);
        threat = combineThreats(threat, averageCityThreat);
        threat *= FACTOR_DEVELOP_MEDICATION;
        //System.out.println(threat + " calculateDevelopMedication");
        return threat;
    }

    public double calculateDeployMedication(City city, Pathogen pathogen) {
        double pathogenThreat = ThreatIndicator.getPathogenThreatIndicator(pathogen);
        double cityThreat = ThreatIndicator.getCityThreatIndicator(city);
        boolean cityHasPathogen = false;
        double prevalenceFactor = 1;
        ArrayList<Event> events = city.getEventsByType("outbreak");
        for (Event event : events) {
            OutbreakEvent outbreakEvent = (OutbreakEvent) event;
            if (outbreakEvent.getPathogen().getName().equals(pathogen.getName())) {
                cityHasPathogen = true;
                prevalenceFactor = PREVALENCE_FACTOR_BOOSTER + outbreakEvent.getPrevalence();
            }
        }
        double threat = combineThreats(pathogenThreat * prevalenceFactor, cityThreat);
        if (!cityHasPathogen) threat = 0;
        threat *= FACTOR_DEPLOY_MEDICATION;
        //System.out.println(threat + " calculateDeployMedication");

        /*double threat = 1;
        double pathogenThreat = ThreatIndicator.getPathogenThreatIndicator(pathogen);
        double cityThreat = ThreatIndicator.getCityThreatIndicator(city);
        threat *= pathogenThreat * cityThreat;
        ArrayList<Event> events = city.getEventsByType("outbreak");
        if (events.isEmpty()){
            threat = 0;
        }
        else {
            for (Event event : events) {
                OutbreakEvent outbreakEvent = (OutbreakEvent) event;
                ArrayList<Event> medicationDeployedEvents = city.getEventsByType("medicationDeployed");
                for (Event medicationEvent : medicationDeployedEvents) {
                    MedicationDeployedEvent medicationDeployedEvent = (MedicationDeployedEvent) medicationEvent;
                    if (medicationDeployedEvent.getPathogen().getName().equals(pathogen.getName())) {
                        threat *= FACTOR_MEDICATION_ALREADY_DEPLOYED;
                    }
                }
                if (pathogen.getName().equals(outbreakEvent.getPathogen().getName())) {
                    double prevalenceThreat = 0;
                    //TODO prevalenceThreat might be too high that way
                    if (outbreakEvent.getPrevalence() != 0) {
                        prevalenceThreat = (1 + outbreakEvent.getPrevalence()) * FACTOR_IMPACT_OF_PREVALENCE;
                    }
                    threat *= prevalenceThreat;
                } else {
                    threat = 0;
                }
            }
            TODO: Split apart nearCities multiplicate with pathogens mobility
            ArrayList<City> nearCities = CityProvider.getNearCities(city);
            ArrayList<City> connectedCities = CityProvider.getConnectedCities(city);
            ArrayList<City> affectedCities;
            if (!nearCities.isEmpty() || !connectedCities.isEmpty()) {
                affectedCities = mergeArrayListOfCityAndRemoveDuplicates(nearCities, connectedCities);
                for (City affectedCity : affectedCities) {
                    if (!affectedCity.hasCityPathogenOutbreak(pathogen)) {
                        double affectedCityThreat = ThreatIndicator.getCityThreatIndicator(affectedCity);
                        threat *= FACTOR_REACHABLE_CITY * (pathogenThreat * affectedCityThreat);
                    }
                }
            }*//*

        }
        threat *= FACTOR_DEPLOY_MEDICATION;
        //System.out.println(threat + " calculateDeployMedication");*/
        return threat;
    }

    public double calculatePutUnderQuarantine(City city, int rounds) {
        double threat = getThreatOfCityAndPathogens(city);
        threat = getThreatBoostedByRounds(threat, rounds, FACTOR_BOOST_ROUND_THREAT_PUT_UNDER_QUARANTINE);
        threat *= FACTOR_PUT_UNDER_QUARANTINE;
        //System.out.println(threat + " calculatePutUnderQuarantine");




        /*boolean inDanger = false;
        double threat = 1;
        double cityThreat = ThreatIndicator.getCityThreatIndicator(city);
        ArrayList<Double> pathogensThreat = new ArrayList<>();
        ArrayList<Pathogen> pathogens = city.getPathogensInCity();
        ArrayList<Event> events = city.getEventsByType("outbreak");
        double prevalence = 0;
        if (!pathogens.isEmpty()) {
            inDanger = true;
            for (Pathogen pathogen : pathogens) {
                for (Event event : events){
                    OutbreakEvent outbreakEvent = (OutbreakEvent) event;
                    if (outbreakEvent.getPathogen().getName().equals(pathogen.getName())){
                        prevalence += outbreakEvent.getPrevalence();
                    }
                }
                double pathogenThreat = ThreatIndicator.getPathogenThreatIndicator(pathogen);
                pathogensThreat.add(pathogenThreat);
            }
        }
        ArrayList<City> nearCities = CityProvider.getNearCities(city);
        ArrayList<City> connectedCities = CityProvider.getConnectedCities(city);
        ArrayList<City> affectedCities;
        affectedCities = mergeArrayListOfCityAndRemoveDuplicates(nearCities, connectedCities);
        int affectedCityCounter = 0;
        for (City affectedCity : affectedCities) {
            boolean affectedCityCounterIncrease = false;
            for (Pathogen pathogen : pathogens) {
                if (affectedCity.hasCityPathogenOutbreak(pathogen)) {
                    affectedCityCounterIncrease = true;
                }
            }
            if (affectedCityCounterIncrease) {
                affectedCityCounter++;
            }
        }
        if (!pathogensThreat.isEmpty()) {
            threat *= getPathogenCityThreat(cityThreat, pathogensThreat);
            threat *= 1 + (FACTOR_IMPACT_OF_PREVALENCE * prevalence);
        }
        if (affectedCityCounter > affectedCities.size() / 2) {
            threat *= FACTOR_TOO_MANY_REACHABLE_CITIES_ALREADY_INFECTED;
        }
        if (!inDanger) threat = 0;
        threat = getThreatBoostedByRounds(threat, rounds);
        threat *= FACTOR_PUT_UNDER_QUARANTINE;
        //System.out.println(threat + " calculatePutUnderQuarantine");*/
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
        threat = getThreatBoostedByRounds(threat, rounds, FACTOR_BOOST_ROUND_THREAT_CLOSE_AIRPORT);
        threat = threat * FACTOR_CLOSE_AIRPORT;
        //System.out.println(threat + " calculateCloseAirport");



        /*boolean cityIsInfected = false;
        double threat = 1;
        double cityThreat = ThreatIndicator.getCityThreatIndicator(city);
        //Wie geht unserer Stadt mit den vorhandenen Threats um
        ArrayList<Pathogen> pathogensInCity = city.getPathogensInCity();
        if (!pathogensInCity.isEmpty()) {
            cityIsInfected = true;
            double arrayListTreat = getThreatOfCityAndPathogens(pathogensInCity, city);
            threat *= arrayListTreat;
            *//*ArrayList<Double> pathogensThreat = new ArrayList<>();
            for (Pathogen pathogenInCity : pathogensInCity) {
                pathogensThreat.add(ThreatIndicator.getPathogenThreatIndicator(pathogenInCity));
            }
            threat *= getPathogenCityThreat(cityThreat, pathogensThreat);*//*
        }
        //Wie schlimm wäre es, wenn das Pathogen auf umliegende Städte überschwappt
        //ArrayList<City> nearCities = CityProvider.getNearCities(city);
        ArrayList<City> connectedCities = CityProvider.getConnectedCities(city);
        //ArrayList<City> reachableCities = mergeArrayListOfCityAndRemoveDuplicates(nearCities, connectedCities);
        double connectedCitiesThreat = 1;
        int connectedCityCounter = 0;
        for (City connectedCity : connectedCities) {
            for (Pathogen pathogenInCity : pathogensInCity) {
                if (!connectedCity.hasCityPathogenOutbreak(pathogenInCity)) {
                    double connectedCityThreat = ThreatIndicator.getCityThreatIndicator(connectedCity);
                    double pathogenInCityThreat = ThreatIndicator.getPathogenThreatIndicator(pathogenInCity);
                    connectedCitiesThreat += FACTOR_AIRPORT_REACHABLE_CITY * (connectedCityThreat * pathogenInCityThreat);
                    connectedCityCounter++;
                }
            }
        }
        if (connectedCityCounter != 0) {
            threat *= (connectedCitiesThreat / connectedCityCounter);
        }
        //Wie schlimm wäre es, wenn ein Pathogen von einer anderen Stadt in unsere kommt
        ArrayList<City> citiesWithConnectionToCurrentCity = CityProvider.getCitiesWhichHaveConnectionToGivenCity(city);
        double connectedCityThreat = 1;
        int connectedCityIncomingCounter = 0;
        for (City cityWithConnectionToCurrentCity : citiesWithConnectionToCurrentCity) {
            ArrayList<Pathogen> pathogensInCityWithConnection = cityWithConnectionToCurrentCity.getPathogensInCity();
            if (!pathogensInCityWithConnection.isEmpty()) {
                for (Pathogen pathogenInCityWithConnection : pathogensInCityWithConnection) {
                    if (!city.hasCityPathogenOutbreak(pathogenInCityWithConnection)) {
                        cityIsInfected = true;
                        double pathogenThreat = ThreatIndicator.getPathogenThreatIndicator(pathogenInCityWithConnection);
                        connectedCityThreat += FACTOR_AIRPORT_REACHABLE_CITY * (pathogenThreat * cityThreat);
                        connectedCityIncomingCounter++;
                    }
                }
            }
        }*//*
        double test = 1;
        if (connectedCityThreat * connectedCityThreat > 500) {
            test += 0.5;
        } else if (connectedCityThreat * connectedCityThreat > 1000) {
            test += 0.75;
        }*//*
        if (connectedCityIncomingCounter != 0) {
            threat *= (connectedCityThreat / connectedCityIncomingCounter);
        }
        if (!cityIsInfected) threat = 0;
        threat = getThreatBoostedByRounds(threat, rounds);
        threat = threat * FACTOR_CLOSE_AIRPORT;
        //System.out.println(threat + " calculateCloseAirport");*/
        return threat;
    }

    public double calculateCloseConnection(City fromCity, City toCity, int rounds) {
        double threat = getThreatOfCityAndPathogens(fromCity);
        threat = combineThreats(threat, ThreatIndicator.getCityThreatIndicator(toCity));
        threat = getThreatBoostedByRounds(threat, rounds, FACTOR_BOOST_ROUND_THREAT_CLOSE_CONNECTION);
        threat *= FACTOR_CLOSE_CONNECTION;


        //System.out.println(threat + " calculateCloseConnection");
        /*
        boolean fromCityIsInfected = false;
        double threat = 1;
        double fromCityThreat = ThreatIndicator.getCityThreatIndicator(fromCity);
        double toCityThreat = ThreatIndicator.getCityThreatIndicator(toCity);
        //Wie geht unserer Stadt mit den vorhandenen Threats um
        ArrayList<Pathogen> pathogensInFromCity = fromCity.getPathogensInCity();
        if (!pathogensInFromCity.isEmpty()) {
            fromCityIsInfected = true;
            ArrayList<Double> pathogensInFromCityThreat = new ArrayList<>();
            ArrayList<Double> pathogensNotInToCityThreat = new ArrayList<>();
            for (Pathogen pathogenInFromCity : pathogensInFromCity) {
                pathogensInFromCityThreat.add(ThreatIndicator.getPathogenThreatIndicator(pathogenInFromCity));
                if (!toCity.hasCityPathogenOutbreak(pathogenInFromCity)) {
                    pathogensNotInToCityThreat.add(ThreatIndicator.getPathogenThreatIndicator(pathogenInFromCity));
                }
            }
            threat *= getPathogenCityThreat(fromCityThreat, pathogensInFromCityThreat);
            if (!pathogensNotInToCityThreat.isEmpty()) {
                threat *= getPathogenCityThreat(toCityThreat, pathogensNotInToCityThreat);
            }
        }
        //Wie stark ist Stadt 2 zur Zeit schon belastet
        ArrayList<Pathogen> pathogensInToCity = toCity.getPathogensInCity();
        if (!pathogensInToCity.isEmpty() && fromCityIsInfected) {
            //threat *= getThreatOfCityAndPathogens(pathogensInFromCity, toCity);
           *//* ArrayList<Double> pathogensInToCityThreat = new ArrayList<>();
            for (Pathogen pathogenInToCity : pathogensInToCity) {
                pathogensInToCityThreat.add(ThreatIndicator.getPathogenThreatIndicator(pathogenInToCity));
            }
            threat *= getPathogenCityThreat(toCityThreat, pathogensInToCityThreat);*//*
        }
        if (!fromCityIsInfected) threat = 0;
        threat = getThreatBoostedByRounds(threat, rounds, FACTOR_BOOST_ROUND_THREAT_CLOSE_CONNECTION);
        threat *= FACTOR_CLOSE_CONNECTION;
        //System.out.println(threat + " calculateCloseConnection");*/
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
        ArrayList<Event> events = city.getEventsByType("outbreak");
        double prevalenceFactor = 1;
        for (Event event : events) {
            OutbreakEvent outbreakEvent = (OutbreakEvent) event;
            if (outbreakEvent.getPathogen().getName().equals(pathogen.getName())) {
                prevalenceFactor = PREVALENCE_FACTOR_BOOSTER + 1 - outbreakEvent.getPrevalence();
            }
        }
        threat = combineThreats(threat, prevalenceFactor);
        threat *= FACTOR_DEPLOY_VACCINE;
        //System.out.println(threat + " calculateDeployVaccine");


        /*double threat = 1;
        double pathogenThreat = ThreatIndicator.getPathogenThreatIndicator(pathogen);
        double cityThreat = ThreatIndicator.getCityThreatIndicator(city);
        threat *= pathogenThreat * cityThreat;
        //Je mehr Leute infiziert sind, desto weniger bringt der Impfstoff (betrifft nur Nicht-Infizierte)
        ArrayList<Event> events = city.getEventsByType("outbreak");
        for (Event event : events) {
            OutbreakEvent outbreakEvent = (OutbreakEvent) event;
            if (pathogen.getName().equals(outbreakEvent.getPathogen().getName())) {
                //TODO prevalenceThreat might be too high that way
                double prevalenceThreat = (1.5 - outbreakEvent.getPrevalence());
                threat *= prevalenceThreat;
            }
        }
        ArrayList<City> nearCities = CityProvider.getNearCities(city);
        ArrayList<City> connectedCities = CityProvider.getConnectedCities(city);
        ArrayList<City> reachableCities;
        if (city.hasCityPathogenOutbreak(pathogen)) {
            double averageCityThreat = 0;
            int cityCount = 0;
            if (!nearCities.isEmpty() || !connectedCities.isEmpty()) {
                reachableCities = mergeArrayListOfCityAndRemoveDuplicates(nearCities, connectedCities);
                for (City reachableCity : reachableCities) {
                    //Wenn die Ausbreitung in der Stadt eingedämpft wird, welche Auswirkung hätte dies auf umliegende Städte
                    if (!reachableCity.hasCityPathogenOutbreak(pathogen)) {
                        double reachableCityThreat = ThreatIndicator.getCityThreatIndicator(reachableCity);
                        averageCityThreat += pathogenThreat * reachableCityThreat;
                        cityCount++;
                    }
                }
            }
            averageCityThreat = average(averageCityThreat, cityCount);
            threat *= FACTOR_REACHABLE_CITY * averageCityThreat;
        } else {
            ArrayList<City> citiesWithConnectionToCurrentCity = CityProvider.getCitiesWhichHaveConnectionToGivenCity(city);
            if (!citiesWithConnectionToCurrentCity.isEmpty()) {
                threat *= getThreatOfCityWithPathogen(FACTOR_AIRPORT_REACHABLE_CITY, citiesWithConnectionToCurrentCity, pathogen, pathogenThreat);
                *//*for (City cityWithConnectionToCurrentCity : citiesWithConnectionToCurrentCity) {
                    if (cityWithConnectionToCurrentCity.hasCityPathogenOutbreak(pathogen)) {
                        double gameCityThreat = ThreatIndicator.getCityThreatIndicator(cityWithConnectionToCurrentCity);
                        threat *= (FACTOR_AIRPORT_REACHABLE_CITY_HAS_PATHOGEN * (pathogenThreat * gameCityThreat));
                    }
                }*//*
            }
            //TODO: Multiply with pathogen´s mobility
            if (!nearCities.isEmpty()) {
                threat *= getThreatOfCityWithPathogen(FACTOR_NEAR_CITY_HAS_PATHOGEN, nearCities, pathogen, pathogenThreat);
            }
        }
        threat *= FACTOR_DEPLOY_VACCINE;
        //System.out.println(threat + " calculateDeployVaccine");*/
        return threat;
    }

    public double calculateExertInfluence(City city) {
        double threat = randomlyChangeValueThreat(city.getEconomy(), false);
        threat *= FACTOR_EXERT_INFLUENCE;
        //System.out.println(threat + " calculateExertInfluence");
        return threat;
    }

    public double calculateCallElection(City city) {
        double threat = randomlyChangeValueThreat(city.getGovernment(), false);
        threat *= FACTOR_CALL_ELECTION;
        //System.out.println(threat + " calculateCallElection");
        return threat;
    }

    public double calculateApplyHygienicMeasures(City city) {
        double threat = randomlyChangeValueThreat(city.getHygiene(), true);
        threat *= FACTOR_APPLY_HYGIENIC_MEASURES;
        //System.out.println(threat + " calculateApplyHygienicMeasures");




        /*double cityHygiene = city.getHygiene();
        threat *= randomlyChangeValueThreat(cityHygiene, true);
        ArrayList<Event> events = city.getEventsByType("outbreak");
        double prevalence = 0;
        ArrayList<Pathogen> pathogensInCity = city.getPathogensInCity();
        for (Pathogen pathogenInCity : pathogensInCity) {
            for (Event event : events) {
                OutbreakEvent outbreakEvent = (OutbreakEvent) event;
                if (pathogenInCity.getName().equals(outbreakEvent.getPathogen().getName())) {
                    prevalence += outbreakEvent.getPrevalence();
                }
            }
        }
        threat *= (1 + (FACTOR_IMPACT_OF_PREVALENCE * (1 - prevalence)));
        threat *= FACTOR_APPLY_HYGIENIC_MEASURES;
        //System.out.println(threat + " calculateApplyHygienicMeasures");*/
        return threat;
    }

    public double calculateLaunchCampaign(City city) {
        double threat = randomlyChangeValueThreat(city.getAwareness(), true);
        /*
        double threat = 1;
        double cityAwareness = city.getAwareness();
        threat *= randomlyChangeValueThreat(cityAwareness, true);
        */
        threat *= FACTOR_LAUNCH_CAMPAIGN;
        //System.out.println(threat + " calculateLaunchCampaign");
        return threat;
    }

    public double calculateEndRound() {
        double threat = FACTOR_END_ROUND;
        if (GameProvider.getGame().getPoints() > 25) threat = 0;
        /*double worldPopulation = 0;
        double worldwideInfected = 0;
        ArrayList<City> cities = CityProvider.getCities();
        for (City city : cities) {
            worldPopulation += city.getPopulation();
            double prevalence = 0;
            ArrayList<Event> events = city.getEventsByType("outbreak");
            for (Event event : events) {
                OutbreakEvent outbreakEvent = (OutbreakEvent) event;
                prevalence += outbreakEvent.getPrevalence();
            }
            worldwideInfected += (prevalence * city.getPopulation());
        }

        if (worldwideInfected > (worldPopulation * PERCENTAGE_INFECTED_WORLD_POPULATION)){
            threat *= FACTOR_TOO_MANY_INFECTED;
        }
*/
        return threat;
    }

    private double randomlyChangeValueThreat(Double value, Boolean justIncrease) {
        double threat = 1;
        if (value == ValueUtility.getVeryHighValueCity() || value == ValueUtility.getHighValueCity()) {
            threat *= 0;
        /*} else if (value == ValueUtility.getHighValueCity()) {
            if (justIncrease) {
                threat *= 0.9;
            } else {
                threat *= 0.25;
            }*/
        } else if (value == ValueUtility.getMidValue()) {
            if (justIncrease) {
                threat *= 1.1;
            } else {
                threat *= 1;
            }
        } else if (value == ValueUtility.getLowValueCity()) {
            threat *= 1.2;
        } else if (value == ValueUtility.getVeryLowValueCity()) {
            threat *= 1.3;
        }
        return threat;
    }

    private double getPathogenCityThreat(double cityThreat, ArrayList<Double> pathogensThreat) {
        double threat = 0;
        for (double pathogenThreat : pathogensThreat) {
            threat += cityThreat * pathogenThreat;
        }
        return average(threat, pathogensThreat.size());
    }

    private ArrayList<City> mergeArrayListOfCityAndRemoveDuplicates(ArrayList<City> arrayListOne, ArrayList<City> arrayListTwo) {
        if (arrayListOne == null) {
            return arrayListTwo;
        } else if (arrayListTwo == null) {
            return arrayListOne;
        } else {
            for (City connectedCity : arrayListTwo) {
                if (!arrayListOne.contains(connectedCity)) {
                    arrayListOne.add(connectedCity);
                }
            }
            return arrayListOne;
        }
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

        /*double threat = 1;
        ArrayList<Double> pathogensInToCityThreat = new ArrayList<>();
        for (Pathogen pathogenInToCity : pathogens) {
            pathogensInToCityThreat.add(ThreatIndicator.getPathogenThreatIndicator(pathogenInToCity));
        }
        threat *= getPathogenCityThreat(ThreatIndicator.getCityThreatIndicator(city), pathogensInToCityThreat);
*/
        return threat;
    }

    private double getThreatOfCityWithPathogen(double factor, ArrayList<City> cities, Pathogen pathogen, double pathogenThreat) {
        double threat = 0;
        int cityCount = 0;
        for (City cityWithConnectionToCurrentCity : cities) {
            if (cityWithConnectionToCurrentCity.hasCityPathogenOutbreak(pathogen)) {
                double gameCityThreat = ThreatIndicator.getCityThreatIndicator(cityWithConnectionToCurrentCity);
                threat += pathogenThreat * gameCityThreat;
            }
        }
        return average(threat, cityCount) * factor;
    }

    private double getThreatBoostedByRounds(double threat, int rounds, double factor){
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
