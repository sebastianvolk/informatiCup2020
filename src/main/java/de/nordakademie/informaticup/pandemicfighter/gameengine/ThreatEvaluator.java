package de.nordakademie.informaticup.pandemicfighter.gameengine;

import de.nordakademie.informaticup.pandemicfighter.gameengine.elements.City;
import de.nordakademie.informaticup.pandemicfighter.gameengine.elements.Pathogen;
import de.nordakademie.informaticup.pandemicfighter.gameengine.elements.events.Event;
import de.nordakademie.informaticup.pandemicfighter.gameengine.elements.events.OutbreakEvent;
import de.nordakademie.informaticup.pandemicfighter.gameengine.provider.CityProvider;

import java.util.ArrayList;

public class ThreatEvaluator {
    private static final double FACTOR_OUTBREAK = 1.05;
    private static final double FACTOR_REACHABLE_CITY = 1.05;
    private static final double FACTOR_AIRPORT_REACHABLE_CITY_HAS_PATHOGEN = 1.05;
    private static final double FACTOR_MOBIL_REACHABLE_CITY_HAS_PATHOGEN = 1.05;

    public double calculateForDevelopMedication(Pathogen pathogen) {
        double threat = 1;
        double pathogenThreat = ThreatIndicator.getPathogenThreatIndicator(pathogen);
        threat *= pathogenThreat;
        ArrayList<City> cities = CityProvider.getCities();
        for (City city : cities) {
            ArrayList<Event> events = city.getEvents();
            if (events != null) {
                for (Event event : events) {
                    if ("outbreak".equals(event.getType())) {
                        OutbreakEvent outbreakEvent = (OutbreakEvent) event;
                        if (pathogen.getName().equals(outbreakEvent.getPathogen().getName())) {
                            threat *= (FACTOR_OUTBREAK * ThreatIndicator.getCityThreatIndicator(city));
                            System.out.println(threat);
                        }
                    }
                }
            }
        }
        return threat;
    }

    public double calculateForDeployMedication(City city, Pathogen pathogen) {
        double threat = 1;
        double pathogenThreat = ThreatIndicator.getPathogenThreatIndicator(pathogen);
        double cityThreat = ThreatIndicator.getCityThreatIndicator(city);
        threat *= pathogenThreat * cityThreat;
        ArrayList<Event> events = city.getEvents();
        if (events != null) {
            for (Event event : events) {
                if ("outbreak".equals(event.getType())) {
                    OutbreakEvent outbreakEvent = (OutbreakEvent) event;
                    if (pathogen == outbreakEvent.getPathogen()) {
                        //TODO prevalenceThreat might be too high that way
                        double prevalenceThreat = 1 + outbreakEvent.getPrevalence();
                        threat *= prevalenceThreat;
                    }
                }
            }
        }
        ArrayList<City> nearCities = CityProvider.getNearCities(city);
        ArrayList<City> connectedCities = CityProvider.getConnectedCities(city);
        ArrayList<City> affectedCities;
        if (nearCities != null || connectedCities != null) {
            affectedCities = mergeArrayListOfCityAndRemoveDuplicates(nearCities, connectedCities);
            for (City affectedCity : affectedCities) {
                if (!affectedCity.hasCityPathogenOutbreak(pathogen)) {
                    double affectedCityThreat = ThreatIndicator.getCityThreatIndicator(affectedCity);
                    threat *= FACTOR_REACHABLE_CITY * (pathogenThreat * affectedCityThreat);
                }
            }
        }
        return threat;
    }

    public double calculateForPutUnderQurantine(City city) {
        double threat = 1;
        double cityThreat = ThreatIndicator.getCityThreatIndicator(city);
        ArrayList<Double> pathogensThreat = new ArrayList<>();
        ArrayList<Pathogen> pathogens = city.getPathogensInCity();
        ArrayList<Event> events = city.getEvents();
        if (pathogens != null) {
            for (Pathogen pathogen : pathogens) {
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

        }
        if (affectedCityCounter > affectedCities.size() / 2){
            threat/=2;
        }
            return threat;
    }

    public double calculateCloseAirportThreat(City city){
        double threat = 1;
        double cityThreat = ThreatIndicator.getCityThreatIndicator(city);
        //Wie geht unserer Stadt mit den vorhandenen Threats um
        ArrayList<Pathogen> pathogensInCity = city.getPathogensInCity();
        if (pathogensInCity!=null) {
            ArrayList<Double> pathogensThreat = new ArrayList<>();
            for (Pathogen pathogenInCity : pathogensInCity) {
                pathogensThreat.add(ThreatIndicator.getPathogenThreatIndicator(pathogenInCity));
            }
            threat *= getPathogenCityThreat(cityThreat, pathogensThreat);
        }

        //Wie schlimm wäre es, wenn das Pathogen auf umliegende Städte überschwappt
        ArrayList<City> nearCities = CityProvider.getNearCities(city);
        ArrayList<City> connectedCities = CityProvider.getConnectedCities(city);
        ArrayList<City> reachableCities = mergeArrayListOfCityAndRemoveDuplicates(nearCities, connectedCities);
        for (City reachableCity : reachableCities) {
            for (Pathogen pathogenInCity : pathogensInCity) {
                if (!reachableCity.hasCityPathogenOutbreak(pathogenInCity)){
                    double nearCityThreat = ThreatIndicator.getCityThreatIndicator(reachableCity);
                    double pathogenInCityThreat = ThreatIndicator.getPathogenThreatIndicator(pathogenInCity);
                    threat *= (FACTOR_REACHABLE_CITY * (nearCityThreat * pathogenInCityThreat));
                }
            }
        }

        //Wie schlimm wäre es, wenn ein Pathogen von einer anderen Stadt in unsere kommt
        ArrayList<City> citiesWithConnectionToCurrentCity = CityProvider.getCitiesWhichHaveConnectionToGivenCity(city);
        for (City cityWithConnectionToCurrentCity : citiesWithConnectionToCurrentCity) {
            ArrayList<Pathogen> pathogensInCityWithConnection = cityWithConnectionToCurrentCity.getPathogensInCity();
            if (pathogensInCityWithConnection!=null) {
                for (Pathogen pathogenInCityWithConnection : pathogensInCityWithConnection) {
                    if (!city.hasCityPathogenOutbreak(pathogenInCityWithConnection)) {
                        double pathogenThreat = ThreatIndicator.getPathogenThreatIndicator(pathogenInCityWithConnection);
                        threat *= (FACTOR_AIRPORT_REACHABLE_CITY_HAS_PATHOGEN * (pathogenThreat * cityThreat));
                    }
                }
            }
        }
        return threat;
    }


    public double calculateForCloseConnection(City fromCity, City toCity){
        double threat = 1;
        double fromCityThreat = ThreatIndicator.getCityThreatIndicator(fromCity);
        double toCityThreat = ThreatIndicator.getCityThreatIndicator(toCity);
        //Wie geht unserer Stadt mit den vorhandenen Threats um
        ArrayList<Pathogen> pathogensInFromCity = fromCity.getPathogensInCity();
        if (pathogensInFromCity!=null) {
            ArrayList<Double> pathogensInFromCityThreat = new ArrayList<>();
            ArrayList<Double> pathogensNotInToCityThreat = new ArrayList<>();
            for (Pathogen pathogenInFromCity : pathogensInFromCity) {
                pathogensInFromCityThreat.add(ThreatIndicator.getPathogenThreatIndicator(pathogenInFromCity));
                if (!toCity.hasCityPathogenOutbreak(pathogenInFromCity)){
                    pathogensNotInToCityThreat.add(ThreatIndicator.getPathogenThreatIndicator(pathogenInFromCity));
                }
            }
            threat *= getPathogenCityThreat(fromCityThreat, pathogensInFromCityThreat);
            if (pathogensNotInToCityThreat!=null) {
                threat *= getPathogenCityThreat(toCityThreat, pathogensNotInToCityThreat);
            }
        }

        //Wie stark ist Stadt 2 zur Zeit schon belastet
        ArrayList<Pathogen> pathogensInToCity = toCity.getPathogensInCity();
        if (pathogensInToCity!=null) {
            ArrayList<Double> pathogensInToCityThreat = new ArrayList<>();
            for (Pathogen pathogenInToCity : pathogensInToCity) {
                pathogensInToCityThreat.add(ThreatIndicator.getPathogenThreatIndicator(pathogenInToCity));
            }
            threat *= getPathogenCityThreat(toCityThreat, pathogensInToCityThreat);
        }
        return threat;
    }

    public double calculateDevelopVaccine(Pathogen pathogen){
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
                        System.out.println(threat);
                    }
                }
            }
        }
        return threat;
    }

    public double calculateForDeployVaccin(City city, Pathogen pathogen) {
        double threat = 1;
        double pathogenThreat = ThreatIndicator.getPathogenThreatIndicator(pathogen);
        double cityThreat = ThreatIndicator.getCityThreatIndicator(city);
        threat *= pathogenThreat * cityThreat;
        //Je mehr Leute infiziert sind, desto weniger bringt der Impfstoff (betrifft nur Nicht-Infizierte)
        ArrayList<Event> events = city.getEvents();
        if (events != null) {
            for (Event event : events) {
                if ("outbreak".equals(event.getType())) {
                    OutbreakEvent outbreakEvent = (OutbreakEvent) event;
                    if (pathogen.getName().equals(outbreakEvent.getPathogen().getName())) {
                        //TODO prevalenceThreat might be too high that way
                        double prevalenceThreat = (1 - outbreakEvent.getPrevalence());
                        threat *= prevalenceThreat;
                    }
                }
            }
        }

        ArrayList<City> nearCities = CityProvider.getNearCities(city);
        ArrayList<City> connectedCities = CityProvider.getConnectedCities(city);
        ArrayList<City> reachableCities;
        if (city.hasCityPathogenOutbreak(pathogen)) {
            if (nearCities != null || connectedCities != null) {
                reachableCities = mergeArrayListOfCityAndRemoveDuplicates(nearCities, connectedCities);
                for (City reachableCity : reachableCities) {
                    //Wenn die Ausbreitung in der Stadt eingedämpft wird, welche Auswirkung hätte dies auf umliegende Städte
                    if (!reachableCity.hasCityPathogenOutbreak(pathogen)) {
                        double reachableCityThreat = ThreatIndicator.getCityThreatIndicator(reachableCity);
                        threat *= (FACTOR_REACHABLE_CITY * (pathogenThreat * reachableCityThreat));
                    }
                }
            }
        }
        else {
            ArrayList<City> citiesWithConnectionToCurrentCity = CityProvider.getCitiesWhichHaveConnectionToGivenCity(city);
            if (citiesWithConnectionToCurrentCity!=null) {
                for (City cityWithConnectionToCurrentCity : citiesWithConnectionToCurrentCity) {
                    if (cityWithConnectionToCurrentCity.hasCityPathogenOutbreak(pathogen)) {
                        double gameCityThreat = ThreatIndicator.getCityThreatIndicator(cityWithConnectionToCurrentCity);
                        threat *= (FACTOR_AIRPORT_REACHABLE_CITY_HAS_PATHOGEN * (pathogenThreat * gameCityThreat));
                    }
                }
            }
            //TODO: If there is no difference between reachable through airport or mobil, then change this part
            if (nearCities!=null){
                for (City nearCity : nearCities) {
                    if (nearCity.hasCityPathogenOutbreak(pathogen)) {
                        double nearCityThreat = ThreatIndicator.getCityThreatIndicator(nearCity);
                        threat *= (FACTOR_MOBIL_REACHABLE_CITY_HAS_PATHOGEN * (pathogenThreat * nearCityThreat));
                    }
                }
            }
        }

        return threat;
    }

    public double calculateEndRound(){
        double threat = 1;
        //TODO: needs to be implemented

        return threat;

    }


    public double calculateExertInfluence(){
        double threat = 1;
        //TODO: needs to be implemented

        return threat;

    }

    public double calculateCallElection(){
        double threat = 1;
        //TODO: needs to be implemented

        return threat;

    }

    public double calculateLunchCampaign(){
        double threat = 1;
        //TODO: needs to be implemented

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

}
