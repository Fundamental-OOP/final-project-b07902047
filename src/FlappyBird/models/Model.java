package FlappyBird.models;

import FlappyBird.Const;
import FlappyBird.events.*;
import FlappyBird.models.objects.Bird;
import FlappyBird.models.objects.Ground;
import FlappyBird.models.objects.Object;
import FlappyBird.models.objects.PipeList;
import FlappyBird.models.states.MenuState;
import FlappyBird.models.states.State;
import FlappyBird.models.states.StateMachine;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Game engine that tracks the game state
 */

public class Model implements Listener {
    private StateMachine stateMachine;
    private boolean running;
    private Random rnd;
    private int score;

    private Bird bird;
    private Ground ground;
    private List<Object> objects;

    private PipeList pipeList;
    private BackgroundTheme backgroundTheme;

    public Model() {
        EventManager.registerListener(this);
        rnd = new Random();
        this.stateMachine = new StateMachine();
        this.running = false;
        this.objects = new ArrayList<>();
    }

    /**
     * Start game engine loop
     */
    public void run() {
        this.running = true;
        EventManager.post(new InitializeEvent());
        this.stateMachine.push(new MenuState());

        while (this.running) {
            EventManager.post(new TickEvent());

            try {
                Thread.sleep(50);
            } catch (InterruptedException ignored) {
            }
        }
    }

    @Override
    public void onEvent(BaseEvent event) {
        if (event instanceof StateChangeEvent) {
            StateChangeEvent stateChangeEvent = (StateChangeEvent) event;
            if (stateChangeEvent.getState() == null) {
                if (this.stateMachine.pop() == null) {
                    EventManager.post(new QuitEvent());
                }
            } else {
                this.stateMachine.push(stateChangeEvent.getState());
            }
        } else if (event instanceof TickEvent) {
            State curState = this.stateMachine.peek();
            curState.runTick(this);
        } else if (event instanceof JumpEvent) {
            bird.setVelocity(Const.birdFlapVelocity);
        } else if (event instanceof InitializeEvent) {
            initialize();
        } else if (event instanceof QuitEvent) {
            this.running = false;
        }
    }

    public boolean scoreIsUpdated() {
        if (pipeList.isObjectPassedThrough(bird, Const.forwardSpeed)) {
            score++;
            return true;
        }
        return false;
    }

    public boolean isCrashed() {
        return pipeList.isCollided(bird) || ground.isCollided(bird);
    }

    public Ground getGround() {
        return ground;
    }

    public Bird getBird() {
        return bird;
    }

    public PipeList getPipeList() {
        return pipeList;
    }

    public List<Object> getObjects() {
        return objects;
    }

    public int getScore() {
        return score;
    }

    public BackgroundTheme getBackgroundTheme() {
        return backgroundTheme;
    }

    private void initialize() {
        this.bird = new Bird(
                Const.birdInitX,
                Const.birdInitY,
                Const.birdWidth,
                Const.birdHeight,
                rnd.nextInt(3),
                rnd.nextInt(3),
                3
        );
        objects.add(this.bird);
        this.ground = new Ground(
                0,
                (int) Math.round(Const.screenY * 0.8),
                Const.screenX,
                (int) Math.round(Const.screenY * 0.2)
        );
        objects.add(this.ground);

        this.pipeList = new PipeList(ground.getY());
        this.running = true;
        this.score = 0;
        // Randomly pick a background theme
        this.backgroundTheme = BackgroundTheme.values()[rnd.nextInt(BackgroundTheme.values().length)];
    }

    public State getState() {
        return stateMachine.peek();
    }
}
