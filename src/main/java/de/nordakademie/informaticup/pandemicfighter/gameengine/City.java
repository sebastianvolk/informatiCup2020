package de.nordakademie.informaticup.pandemicfighter.gameengine;

import de.nordakademie.informaticup.pandemicfighter.gameengine.events.Event;

import java.util.ArrayList;

public class City {
    private String name;
    private String latitude;
    private String longitude;
    private int population;
    private ArrayList<String> connections;
    private ArrayList<Event> events;
    private String economy;
    private String government;
    private String hygiene;
    private String awareness;

    public City(String name, String latitude, String longitude, ArrayList<String> connections) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.connections = connections;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public void setEvents(ArrayList<Event> events) {
        this.events = events;
    }

    public void setEconomy(String economy) {
        this.economy = economy;
    }

    public void setGovernment(String government) {
        this.government = government;
    }

    public void setHygiene(String hygiene) {
        this.hygiene = hygiene;
    }

    public void setAwareness(String awareness) {
        this.awareness = awareness;
    }

    public String getName() {
        return name;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public int getPopulation() {
        return population;
    }

    public ArrayList<String> getConnections() {
        return connections;
    }

    public ArrayList<Event> getEvents() {
        return events;
    }

    public String getEconomy() {
        return economy;
    }

    public String getGovernment() {
        return government;
    }

    public String getHygiene() {
        return hygiene;
    }

    public String getAwareness() {
        return awareness;
    }
}
