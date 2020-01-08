package de.nordakademie.informaticup.pandemicfighter.gameengine.elements;

import de.nordakademie.informaticup.pandemicfighter.gameengine.elements.events.Event;

import java.util.ArrayList;

public abstract class ObjectWithEvents {
    private ArrayList<Event> events;

    public ArrayList<Event> getEventsByType(String type) {
        ArrayList<Event> events = new ArrayList<>();
        if (this.events != null && type != null) {
            for (Event event : this.events) {
                if (type.equals(event.getType())) {
                    events.add(event);
                }
            }
        }
        return events;
    }

    public ArrayList<Event> getEvents() {
        return events;
    }

    public void setEvents(ArrayList<Event> events) {
        this.events = events;
    }
}
