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

    public double calculateForDeployMedication(City city, Pathogen pathogen){
        double threat = 1;
        double pathogenThreat = ThreatIndicator.getPathogenThreatIndicator(pathogen);
        double cityThreat = ThreatIndicator.getCityThreatIndicator(city);
        threat *= pathogenThreat * cityThreat;

        ArrayList<Event> events = city.getEvents();
        if (events!=null) {
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

        ArrayList<City> affectedCities = CityProvider.getNearCities(city);
        ArrayList<City> connectedCities = CityProvider.getConnectedCities(city);
        if (affectedCities!=null) {
            if (connectedCities!=null) {
                for (City connectedCity : connectedCities) {
                    if (!affectedCities.contains(connectedCity)) {
                        affectedCities.add(connectedCity);
                    }
                }
            }
            for (City affectedCity : affectedCities) {
                ArrayList<Event> affectedCityEvents = affectedCity.getEvents();
                if (affectedCityEvents != null) {
                    boolean pathogenFound = false;
                    for (Event affectedCityEvent : affectedCityEvents) {
                        if ("outbreak".equals(affectedCityEvent.getType())) {
                            OutbreakEvent outbreakEvent = (OutbreakEvent) affectedCityEvent;
                            if (pathogen == outbreakEvent.getPathogen()) {
                                pathogenFound = true;
                            }
                        }
                    }
                    if (!pathogenFound) {
                        double affectedCityThreat = ThreatIndicator.getCityThreatIndicator(affectedCity);
                        threat *= FACTOR_AFFECTED_CITY * (pathogenThreat * affectedCityThreat);
                    }
                }
            }
        }
        return threat;
    }

    /*public double calculateForPutUnderQurantine(City city){
        double threat = 1;
        double threatCity = ThreatIndicator.getCityThreatIndicator(city);
        double threatPathogen;
        ArrayList<Event> events = city.getEvents();
        for(Event event: events){
            if("outbreak".equals(event.getType())){
                OutbreakEvent outbreakEvent = (OutbreakEvent) event;
               threatPathogen = ThreatIndicator.getPathogenThreatIndicatore(outbreakEvent.getPathogen());

            }
        }

        return threat;
    }*/

}
