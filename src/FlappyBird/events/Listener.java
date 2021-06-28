package FlappyBird.events;

/**
 * An base class for FlappyBird.models.Model, FlappyBird.view.View, and FlappyBird.controller.Controller
 */
public abstract class Listener implements Comparable<Listener> {
    protected String name;

    /**
     * Called by an FlappyBird.controller.Controller.EventManager to notify new event
     *
     * @param event
     */
    protected abstract void onEvent(BaseEvent event);

    @Override
    public int compareTo(Listener o) {
        return name.compareTo(o.name);
    }
}
