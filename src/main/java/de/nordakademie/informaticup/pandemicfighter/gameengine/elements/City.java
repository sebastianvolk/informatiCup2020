package de.nordakademie.informaticup.pandemicfighter.gameengine.elements;

import de.nordakademie.informaticup.pandemicfighter.gameengine.elements.events.Event;
import de.nordakademie.informaticup.pandemicfighter.gameengine.elements.events.OutbreakEvent;

import java.util.ArrayList;

public class City extends ObjectWithEvents {
    private String name;
    private double latitude;
    private double longitude;
    private int population;
    private ArrayList<String> connections;
    private double economy;
    private double government;
    private double hygiene;
    private double awareness;

    public City(String name, double latitude, double longitude, ArrayList<String> connections) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.connections = connections;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public void setEconomy(double economy) {
        this.economy = economy;
    }

    public void setGovernment(double government) {
        this.government = government;
    }

    public void setHygiene(double hygiene) {
        this.hygiene = hygiene;
    }

    public void setAwareness(double awareness) {
        this.awareness = awareness;
    }

    public String getName() {
        return name;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public int getPopulation() {
        return population;
    }

    public ArrayList<String> getConnections() {
        return connections;
    }

    public double getEconomy() {
        return economy;
    }

    public double getGovernment() {
        return government;
    }

    public double getHygiene() {
        return hygiene;
    }

    public double getAwareness() {
        return awareness;
    }

    public ArrayList<Pathogen> getPathogensInCity() {
        ArrayList<Pathogen> pathogens = new ArrayList<>();
        ArrayList<Event> outbreakEvents = getEventsByType("outbreak");
        for (Event event : outbreakEvents) {
            OutbreakEvent outbreakEvent = (OutbreakEvent) event;
            Pathogen pathogen = outbreakEvent.getPathogen();
            pathogens.add(pathogen);
        }
        return pathogens;
    }

    public boolean hasCityPathogenOutbreak(Pathogen pathogen) {
        boolean result = false;
        ArrayList<Event> outbreakEvents = getEventsByType("outbreak");
        for (Event event : outbreakEvents) {
            OutbreakEvent outbreakEvent = (OutbreakEvent) event;
            if (pathogen.getName().equals(outbreakEvent.getPathogen().getName())) {
                result = true;
            }
        }
        return result;
    }
}
