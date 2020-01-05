package de.nordakademie.informaticup.pandemicfighter.gameengine.elements;

import de.nordakademie.informaticup.pandemicfighter.gameengine.elements.events.Event;

import java.util.ArrayList;

public class City {
    private String name;
    private double latitude;
    private double longitude;
    private int population;
    private ArrayList<String> connections;
    private ArrayList<Event> events;
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

    public void setEvents(ArrayList<Event> events) {
        this.events = events;
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

    public ArrayList<Event> getEvents() {
        return events;
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
}
