package FlappyBird.controller;

import FlappyBird.events.*;
import FlappyBird.models.Model;

import java.util.HashSet;
import java.util.Set;

public class Controller extends Listener {
    EventManager eventManager;
    Model model;
    private final Set<ControllerEvent> queuedEventSet = new HashSet<>();

    public Controller(EventManager eventManager, Model model) {
        this.eventManager = eventManager;
        eventManager.registerListener(this);
        this.model = model;
    }

    @Override
    protected void onEvent(BaseEvent event) {
        if (event instanceof TickEvent) {
            processTick();
        }
    }

    private void processTick() {
        queuedEventSet.forEach(eventManager::postEvent);
    }

    public void addQueuedEvent(ControllerEvent event) {
        queuedEventSet.add(event);
    }
}
