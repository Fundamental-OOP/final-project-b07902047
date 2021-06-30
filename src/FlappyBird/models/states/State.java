package FlappyBird.models.states;

import java.util.List;

import FlappyBird.models.Model;
import FlappyBird.models.objects.Bird;
import FlappyBird.models.objects.SelfControlled;

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
        List<SelfControlled> selfControlledEntities = model.getSelfControlledEntities();
        for (SelfControlled selfControlledEntity : selfControlledEntities) {
            selfControlledEntity.updatePosition();
        }
        Bird bird = model.getBird();
        bird.actToState(this);
    }
}
