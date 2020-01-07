package de.nordakademie.informaticup.pandemicfighter.gameengine;

import de.nordakademie.informaticup.pandemicfighter.gameengine.elements.City;
import de.nordakademie.informaticup.pandemicfighter.gameengine.elements.Pathogen;
import de.nordakademie.informaticup.pandemicfighter.gameengine.elements.events.Event;
import de.nordakademie.informaticup.pandemicfighter.gameengine.elements.events.OutbreakEvent;
import de.nordakademie.informaticup.pandemicfighter.gameengine.provider.CityProvider;

import java.util.ArrayList;

public class ThreatEvaluator {
    private static final double FACTOR_OUTBREAK = 1.05;

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
