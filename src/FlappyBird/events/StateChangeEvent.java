package FlappyBird.events;

import FlappyBird.models.states.State;

public class StateChangeEvent extends ControllerEvent {
    State state;

    public StateChangeEvent(State state) {
        this.name = "State change event";
        this.state = state;
    }

    public State getState() {
        return state;
    }

    @Override
    public String toString() {
        if (this.state != null) return String.format("%s pushed %s", this.name, this.state);
        return String.format("%s popped", this.name);
    }
}
