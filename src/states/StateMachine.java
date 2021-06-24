package states;

import java.util.ArrayList;

/**
 * A stack based state machine
 */
public class StateMachine {
    ArrayList<State> stateStack;

    public StateMachine() {
        this.stateStack = new ArrayList<State>();
    }

    /**
     * returns the current state or null if the stack is empty
     * @return current state or null
     */
    public State peek() {
        if (stateStack.size() > 0) return stateStack.get(stateStack.size()-1);
        return null;
    }

    /**
     * remove the top state in the stack and return it
     * @return removed state or null
     */
    public State pop() {
        if (stateStack.size() > 0) {
            State state = peek();
            this.stateStack.remove(stateStack.size()-1);
            return state;
        }
        else return null;
    }

    /**
     * add new state to stack
     * @param state state to be added
     * @return added state
     */
    public State push(State state) {
        this.stateStack.add(state);
        return state;
    }
}
