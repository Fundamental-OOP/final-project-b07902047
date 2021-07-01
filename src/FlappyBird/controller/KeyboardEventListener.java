package FlappyBird.controller;

import FlappyBird.controller.Controller;
import FlappyBird.events.InitializeEvent;
import FlappyBird.events.JumpEvent;
import FlappyBird.events.QuitEvent;
import FlappyBird.events.StateChangeEvent;
import FlappyBird.models.Model;
import FlappyBird.models.states.*;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class KeyboardEventListener implements KeyListener {
    private final Controller controller;
    private final Model model;

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

    private void setControllerEvent(int keyCode, State state) {
        if(state instanceof MenuState){
            getEventOnMenu(keyCode);
        }
        else if(state instanceof PlayState){
            getEventOnPlay(keyCode);
        }
        else if(state instanceof StopState){
            getEventOnStop(keyCode);
        }
        else if(state instanceof DeadState){
            getEventOnDead(keyCode);
        }
    }

    private void getEventOnMenu(int keyCode) {
        switch (keyCode) {
            case KeyEvent.VK_ESCAPE:
                controller.addQueuedEvent(new QuitEvent());
                break;
            case KeyEvent.VK_SPACE:
                controller.addQueuedEvent(new StateChangeEvent(new PlayState()));
                break;
        }
    }

    private void getEventOnPlay(int keyCode) {
        switch (keyCode) {
            case KeyEvent.VK_ESCAPE:
                controller.addQueuedEvent(new StateChangeEvent(null));
                controller.addQueuedEvent(new InitializeEvent());
                break;
            case KeyEvent.VK_P:
                controller.addQueuedEvent(new StateChangeEvent(new StopState()));
                break;
            case KeyEvent.VK_SPACE:
            case KeyEvent.VK_UP:
                controller.addQueuedEvent(new JumpEvent());
                break;
        }
    }


    private void getEventOnStop(int keyCode) {
        if (keyCode == KeyEvent.VK_P) {
            controller.addQueuedEvent(new StateChangeEvent(null));
        }
    }


    private void getEventOnDead(int keyCode) {
        switch (keyCode) {
            case KeyEvent.VK_ESCAPE:
                controller.addQueuedEvent(new QuitEvent());
                break;
            case KeyEvent.VK_SPACE:
                controller.addQueuedEvent(new StateChangeEvent(null));
                controller.addQueuedEvent(new InitializeEvent());
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
