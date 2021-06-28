package FlappyBird.view;

import FlappyBird.controller.Controller;
import FlappyBird.events.JumpEvent;
import FlappyBird.events.QuitEvent;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardEventListener implements KeyListener {
    private Controller controller;

    public KeyboardEventListener() { }

    public KeyboardEventListener(Controller controller) { this.controller = controller; }

    KeyboardEventListener setController(Controller controller) {
        this.controller = controller;
        return this;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_ENTER:
                controller.addQueuedEvent(new JumpEvent());
                break;
            case KeyEvent.VK_ESCAPE:
                controller.addQueuedEvent(new QuitEvent());
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
