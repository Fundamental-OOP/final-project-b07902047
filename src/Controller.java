import events.BaseEvent;
import events.InitializeEvent;
import events.TickEvent;

public class Controller extends Listener {
    EventManager eventManager;
    Model model;

    public Controller(EventManager eventManager, Model model) {
        this.eventManager = eventManager;
        eventManager.registerListener(this);
        this.model = model;
    }

    @Override
    void notifyEvent(BaseEvent event) {
        if (event instanceof TickEvent) {
            // For each game tick. We check our keyboard presses here.

        }
    }
}
