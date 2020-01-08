package de.nordakademie.informaticup.pandemicfighter.gameengine.elements;

import java.util.ArrayList;

public class Game extends ObjectWithEvents {
    private String outcome;
    private int round;
    private int points;
    private ArrayList<City> cities;

    public String getOutcome() {
        return outcome;
    }

    public void setOutcome(String outcome) {
        this.outcome = outcome;
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public ArrayList<City> getCities() {
        return cities;
    }

    public void setCities(ArrayList<City> cities) {
        this.cities = cities;
    }
}
