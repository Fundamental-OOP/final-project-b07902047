package FlappyBird.controller;

import FlappyBird.controller.policies.KeyboardEventPolicy;
import FlappyBird.events.BaseEvent;
import FlappyBird.events.ControllerEvent;
import FlappyBird.models.Model;
import FlappyBird.models.states.State;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Optional;


public class KeyboardEventListener implements KeyListener {
    private final Controller controller;
    private final Model model;
    private KeyboardEventPolicy policy;

    public KeyboardEventListener(Controller controller, Model model) {
        this.controller = controller;
        this.model = model;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        setControllerEvent(e.getKeyCode(), model.getState());
    }

    public KeyboardEventListener addPolicy(KeyboardEventPolicy policy) {
        if (this.policy != null) {
            policy.setNextPolicy(this.policy);
        }
        this.policy = policy;
        return this;
    }

    private void setControllerEvent(int keyCode, State state) {
        Optional<BaseEvent> event = policy.parse(keyCode, state);
        event.ifPresent(baseEvent -> controller.addQueuedEvent((ControllerEvent) baseEvent));
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
