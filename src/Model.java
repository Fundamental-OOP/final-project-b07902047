import events.*;
import states.State;
import states.StateMachine;

/**
 * Game engine that tracks the game state
 */
public class Model extends Listener {
    EventManager eventManager;
    StateMachine stateMachine;
    boolean running;

    public Model(EventManager eventManager) {
        this.name = "Model";
        this.eventManager = eventManager;
        eventManager.registerListener(this);
        this.stateMachine = new StateMachine();
        this.running = false;
    }

    /**
     * Start game engine loop
     */
    public void run() {
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
            }
            else {
                this.stateMachine.push(stateChangeEvent.getState());
            }
        } else if (event instanceof TickEvent) {
            State curState = this.stateMachine.peek();
            // user do nothing
            // four cases to handle: STATE_MENU, STATE_PLAY, STATE_STOP, STATE_DEAD
        } else if (event instanceof JumpEvent) {
            // user press jump
            // should be in STATE_PLAY
        } else if (event instanceof InitializeEvent) {
            // initialize game setting
        } else if (event instanceof QuitEvent) {
            this.running = false;
        }
    }
}
