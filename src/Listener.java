import events.BaseEvent;

/**
 * An base class for Model, View, and Controller
 */
public abstract class Listener implements Comparable<Listener> {
    String name;

    /**
     * Called by an EventManager to notify new event
     * @param event
     */
    abstract void notifyEvent(BaseEvent event);

    @Override
    public int compareTo(Listener o) {
        return name.compareTo(o.name);
    }
}
