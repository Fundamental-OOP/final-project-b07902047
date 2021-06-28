package FlappyBird.events;

import java.util.ArrayList;

/**
 * Coordinate communication between the FlappyBird.models.Model, FlappyBird.view.View, and FlappyBird.controller.Controller.
 */
public class EventManager {
    private final ArrayList<Listener> listeners = new ArrayList<>();

    /**
     * Adds a listener to our list. It will receive FlappyBird.events through its notifyEvent(event) call.
     *
     * @param listener listener to add
     */
    public void registerListener(Listener listener) {
        this.listeners.add(listener);
    }

    /**
     * Remove a listener from our list.
     *
     * @param listener listener to remove
     */
    public void unregisterListener(Listener listener) {
        this.listeners.remove(listener);
    }

    /**
     * Post a new event to all listeners
     *
     * @param event event to be broadcast
     */
    public void postEvent(BaseEvent event) {
        if (!(event instanceof TickEvent)) {
            System.err.println(event);
        }

        for (Listener listener : listeners) {
            if (listener != null) {
                listener.onEvent(event);
            }
        }
    }
}
