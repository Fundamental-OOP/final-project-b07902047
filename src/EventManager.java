import events.BaseEvent;
import events.TickEvent;

import java.util.ArrayList;

/**
 * Coordinate communication between the Model, View, and Controller.
 */
public class EventManager {
    ArrayList<Listener> listeners;

    /**
     * Adds a listener to our list. It will receive events through it's notifyEvent(event) call.
     * @param listener listener to add
     */
    void registerListener(Listener listener) {
        this.listeners.add(listener);
    }

    /**
     * Remove a listener from our list.
     * @param listener listener to remove
     */
    void unregisterListerner(Listener listener) {
        this.listeners.remove(listener);
    }

    /**
     * Post a new event to all listeners
     * @param event event to be broadcast
     */
    void post(BaseEvent event) {
        if (!(event instanceof TickEvent)) {
            System.err.println(event);
        }
        for (Listener listener : listeners) if (listener != null) {
            listener.notifyEvent(event);
        }
    }
}
