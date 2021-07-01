package FlappyBird.models.stateHandlers;

import FlappyBird.models.objects.Bird;
import FlappyBird.models.states.State;

public abstract class StateHandler {
    public abstract boolean isHandlerApplicable(State state);

    public abstract void handleState(Bird bird);
}
