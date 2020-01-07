package de.nordakademie.informaticup.pandemicfighter.gameengine;

import de.nordakademie.informaticup.pandemicfighter.gameengine.elements.City;
import de.nordakademie.informaticup.pandemicfighter.gameengine.elements.Pathogen;
import de.nordakademie.informaticup.pandemicfighter.gameengine.elements.events.Event;
import de.nordakademie.informaticup.pandemicfighter.gameengine.elements.events.OutbreakEvent;
import de.nordakademie.informaticup.pandemicfighter.gameengine.provider.CityProvider;

import java.util.ArrayList;

public class ThreatEvaluator {
    private static final double FACTOR_OUTBREAK = 1.05;
    private static final double FACTOR_AFFECTED_CITY = 1.05;

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
                    threat *= FACTOR_AFFECTED_CITY * (pathogenThreat * affectedCityThreat);
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
