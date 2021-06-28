package FlappyBird.controller;

import FlappyBird.events.*;
import FlappyBird.models.Model;

import java.util.HashSet;
import java.util.Set;

public class Controller implements Listener {
    Model model;
    private final Set<ControllerEvent> queuedEventSet = new HashSet<>();

    public Controller(Model model) {
        EventManager.registerListener(this);
        this.model = model;
    }

    @Override
    public void onEvent(BaseEvent event) {
        if (event instanceof TickEvent) {
            processTick();
        }
    }

    private void processTick() {
        queuedEventSet.forEach(EventManager::post);
        queuedEventSet.clear();
    }

    public void addQueuedEvent(ControllerEvent event) {
        queuedEventSet.add(event);
    }
}
