package FlappyBird.models;

import FlappyBird.events.*;
import FlappyBird.states.State;
import FlappyBird.states.StateMachine;
import java.util.Random;

import FlappyBird.models.objects.Ground;
import FlappyBird.models.objects.Bird;
import FlappyBird.models.objects.PipeList;
import FlappyBird.Const;

/**
 * Game engine that tracks the game state
 */
public class Model implements Listener {
    StateMachine stateMachine;
    boolean running;
    Random rnd = new Random();
    int score;

    Bird bird;
    PipeList pipeList = new PipeList();
    Ground ground;

    public Model() {
        EventManager.registerListener(this);
        this.stateMachine = new StateMachine();
        this.running = false;
    }

    /**
     * Start game engine loop
     */
    public void run() {
        this.running = true;
        EventManager.post(new InitializeEvent());
        this.stateMachine.push(State.STATE_MENU);

        while (this.running) {
            EventManager.post(new TickEvent());

            try {
                Thread.sleep(50);
            } catch (InterruptedException ignored) { }
        }
    }

    @Override
    public void onEvent(BaseEvent event) {
        if (event instanceof StateChangeEvent) {
            StateChangeEvent stateChangeEvent = (StateChangeEvent)event;
            if (stateChangeEvent.getState() == null) {
                if (this.stateMachine.pop() == null) {
                    EventManager.post(new QuitEvent());
                }
            } else {
                this.stateMachine.push(stateChangeEvent.getState());
            }
        } else if (event instanceof TickEvent) {
            State curState = this.stateMachine.peek();
            switch (curState) {
                case STATE_MENU:
                    // When displaying the menu, make the bird move around a little
                    // The bird will only move between [initY - boundary, initY + boundary]
                    int boundary = 16;
                    // The direction the bird is flying towards. Positive for downward and negative for upward.
                    // Using only 0.5 of its normal velocity to fly a little slower.
                    double directionAndScale = 0.5; 
                    // The distance of movement.
                    int deltaY = (int)(directionAndScale * bird.getVelocity());
                    bird.setY(bird.getY() + deltaY);
                    if (Math.abs(deltaY) >= boundary) {
                        // If the bird has moved out of the boundary, change the direction.
                        directionAndScale *= -1.0;
                        // TODO: this variable set but not used
                    }
                    ground.updateCoord();
                    bird.nextState();
                    break;
                case STATE_PLAY:
                    if (scoreIsUpdated()) {
                        EventManager.post(new ScoreEvent());
                    }
                    bird.setY(bird.getY() + bird.getVelocity());
                    bird.setVelocity(Math.min(bird.getVelocity() + 1, Const.birdMaxVelocity));
                    ground.updateCoord();
                    bird.nextState();
                    pipeList.updatePipes(ground.getHeight());

                    if (isCrashed()) {
                        EventManager.post(new HitEvent());
                        EventManager.post(new StateChangeEvent(null));
                        EventManager.post(new StateChangeEvent(State.STATE_DEAD));
                    }
                    break;
                case STATE_STOP:
                    break;
                case STATE_DEAD:
                    // Fall to the ground with max speed until it hits the ground
                    if (!ground.isCollided(bird)) {
                        bird.setY(Math.min(bird.getY() + Const.birdMaxVelocity, Const.screenY - ground.getHeight()));
                        bird.nextState();
                    }
                    break;
            }
        } else if (event instanceof JumpEvent) {
            bird.setVelocity(Const.birdFlapVelocity);
        } else if (event instanceof InitializeEvent) {
            initialize();
        } else if (event instanceof QuitEvent) {
            this.running = false;
        }
    }

    private boolean scoreIsUpdated() {
        if (pipeList.isObjectPassedThrough(bird, Const.forwardSpeed)) {
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
            Const.birdInitX,
            Const.birdInitY,
            Const.birdWidth,
            Const.birdHeight,
            0,
            rnd.nextInt(2),
            4
        );
        this.ground = new Ground(
            0,
            (int)Math.round(Const.screenY * 0.8),
            Const.screenX,
            (int)Math.round(Const.screenY * 0.2)
        );
        this.score = 0;
    }
}
