package de.nordakademie.informaticup.pandemicfighter.gameengine.threats;

public abstract class Threat {
    private double indicator;

    public Threat(double indicator) {
        this.indicator = indicator;
    }

    public double getIndicator() {
        return indicator;
    }

    public abstract void resolve();
}
