package FlappyBird.models;

import FlappyBird.Config;
import FlappyBird.events.EventManager;
import FlappyBird.events.Listener;
import FlappyBird.events.*;
import FlappyBird.states.State;
import FlappyBird.states.StateMachine;
import java.util.Random;
import java.util.ArrayList;
import java.util.Map;

/**
 * Game engine that tracks the game state
 */
public class Model extends Listener {
    EventManager eventManager;
    StateMachine stateMachine;
    boolean running;
    Random rnd = new Random();

    Bird bird;
    ArrayList<Pipe> pipes;
    Base base;
    Config config;

    public Model(EventManager eventManager, Config config) {
        this.name = "FlappyBird.models.Model";
        this.eventManager = eventManager;
        eventManager.registerListener(this);
        this.stateMachine = new StateMachine();
        this.running = false;
        this.config = config;
    }

    /**
     * Start game engine loop
     */
    public void run() {
        this.running = true;
        this.eventManager.postEvent(new InitializeEvent());
        this.stateMachine.push(State.STATE_MENU);

        while (this.running) {
            this.eventManager.postEvent(new TickEvent());
        }
    }

    @Override
    protected void onEvent(BaseEvent event) {
        if (event instanceof StateChangeEvent) {
            StateChangeEvent stateChangeEvent = (StateChangeEvent)event;
            if (stateChangeEvent.getState() == null) {
                if (this.stateMachine.pop() == null) {
                    this.eventManager.postEvent(new QuitEvent());
                }
            } else {
                this.stateMachine.push(stateChangeEvent.getState());
            }
        } else if (event instanceof TickEvent) {
            State curState = this.stateMachine.peek();
            switch (curState) {
                case STATE_MENU:
                    break;
                case STATE_PLAY:
                    bird.setY(bird.getY() + bird.getVelocity());
                    bird.setVelocity(Math.min(bird.getVelocity(), config.getMaxVelocity()));
                case STATE_STOP:
                    break;
                case STATE_DEAD:
                    break;
            }
        } else if (event instanceof JumpEvent) {
            // user press jump
            // should be in STATE_PLAY
        } else if (event instanceof InitializeEvent) {
            initialize();

        } else if (event instanceof QuitEvent) {
            this.running = false;
        }
    }

    private void initialize() {
        this.bird = new Bird(0, 4, rnd.nextInt() % 2, 40, 244);
        this.pipes = new ArrayList<Pipe>();
        this.base = new Base(0, (int)(config.getScreenWidth() * 0.8));
    }
}