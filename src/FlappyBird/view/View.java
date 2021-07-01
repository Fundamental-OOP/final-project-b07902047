package FlappyBird.view;

import FlappyBird.Const;
import FlappyBird.controller.Controller;
import FlappyBird.controller.KeyboardEventListener;
import FlappyBird.events.*;
import FlappyBird.models.Model;
import FlappyBird.view.components.*;

import javax.swing.*;
import javax.sound.sampled.*;
import java.io.File;
import java.util.HashMap;

/**
 * Draws the model state onto the screen.
 */
public class View implements Listener {
    boolean initialized = false;

    Model model;
    Controller controller;

    JFrame jFrame;
    Renderer renderer;
    HashMap<String, AudioComponent> audioPlayer;

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
            // TODO remove: controller.addQueuedEvent(new StateChangeEvent(new PlayState()));
        } else if (event instanceof ScoreEvent) {
            audioPlayer.get("point").play();
        } else if (event instanceof HitEvent) {
            audioPlayer.get("hit").play();
            audioPlayer.get("die").play();
        } else if (event instanceof JumpEvent) {
            audioPlayer.get("wing").play();
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
        renderer.addViewComponent(new BackgroundViewComponent(model.getBackgroundTheme()));
        renderer.addViewComponent(new PipeViewComponent(model.getPipeList()));
        renderer.addViewComponent(new GroundViewComponent(model.getGround()));
        renderer.addViewComponent(new ScoreViewComponent(model));
        renderer.addViewComponent(new MenuViewComponent(model));
        renderer.addViewComponent(new DeadViewComponent(model));
        renderer.addViewComponent(new BirdViewComponent(model.getBird()));
    }

    private void setupAudioPlayer() {
        audioPlayer = new HashMap<>();
        audioPlayer.put("die", new DieAudioComponent());
        audioPlayer.put("hit", new HitAudioComponent());
        audioPlayer.put("point", new PointAudioComponent());
        audioPlayer.put("swoosh", new SwooshAudioComponent());
        audioPlayer.put("wing", new WingAudioComponent());
    }
}

