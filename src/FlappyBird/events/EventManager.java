package FlappyBird.events;

import java.util.ArrayList;

/**
 * Coordinate communication between the FlappyBird.models.Model, FlappyBird.view.View, and FlappyBird.controller.Controller.
 */
public class EventManager {
    private static final ArrayList<Listener> listeners = new ArrayList<>();

    private EventManager() {}

    /**
     * Adds a listener to our list. It will receive FlappyBird.events through its notifyEvent(event) call.
     *
     * @param listener listener to add
     */
    public static void registerListener(Listener listener) { listeners.add(listener); }


    /**
     * Remove a listener from our list.
     *
     * @param listener listener to remove
     */
    public static void unregisterListener(Listener listener) { listeners.remove(listener); }

    /**
     * Post a new event to all listeners
     *
     * @param event event to be broadcast
     */
    public static void post(BaseEvent event) {
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
