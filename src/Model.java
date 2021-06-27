import events.*;
import states.State;
import states.StateMachine;
import java.util.Random;
import java.util.ArrayList;
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
    private int nextPipeGapX;

    Bird bird;
    ArrayList<Pipe> pipes;
    Base base;
    Map<String, Integer> config;

    public Model(EventManager eventManager, HashMap<String, Integer> config) {
        this.name = "Model";
        this.eventManager = eventManager;
        eventManager.registerListener(this);
        this.stateMachine = new StateMachine();
        this.running = false;
        this.config = config;
        this.nextPipeGapX = getNextPipeGapX();
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
                    base.updateCoord();
                    bird.nextState();
                    updatePipe();
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

    private int getNextPipeGapX() {
        int minGapX = config.get("MinColumnGapX");
        int maxGapX = config.get("MaxColumnGapX");
        // Determine the gap (distance) between the last column and the column to be created.
        int gap = rnd.nextInt(maxGapX - minGapX + 1) + minGapX;
        return gap;
    }

    private int getNextPipeGapY() {
        int minGapY = config.get("MinColumnGapY");
        int maxGapY = config.get("MaxColumnGapY");
        // Determine the gap (distance) between the upper column and the bottom column.
        int gap = rnd.nextInt(maxGapY - minGapY + 1) + minGapY;
        return gap;
    }

    void updatePipe() {
        // Update pipes position
        for (Pipe pipe : pipes) {
            pipe.setX(pipe.getX() - config.get("ForwardSpeed"));
        }

        // Remove out of bound pipes
        while (pipes.size() != 0) {
            if (pipes.get(0).getX() + Pipe.width < 0) {
                pipes.remove(0);
            }
        }

        // Insert new pipes if needed
        if (pipes.size() == 0 || pipes.get(pipes.size() - 1).getX() + nextPipeGapX < config.get("ScreenX")) {
            int gapY = getNextPipeGapY();
            int upperY = rnd.nextInt(config.get("ScreenY") - base.getHeight() - gapY); // heigher than ground and leave enough space for gap
            pipes.add(new Pipe(0, upperY, upperY + gapY));
            nextPipeGapX = getNextPipeGapX();
        }
    }

    private boolean scoreIsUpdated() {
        for (Pipe pipe : pipes) {
            // The rightmost point of the bird
            int birdFrontX = bird.getX() + bird.getWidth() / 2;
            // The rightmost point of the pipe
            int pipeFrontX = pipe.getX() + Pipe.width / 2;

            // Check if the bird just passed the pipe
            if (birdFrontX - pipeFrontX > 0 && birdFrontX - pipeFrontX <= config.get("ForwardSpeed")) {
                score++;
                return true;
            }
        }
        return false;
    }

    private void initialize() {
        this.bird = new Bird(0, 4, rnd.nextInt() % 2, config.get("BirdInitX"), config.get("BirdInitY"));
        this.pipes = new ArrayList<Pipe>();
        this.base = new Base(
            config.get("ScreenX"),
            (int)Math.round(config.get("ScreenY") * 0.2),
            config.get("ForwardSpeed"));
        this.score = 0;
    }
}
