package FlappyBird.models.statesHandler;

import FlappyBird.models.objects.Bird;
import FlappyBird.models.states.State;
import FlappyBird.models.states.StopState;

public class StopStateHandler extends StateHandler {
    @Override
    public boolean isHandlerApplicable(State state) {
        return state instanceof StopState;
    }

    @Override
    public void handleState(Bird bird) {
        // do nothing when stopped
        return;
    }
}
