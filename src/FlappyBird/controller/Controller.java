package FlappyBird.controller;

import FlappyBird.controller.policies.*;
import FlappyBird.events.*;
import FlappyBird.models.Model;

import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Controller implements Listener {
    Model model;
    KeyboardEventListener keyboardEventListener;

    private final ArrayList<ControllerEvent> queuedEventSet = new ArrayList<>();

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

    public KeyListener getKeyboardEventListener() {
        if (keyboardEventListener == null) {
            keyboardEventListener = new KeyboardEventListener(this, model)
                    .addPolicy(new JumpPolicy())
                    .addPolicy(new PausePolicy())
                    .addPolicy(new UnpausePolicy())
                    .addPolicy(new QuitEventPolicy())
                    .addPolicy(new RestartPolicy())
                    .addPolicy(new StartPlayPolicy());
        }
        return keyboardEventListener;
    }
}
