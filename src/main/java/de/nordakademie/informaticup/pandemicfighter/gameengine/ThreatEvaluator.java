package de.nordakademie.informaticup.pandemicfighter.gameengine;

import de.nordakademie.informaticup.pandemicfighter.gameengine.elements.City;
import de.nordakademie.informaticup.pandemicfighter.gameengine.elements.events.Event;
import de.nordakademie.informaticup.pandemicfighter.gameengine.elements.events.OutbreakEvent;

import java.util.ArrayList;

import static de.nordakademie.informaticup.pandemicfighter.gameengine.ThreatIndicator.getPathogenThreatIndicatore;

public class ThreatEvaluator {

    public double calculateForPutUnderQurantine(City city){
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
    }

}
