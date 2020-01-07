package de.nordakademie.informaticup.pandemicfighter.gameengine;

import de.nordakademie.informaticup.pandemicfighter.gameengine.elements.City;
import de.nordakademie.informaticup.pandemicfighter.gameengine.elements.Pathogen;

public class ThreatIndicator {

    private final static double FACTOR_PATHOGEN_INFECTIVITY = 0.8;
    private final static double FACTOR_PATHOGEN_MOBILITY = 0.6;
    private final static double FACTOR_PATHOGEN_DURATION = 0.4;
    private final static double FACTOR_PATHOGEN_LETHALITY = 0.7;

    private final static double FACTOR_CITY_ECONOMY = 0.6;
    private final static double FACTOR_CITY_GOVERNMENT = 0.7;
    private final static double FACTOR_CITY_HYGIENE = 0.8;
    private final static double FACTOR_CITY_AWARENESS = 0.5;

    public static double getPathogenThreatIndicatore(Pathogen pathogen) {
        double infectivityThreat = pathogen.getInfectivity() * FACTOR_PATHOGEN_INFECTIVITY;
        double mobilityThreat = pathogen.getMobility() * FACTOR_PATHOGEN_MOBILITY;
        double durationThreat = pathogen.getDuration() * FACTOR_PATHOGEN_DURATION;
        double lethalityThreat = pathogen.getLethality() * FACTOR_PATHOGEN_LETHALITY;
        return infectivityThreat+mobilityThreat+durationThreat+lethalityThreat;
    }

    public static double getCityThreatIndicator(City city){
        double economyThreat = city.getEconomy()*FACTOR_CITY_ECONOMY;
        double governmentThreat = city.getGovernment()*FACTOR_CITY_GOVERNMENT;
        double hygieneThreat = city.getHygiene()*FACTOR_CITY_HYGIENE;
        double awarenessThreat = city.getAwareness()*FACTOR_CITY_AWARENESS;
        return economyThreat+governmentThreat+hygieneThreat+awarenessThreat;
    }
}
