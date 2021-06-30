package FlappyBird.models.states;

import FlappyBird.models.Model;
import FlappyBird.models.objects.Object;

public abstract class State {
    protected String name;

    State(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public void runTick(Model model) {
        for (Object object : model.getObjects()) {
            object.actToState(model, this);
        }
    }
}
