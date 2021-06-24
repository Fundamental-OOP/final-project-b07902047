package events;

import states.State;

public class StateChangeEvent extends BaseEvent {
    State state;

    public StateChangeEvent(State state) {
        this.name = "Quit event";
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
