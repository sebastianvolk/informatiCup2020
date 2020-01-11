package de.nordakademie.informaticup.pandemicfighter.gameengine;

import de.nordakademie.informaticup.pandemicfighter.gameengine.elements.City;
import de.nordakademie.informaticup.pandemicfighter.gameengine.elements.Pathogen;
import de.nordakademie.informaticup.pandemicfighter.gameengine.elements.events.Event;
import de.nordakademie.informaticup.pandemicfighter.gameengine.elements.events.OutbreakEvent;
import de.nordakademie.informaticup.pandemicfighter.gameengine.provider.CityProvider;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class ThreatEvaluator {
    private static final double FACTOR_OUTBREAK = 1.05;
    private static final double FACTOR_REACHABLE_CITY = 1.02;
    private static final double FACTOR_AIRPORT_REACHABLE_CITY = 1.02;
    private static final double FACTOR_NEAR_CITY_HAS_PATHOGEN = 1.01;
    private static final double FACTOR_IMPACT_OF_PREVALENCE = 1.1;
    private static final double FACTOR_TOO_MANY_REACHABLE_CITIES_ALREADY_INFECTED = 0.95;

    private static final double THREAT_THRESHOLD = 14;
    private static final double FACTOR_BOOST_ROUND_THREAT = 1.5;

    private static final double PERCENTAGE_INFECTED_WORLD_POPULATION = 0.3;
    private static final double FACTOR_TOO_MANY_INFECTED = 2;
    private static final double FACTOR_END_ROUND = 2.5;
    //private static final double FACTOR_END_ROUND = 3; DA HAT ER GESPART

    private static final double FACTOR_DEVELOP_MEDICATION = 10;
    private static final double FACTOR_DEPLOY_MEDICATION = 0.9;
    private static final double FACTOR_PUT_UNDER_QUARANTINE = 5;
    private static final double FACTOR_CLOSE_AIRPORT = 0.95;
    private static final double FACTOR_CLOSE_CONNECTION = 0.9;
    private static final double FACTOR_DEVELOP_VACCINE = 250;
    private static final double FACTOR_DEPLOY_VACCINE = 0.8;
    private static final double FACTOR_EXERT_INFLUENCE = 1.5;
    private static final double FACTOR_CALL_ELECTION = 1.5;
    private static final double FACTOR_APPLY_HYGIENIC_MEASURES = 0.8;
    private static final double FACTOR_LAUNCH_CAMPAIGN = 1.5;

    public double calculateDevelopMedication(Pathogen pathogen) {
        double threat = 1;
        double pathogenThreat = ThreatIndicator.getPathogenThreatIndicator(pathogen);
        threat *= pathogenThreat;
        ArrayList<City> cities = CityProvider.getCities();
        for (City city : cities) {
            ArrayList<Event> events = city.getEventsByType("outbreak");
            for (Event event : events) {
                OutbreakEvent outbreakEvent = (OutbreakEvent) event;
                if (pathogen.getName().equals(outbreakEvent.getPathogen().getName())) {
                    threat *= (FACTOR_OUTBREAK * ThreatIndicator.getCityThreatIndicator(city));
                }
            }
        }
        return threat * FACTOR_DEVELOP_MEDICATION;
    }

    public double calculateDeployMedication(City city, Pathogen pathogen) {
        double threat = 1;
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
            //TODO: Split apart nearCities multiplicate with pathogens mobility
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
            }
        }
        return threat * FACTOR_DEPLOY_MEDICATION;
    }

    public double calculatePutUnderQuarantine(City city, int rounds) {
        boolean inDanger = false;
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
        return threat * FACTOR_PUT_UNDER_QUARANTINE;
    }

    public double calculateCloseAirport(City city, int rounds) {
        boolean inDanger = false;
        double threat = 1;
        double cityThreat = ThreatIndicator.getCityThreatIndicator(city);
        //Wie geht unserer Stadt mit den vorhandenen Threats um
        ArrayList<Pathogen> pathogensInCity = city.getPathogensInCity();
        if (!pathogensInCity.isEmpty()) {
            inDanger = true;
            threat *= getArrayListOfThreats(pathogensInCity, city);
            /*ArrayList<Double> pathogensThreat = new ArrayList<>();
            for (Pathogen pathogenInCity : pathogensInCity) {
                pathogensThreat.add(ThreatIndicator.getPathogenThreatIndicator(pathogenInCity));
            }
            threat *= getPathogenCityThreat(cityThreat, pathogensThreat);*/
        }
        //Wie schlimm wäre es, wenn das Pathogen auf umliegende Städte überschwappt
        //ArrayList<City> nearCities = CityProvider.getNearCities(city);
        ArrayList<City> connectedCities = CityProvider.getConnectedCities(city);
        //ArrayList<City> reachableCities = mergeArrayListOfCityAndRemoveDuplicates(nearCities, connectedCities);
        for (City connectedCity : connectedCities) {
            for (Pathogen pathogenInCity : pathogensInCity) {
                if (!connectedCity.hasCityPathogenOutbreak(pathogenInCity)) {
                    double connectedCityThreat = ThreatIndicator.getCityThreatIndicator(connectedCity);
                    double pathogenInCityThreat = ThreatIndicator.getPathogenThreatIndicator(pathogenInCity);
                    threat *= (FACTOR_AIRPORT_REACHABLE_CITY * (connectedCityThreat * pathogenInCityThreat));
                }
            }
        }
        //Wie schlimm wäre es, wenn ein Pathogen von einer anderen Stadt in unsere kommt
        ArrayList<City> citiesWithConnectionToCurrentCity = CityProvider.getCitiesWhichHaveConnectionToGivenCity(city);
        for (City cityWithConnectionToCurrentCity : citiesWithConnectionToCurrentCity) {
            ArrayList<Pathogen> pathogensInCityWithConnection = cityWithConnectionToCurrentCity.getPathogensInCity();
            if (!pathogensInCityWithConnection.isEmpty()) {
                for (Pathogen pathogenInCityWithConnection : pathogensInCityWithConnection) {
                    if (!city.hasCityPathogenOutbreak(pathogenInCityWithConnection)) {
                        inDanger = true;
                        double pathogenThreat = ThreatIndicator.getPathogenThreatIndicator(pathogenInCityWithConnection);
                        threat *= (FACTOR_AIRPORT_REACHABLE_CITY * (pathogenThreat * cityThreat));
                    }
                }
            }
        }
        if (!inDanger) threat = 0;
        threat = getThreatBoostedByRounds(threat, rounds);
        return threat * FACTOR_CLOSE_AIRPORT;
    }

    public double calculateCloseConnection(City fromCity, City toCity, int rounds) {
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
            threat *= getArrayListOfThreats(pathogensInFromCity, toCity);
           /* ArrayList<Double> pathogensInToCityThreat = new ArrayList<>();
            for (Pathogen pathogenInToCity : pathogensInToCity) {
                pathogensInToCityThreat.add(ThreatIndicator.getPathogenThreatIndicator(pathogenInToCity));
            }
            threat *= getPathogenCityThreat(toCityThreat, pathogensInToCityThreat);*/
        }
        if (!fromCityIsInfected) threat = 0;
        threat = getThreatBoostedByRounds(threat, rounds);
        return threat * FACTOR_CLOSE_CONNECTION;
    }

    public double calculateDevelopVaccine(Pathogen pathogen) {
        double threat = 1;
        double pathogenThreat = ThreatIndicator.getPathogenThreatIndicator(pathogen);
        threat *= pathogenThreat;
        ArrayList<City> cities = CityProvider.getCities();
        for (City city : cities) {
            ArrayList<Pathogen> pathogensInCity = city.getPathogensInCity();
            if (pathogensInCity != null) {
                for (Pathogen pathogenInCity : pathogensInCity) {
                    if (pathogen.getName().equals(pathogenInCity.getName())) {
                        threat *= (FACTOR_OUTBREAK * ThreatIndicator.getCityThreatIndicator(city));
                    }
                }
            }
        }
        return threat * FACTOR_DEVELOP_VACCINE;
    }

    public double calculateDeployVaccine(City city, Pathogen pathogen) {
        double threat = 1;
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
            if (!nearCities.isEmpty() || !connectedCities.isEmpty()) {
                reachableCities = mergeArrayListOfCityAndRemoveDuplicates(nearCities, connectedCities);
                for (City reachableCity : reachableCities) {
                    //Wenn die Ausbreitung in der Stadt eingedämpft wird, welche Auswirkung hätte dies auf umliegende Städte
                    if (!reachableCity.hasCityPathogenOutbreak(pathogen)) {
                        double reachableCityThreat = ThreatIndicator.getCityThreatIndicator(reachableCity);
                        threat *= (FACTOR_REACHABLE_CITY * (pathogenThreat * reachableCityThreat));
                    }
                }
            }
        } else {
            ArrayList<City> citiesWithConnectionToCurrentCity = CityProvider.getCitiesWhichHaveConnectionToGivenCity(city);
            if (!citiesWithConnectionToCurrentCity.isEmpty()) {
                threat *= getThreatOfCityWithPathogen(FACTOR_AIRPORT_REACHABLE_CITY, citiesWithConnectionToCurrentCity, pathogen, pathogenThreat);
                /*for (City cityWithConnectionToCurrentCity : citiesWithConnectionToCurrentCity) {
                    if (cityWithConnectionToCurrentCity.hasCityPathogenOutbreak(pathogen)) {
                        double gameCityThreat = ThreatIndicator.getCityThreatIndicator(cityWithConnectionToCurrentCity);
                        threat *= (FACTOR_AIRPORT_REACHABLE_CITY_HAS_PATHOGEN * (pathogenThreat * gameCityThreat));
                    }
                }*/
            }
            //TODO: Multiply with pathogen´s mobility
            if (!nearCities.isEmpty()) {
                threat *= getThreatOfCityWithPathogen(FACTOR_NEAR_CITY_HAS_PATHOGEN, nearCities, pathogen, pathogenThreat);
            }
        }
        return threat * FACTOR_DEPLOY_VACCINE;
    }

    public double calculateExertInfluence(City city) {
        double threat = 1;
        double economy = city.getEconomy();
        threat *= randomlyChangeValueThreat(economy, false);

        return threat * FACTOR_EXERT_INFLUENCE;
    }

    public double calculateCallElection(City city) {
        double threat = 1;
        double government = city.getGovernment();
        threat *= randomlyChangeValueThreat(government, false);
        return threat * FACTOR_CALL_ELECTION;
    }

    public double calculateApplyHygienicMeasures(City city) {
        double threat = 1;
        double cityHygiene = city.getHygiene();
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
        return threat * FACTOR_APPLY_HYGIENIC_MEASURES;
    }

    public double calculateLaunchCampaign(City city) {
        double threat = 1;
        double cityAwareness = city.getAwareness();
        threat *= randomlyChangeValueThreat(cityAwareness, true);
        return threat * FACTOR_LAUNCH_CAMPAIGN;
    }

    public double calculateEndRound() {
        double threat = FACTOR_END_ROUND;
        double worldPopulation = 0;
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

        return threat;
    }

    private double randomlyChangeValueThreat(Double value, Boolean justIncrease) {
        double threat = 1;
        if (value == ValueUtility.getVeryHighValueCity()) {
            threat *= 0;
        } else if (value == ValueUtility.getHighValueCity()) {
            if (justIncrease) {
                threat *= 0.9;
            } else {
                threat *= 0.25;
            }
        } else if (value == ValueUtility.getMidValue()) {
            if (justIncrease) {
                threat *= 1;
            } else {
                threat *= 0.9;
            }
        } else if (value == ValueUtility.getLowValueCity()) {
            threat *= 1.05;
        } else if (value == ValueUtility.getVeryLowValueCity()) {
            threat *= 1.1;
        }
        return threat;
    }

    private double getPathogenCityThreat(double cityThreat, ArrayList<Double> pathogensThreat) {
        double threat = 1;
        for (double pathogenThreat : pathogensThreat) {
            threat *= cityThreat * pathogenThreat;
        }
        return threat;
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

    private double getArrayListOfThreats(ArrayList<Pathogen> pathogens, City city) {
        double threat = 1;
        ArrayList<Double> pathogensInToCityThreat = new ArrayList<>();
        for (Pathogen pathogenInToCity : pathogens) {
            pathogensInToCityThreat.add(ThreatIndicator.getPathogenThreatIndicator(pathogenInToCity));
        }
        threat *= getPathogenCityThreat(ThreatIndicator.getCityThreatIndicator(city), pathogensInToCityThreat);
        return threat;
    }

    private double getThreatOfCityWithPathogen(double factor, ArrayList<City> cities, Pathogen pathogen, double pathogenThreat) {
        double threat = 1;
        for (City cityWithConnectionToCurrentCity : cities) {
            if (cityWithConnectionToCurrentCity.hasCityPathogenOutbreak(pathogen)) {
                double gameCityThreat = ThreatIndicator.getCityThreatIndicator(cityWithConnectionToCurrentCity);
                threat *= (factor * (pathogenThreat * gameCityThreat));
            }
        }
        return threat;
    }

    private double getThreatBoostedByRounds(double threat, int rounds){
        return (threat / THREAT_THRESHOLD) * Math.pow(FACTOR_BOOST_ROUND_THREAT, rounds);
    }
}
