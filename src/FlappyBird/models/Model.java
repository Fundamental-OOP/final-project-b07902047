package FlappyBird.models;

import FlappyBird.Const;
import FlappyBird.events.*;
import FlappyBird.models.objects.*;
import FlappyBird.models.objects.Object;
import FlappyBird.models.objects.PipeList;
import FlappyBird.models.objects.SelfControlled;
import FlappyBird.models.states.DeadState;
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
    private Background background;
    private List<SelfControlled> selfControlledEntities;

    private PipeList pipeList;

    public Model(Bird bird) {
        EventManager.registerListener(this);
        rnd = new Random();
        this.stateMachine = new StateMachine();
        this.running = false;
        this.selfControlledEntities = new ArrayList<SelfControlled>();
        this.bird = bird;
        this.ground = new Ground(
            0,
            (int) Math.round(Const.screenY * 0.8),
            Const.screenX,
            (int) Math.round(Const.screenY * 0.2)
        );
        bird.setMaxPositionY(ground.getY() - Const.birdHeight + 1);
        this.pipeList = new PipeList(ground.getY(), PipeType.randomPipeType(rnd));
        this.background = new Background(0, 0, Const.screenX, Const.screenY);
        selfControlledEntities.add(ground);
        selfControlledEntities.add(pipeList);
        selfControlledEntities.add(background);
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
                Thread.sleep(30);
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
            bird.jump();
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

    public Background getBackground() { return background; }

    public Random getRnd() {
        return rnd;
    }

    public PipeList getPipeList() {
        return pipeList;
    }

    public List<Object> getObjects() {
        List<Object> objects = new ArrayList<Object>();
        objects.add(bird);
        for (SelfControlled selfControlledEntity : selfControlledEntities) {
            objects.addAll(selfControlledEntity.getObjects());
        }
        return objects;
    }

    public List<SelfControlled> getSelfControlledEntities() {
        return selfControlledEntities;
    }

    public int getScore() {
        return score;
    }

    private void initialize() {
        this.bird.initialize(rnd.nextInt(this.bird.getTotalState()), rnd.nextInt(this.bird.getTotalState()));
        selfControlledEntities.forEach(entities -> entities.initialize(this));

        this.running = true;
        this.score = 0;
    }

    public State getState() {
        return stateMachine.peek();
    }

    public boolean isGameOver() {
        return getState() instanceof DeadState;
    }

    public boolean showMenu() {
        return getState() instanceof MenuState;
    }

    public boolean showScore() {
        return !(getState() instanceof MenuState);
    }
}
