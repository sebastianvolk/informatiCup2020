package de.nordakademie.informaticup.pandemicfighter.gameengine.elements;

public class Pathogen {
    private String name;
    private double infectivity;
    private double mobility;
    private double duration;
    private double lethality;

    public Pathogen(String name, double infectivity, double mobility, double duration, double lethality) {
        this.name = name;
        this.infectivity = infectivity;
        this.mobility = mobility;
        this.duration = duration;
        this.lethality = lethality;
    }

    public String getName() {
        return name;
    }

    public double getInfectivity() {
        return infectivity;
    }

    public double getMobility() {
        return mobility;
    }

    public double getDuration() {
        return duration;
    }

    public double getLethality() {
        return lethality;
    }
}
