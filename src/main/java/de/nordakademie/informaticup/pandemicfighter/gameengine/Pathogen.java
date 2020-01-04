package de.nordakademie.informaticup.pandemicfighter.gameengine;

public class Pathogen {
    private String name;
    private String infectivity;
    private String mobility;
    private String duration;
    private String lethality;

    public Pathogen(String name, String infectivity, String mobility, String duration, String lethality) {
        this.name = name;
        this.infectivity = infectivity;
        this.mobility = mobility;
        this.duration = duration;
        this.lethality = lethality;
    }

    public String getName() {
        return name;
    }

    public String getInfectivity() {
        return infectivity;
    }

    public String getMobility() {
        return mobility;
    }

    public String getDuration() {
        return duration;
    }

    public String getLethality() {
        return lethality;
    }
}
