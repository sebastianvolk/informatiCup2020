package de.nordakademie.informaticup.pandemicfighter.gameengine;

import de.nordakademie.informaticup.pandemicfighter.gameengine.elements.City;
import de.nordakademie.informaticup.pandemicfighter.gameengine.elements.Pathogen;

class ThreatIndicator {

    private final static double FACTOR_PATHOGEN_INFECTIVITY = 1.05;
    private final static double FACTOR_PATHOGEN_MOBILITY = 1;
    private final static double FACTOR_PATHOGEN_DURATION = 1;
    private final static double FACTOR_PATHOGEN_LETHALITY = 1.08;

    private final static double FACTOR_CITY_ECONOMY = 1;
    private final static double FACTOR_CITY_GOVERNMENT = 1;
    private final static double FACTOR_CITY_HYGIENE = 1.08;
    private final static double FACTOR_CITY_AWARENESS = 1.05;

    static double getPathogenThreatIndicator(Pathogen pathogen) {
        double infectivityThreat = pathogen.getInfectivity() * FACTOR_PATHOGEN_INFECTIVITY;
        double mobilityThreat = pathogen.getMobility() * FACTOR_PATHOGEN_MOBILITY;
        double durationThreat = pathogen.getDuration() * FACTOR_PATHOGEN_DURATION;
        double lethalityThreat = pathogen.getLethality() * FACTOR_PATHOGEN_LETHALITY;
        return infectivityThreat*mobilityThreat*durationThreat*lethalityThreat;
    }

    static double getCityThreatIndicator(City city){
        double economyThreat = city.getEconomy() * FACTOR_CITY_ECONOMY;
        double governmentThreat = city.getGovernment() * FACTOR_CITY_GOVERNMENT;
        double hygieneThreat = city.getHygiene() * FACTOR_CITY_HYGIENE;
        double awarenessThreat = city.getAwareness() * FACTOR_CITY_AWARENESS;
        return economyThreat * governmentThreat * hygieneThreat * awarenessThreat;
    }
}
