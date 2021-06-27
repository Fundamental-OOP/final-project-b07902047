import events.*;
import objects.Ground;
import objects.Bird;
import objects.PipeList;
import states.State;
import states.StateMachine;
import java.util.Random;
import java.util.HashMap;
import java.util.Map;

/**
 * Game engine that tracks the game state
 */
public class Model extends Listener {
    EventManager eventManager;
    StateMachine stateMachine;
    boolean running;
    Random rnd;
    int score;

    Bird bird;
    PipeList pipeList;
    Ground ground;
    Map<String, Integer> config;

    public Model(EventManager eventManager, HashMap<String, Integer> config) {
        this.name = "Model";
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
        this.eventManager.post(new InitializeEvent());
        this.stateMachine.push(State.STATE_MENU);

        while (this.running) {
            this.eventManager.post(new TickEvent());
        }
    }

    @Override
    void notifyEvent(BaseEvent event) {
        if (event instanceof StateChangeEvent) {
            StateChangeEvent stateChangeEvent = (StateChangeEvent)event;
            if (stateChangeEvent.getState() == null) {
                if (this.stateMachine.pop() == null) {
                    this.eventManager.post(new QuitEvent());
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
                    if (scoreIsUpdated()) {
                        eventManager.post(new ScoreEvent());
                    }
                    bird.setY(bird.getY() + bird.getVelocity());
                    bird.setVelocity(Math.min(bird.getVelocity() + 1, config.get("MaxVelocity")));
                    ground.updateCoord();
                    bird.nextState();
                    pipeList.updatePipes(config, ground.getHeight());

                    if (isCrashed()) {
                        eventManager.post(new HitEvent());
                        eventManager.post(new StateChangeEvent(null));
                        eventManager.post(new StateChangeEvent(State.STATE_DEAD));
                    }
                case STATE_STOP:
                    break;
                case STATE_DEAD:
                    break;
            }
        } else if (event instanceof JumpEvent) {
            bird.setVelocity(config.get("FlapVelocity"));
        } else if (event instanceof InitializeEvent) {
            initialize();
        } else if (event instanceof QuitEvent) {
            this.running = false;
        }
    }

    private boolean scoreIsUpdated() {
        if (pipeList.isObjectPassedThrough(bird, config.get("ForwardSpeed"))) {
            score++;
            return true;
        }
        return false;
    }

    private boolean isCrashed() {
        return pipeList.isCollided(bird) || ground.isCollided(bird);
    }

    private void initialize() {
        this.bird = new Bird(
            config.get("BirdInitX"),
            config.get("BirdInitY"),
            config.get("BirdWidth"),
            config.get("BirdHeight"),
            0,
            rnd.nextInt(2),
            4
        );
        this.ground = new Ground(
            0,
            (int)Math.round(config.get("ScreenY") * 0.8),
            config.get("ScreenX"),
            (int)Math.round(config.get("ScreenY") * 0.2),
            config.get("ForwardSpeed")
        );
        this.score = 0;
    }
}
