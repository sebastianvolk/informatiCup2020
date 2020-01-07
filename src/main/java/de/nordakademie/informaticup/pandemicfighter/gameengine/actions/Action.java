package de.nordakademie.informaticup.pandemicfighter.gameengine.actions;

import com.google.gson.JsonObject;

public abstract class Action {
    private double threatIndicator;
    private int points;

    public Action() {
        this.threatIndicator = calculateThreatIndicator();
    }

    public abstract JsonObject toJson();

    protected abstract double calculateThreatIndicator();

    protected abstract int calculatePoints();

    protected void setPoints() {
        points = calculatePoints();
    }

    public double getThreatIndicator() {
        return threatIndicator;
    }

    public int getPoints() {
        return points;
    }
}
