package de.nordakademie.informaticup.pandemicfighter.gameengine.events;

public abstract class Event {
    private String type;

    public Event(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
