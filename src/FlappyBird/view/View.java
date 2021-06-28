package FlappyBird.view;

import FlappyBird.controller.Controller;
import FlappyBird.events.EventManager;
import FlappyBird.events.Listener;
import FlappyBird.events.*;
import FlappyBird.models.Model;

import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Draws the model state onto the screen.
 */
public class View extends Listener {
    EventManager eventManager;
    Model model;
    boolean isInitialized;
    JFrame jFrame;
    Renderer renderer;
    Controller controller;

    public View(EventManager eventManager, Model model) {
        this.eventManager = eventManager;
        eventManager.registerListener(this);
        this.model = model;
        this.isInitialized = false;
    }

    public View initialize(int height, int width) {
        renderer = new Renderer();
        jFrame = new JFrame("Flappy FlappyBird.models.Bird");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setSize(width, height);
        jFrame.setResizable(false);
        jFrame.setVisible(true);
        jFrame.addKeyListener(new KeyboardEventListener(controller));
        return this;
    }

    public void repaint(Graphics g) {

    }

    @Override
    public void onEvent(BaseEvent event) {
        if (event instanceof TickEvent && this.isInitialized) {
            // rerender
        } else if (event instanceof InitializeEvent) {
            // TODO: connect with const or config
            initialize(360, 600);
            this.isInitialized = true;
        } else if (event instanceof ScoreEvent) {

        } else if (event instanceof HitEvent) {
            // play sound
        } else if (event instanceof QuitEvent) {
            this.isInitialized = false;
        }
    }

    public View setController(Controller controller) {
        this.controller = controller;
        return this;
    }
}

class Renderer extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        // TODO Auto-generated method stub
        super.paintComponent(g);
    }
}
