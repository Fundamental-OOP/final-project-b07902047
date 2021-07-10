package FlappyBird.events;

/**
 * An base class for FlappyBird.models.Model, FlappyBird.view.View, and FlappyBird.controller.Controller
 */
public interface Listener {
    /**
     * Called by an FlappyBird.controller.Controller.EventManager to notify new event
     *
     * @param event
     */
    void onEvent(BaseEvent event);
}
