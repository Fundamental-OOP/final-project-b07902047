package FlappyBird.view;

import FlappyBird.Const;
import FlappyBird.controller.Controller;
import FlappyBird.events.EventManager;
import FlappyBird.events.Listener;
import FlappyBird.events.*;
import FlappyBird.models.Model;
import FlappyBird.view.components.BackgroundViewComponent;
import FlappyBird.view.components.BirdViewComponent;
import FlappyBird.view.components.GroundViewComponent;

import javax.swing.JFrame;

/**
 * Draws the model state onto the screen.
 */
public class View implements Listener {
    boolean isInitialized = false;

    Model model;
    Controller controller;

    JFrame jFrame;
    Renderer renderer;

    public View(Model model, Controller controller) {
        EventManager.registerListener(this);
        this.model = model;
        this.controller = controller;
    }

    public View initialize() {
        jFrame = new JFrame("Flappy Bird");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setSize(Const.screenX, Const.screenY);
        jFrame.setResizable(false);
        jFrame.setVisible(true);
        jFrame.addKeyListener(new KeyboardEventListener(controller, model));

        renderer = new Renderer(model);
        renderer.addViewComponent(new BackgroundViewComponent(/* model */));
        renderer.addViewComponent(new GroundViewComponent(/* model */));
        renderer.addViewComponent(new BirdViewComponent(/* model */));

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

