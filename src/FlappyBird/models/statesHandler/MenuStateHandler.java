package FlappyBird.models.statesHandler;

import FlappyBird.models.objects.Bird;
import FlappyBird.models.states.MenuState;
import FlappyBird.models.states.State;

public class MenuStateHandler extends StateHandler {
    @Override
    public boolean isHandlerApplicable(State state) {
        return state instanceof MenuState;
    }

    @Override
    public void handleState(Bird bird) {
        bird.oscillate();
    }
}
