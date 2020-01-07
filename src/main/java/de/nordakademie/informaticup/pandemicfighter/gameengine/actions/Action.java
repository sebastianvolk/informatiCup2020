package de.nordakademie.informaticup.pandemicfighter.gameengine.actions;

import com.google.gson.JsonObject;

public abstract class Action {
    private double threatIndicator;
    private int points;

    public abstract JsonObject toJson();

    protected abstract double calculateThreatIndicator();

    protected abstract int calculatePoints();

    protected void setCharacteristics() {
        points = calculatePoints();
        threatIndicator = calculateThreatIndicator();
    }

    public double getThreatIndicator() {
        return threatIndicator;
    }

    public int getPoints() {
        return points;
    }
}
