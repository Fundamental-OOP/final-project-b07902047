package FlappyBird.view;

import FlappyBird.Const;
import FlappyBird.controller.Controller;
import FlappyBird.events.*;
import FlappyBird.models.Model;
import FlappyBird.view.components.audio.AudioPlayer;
import FlappyBird.view.components.audio.AudioPlayerInterface;
import FlappyBird.view.components.view.*;

import javax.swing.*;

/**
 * Draws the model state onto the screen.
 */
public class View implements Listener {
    boolean initialized = false;

    Model model;
    Controller controller;

    JFrame jFrame;
    Renderer renderer;
    AudioPlayerInterface audioPlayer;

    public View(Model model, Controller controller) {
        EventManager.registerListener(this);
        this.model = model;
        this.controller = controller;

        setupJFrame();
    }


    public void initialize() {
        if (initialized) {
            return;
        }

        setupRenderer();
        setupAudioPlayer();

        jFrame.add(renderer);
        jFrame.setVisible(true);

        this.initialized = true;
    }

    @Override
    public void onEvent(BaseEvent event) {
        if (event instanceof TickEvent && this.initialized) {
            this.renderer.render();
        } else if (event instanceof InitializeEvent) {
            initialize();
        } else if (event instanceof ScoreEvent) {
            audioPlayer.playGetPointSound();
        } else if (event instanceof HitEvent) {
            audioPlayer.playHitSound();
        } else if (event instanceof JumpEvent) {
            audioPlayer.playJumpSound();
        } else if (event instanceof QuitEvent) {
            this.initialized = false;
            jFrame.setVisible(false);
            jFrame.dispose();
        }
    }

    private void setupJFrame() {
        jFrame = new JFrame("Flappy Bird");

        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setSize(Const.screenX, Const.screenY);
        jFrame.setResizable(false);
        jFrame.addKeyListener(new KeyboardEventListener(controller, model));
    }

    private void setupRenderer() {
        renderer = new Renderer(model);

        renderer.addViewComponent(new BackgroundViewComponent(model.getBackgroundTheme()))
                .addViewComponent(new PipeViewComponent(model.getPipeList()))
                .addViewComponent(new GroundViewComponent(model.getGround()))
                .addViewComponent(new ScoreViewComponent(model))
                .addViewComponent(new MenuViewComponent(model))
                .addViewComponent(new DeadViewComponent(model))
                .addViewComponent(new BirdViewComponent(model.getBird()));
    }

    private void setupAudioPlayer() {
        audioPlayer = new AudioPlayer();
    }
}

