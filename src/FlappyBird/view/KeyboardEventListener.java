package FlappyBird.view;

import FlappyBird.controller.Controller;
import FlappyBird.events.*;
import FlappyBird.models.Model;
import FlappyBird.states.State;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import FlappyBird.events.JumpEvent;
import FlappyBird.events.QuitEvent;


public class KeyboardEventListener implements KeyListener {
    private Controller controller;
    private Model model;

    public KeyboardEventListener(Controller controller, Model model) {
        this.controller = controller;
        this.model = model;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        setControllerEvent(e.getKeyCode(),model.getState());
    }

    private void setControllerEvent(int keyCode, State state) {
        switch (model.getState()) {
            case STATE_MENU: getEventOnMenu(keyCode); break;
            case STATE_PLAY: getEventOnPlay(keyCode); break;
            case STATE_STOP: getEventOnStop(keyCode); break;
            case STATE_DEAD: getEventOnDead(keyCode); break;
        }
    }

    private void getEventOnMenu(int keyCode) {
        switch (keyCode) {
            case KeyEvent.VK_ESCAPE: controller.addQueuedEvent(new StateChangeEvent(null)); break;
            case KeyEvent.VK_SPACE: controller.addQueuedEvent(new StateChangeEvent(State.STATE_PLAY)); break;
        };
    }

    private void getEventOnPlay(int keyCode) {
        switch (keyCode) {
            case KeyEvent.VK_ESCAPE:
                controller.addQueuedEvent(new StateChangeEvent(null));
                controller.addQueuedEvent(new InitializeEvent());
                break;
            case KeyEvent.VK_P:
                controller.addQueuedEvent(new StateChangeEvent(State.STATE_STOP));
                break;
            case KeyEvent.VK_SPACE:
            case KeyEvent.VK_UP:
                controller.addQueuedEvent(new JumpEvent());
                break;
        };
    }


    private void getEventOnStop(int keyCode) {
        switch (keyCode) {
            case KeyEvent.VK_P:
                controller.addQueuedEvent(new StateChangeEvent(null));
                break;
        };
    }


    private void getEventOnDead(int keyCode) {
        switch (keyCode){
            case KeyEvent.VK_ESCAPE:
                controller.addQueuedEvent(new StateChangeEvent(null));
                controller.addQueuedEvent(new StateChangeEvent(null));
            case KeyEvent.VK_SPACE:
                // TODO:
                // controller.addQueuedEvent(new StateChangeEvent(null));
                // controller.addQueuedEvent(new InitializeEvent());

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
