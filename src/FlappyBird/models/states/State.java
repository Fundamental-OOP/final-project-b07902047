package FlappyBird.models.states;

import FlappyBird.models.Model;

public abstract class State {
    protected String name;

    State(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public abstract void action(Model model);
}
