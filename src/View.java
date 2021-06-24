import events.*;

/**
 * Draws the model state onto the screen.
 */
public class View extends Listener {
    EventManager eventManager;
    Model model;
    boolean isInitialized;

    public View(EventManager eventManager, Model model) {
        this.eventManager = eventManager;
        eventManager.registerListener(this);
        this.model = model;
        this.isInitialized = false;
    }

    @Override
    void notifyEvent(BaseEvent event) {
        if (event instanceof TickEvent && this.isInitialized) {

        } else if (event instanceof InitializeEvent) {
            this.isInitialized = true;
        } else if (event instanceof JumpEvent) {

        } else if (event instanceof ScoreEvent) {

        } else if (event instanceof HitEvent) {

        } else if (event instanceof QuitEvent) {
            this.isInitialized = false;
        }
    }
}
