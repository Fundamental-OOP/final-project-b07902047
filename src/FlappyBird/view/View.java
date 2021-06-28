package FlappyBird.view;

import FlappyBird.Config;
import FlappyBird.controller.Controller;
import FlappyBird.events.EventManager;
import FlappyBird.events.Listener;
import FlappyBird.events.*;
import FlappyBird.models.Model;
import FlappyBird.states.State;

import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Draws the model state onto the screen.
 */
public class View implements Listener {
    boolean isInitialized = false;

    Model model;
    Controller controller;
    Config config;

    JFrame jFrame;
    Renderer renderer;

    public View(Model model, Controller controller, Config config) {
        EventManager.registerListener(this);
        this.model = model;
        this.controller = controller;
        this.config = config;
    }

    public View initialize() {
        renderer = new Renderer(model, config);
        jFrame = new JFrame("Flappy Bird");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setSize(config.getScreenWidth(), config.getScreenHeight());
        jFrame.setResizable(false);
        jFrame.setVisible(true);
        jFrame.addKeyListener(new KeyboardEventListener(controller));

        jFrame.add(renderer);

        this.isInitialized = true;

        return this;
    }

    @Override
    public void onEvent(BaseEvent event) {
        if (event instanceof TickEvent && this.isInitialized) {
            this.renderer.render();
        } else if (event instanceof InitializeEvent) {
            initialize();
        } else if (event instanceof ScoreEvent) {

        } else if (event instanceof HitEvent) {
            // play sound
        } else if (event instanceof QuitEvent) {
            this.isInitialized = false;
        }
    }
}

