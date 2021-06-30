package FlappyBird.models.statesHandler;

import FlappyBird.models.objects.Bird;
import FlappyBird.models.states.PlayState;
import FlappyBird.models.states.State;

public class PlayStateHandler extends StateHandler {
    @Override
    public boolean isHandlerApplicable(State state) {
        return state instanceof PlayState;
    }

    @Override
    public void handleState(Bird bird) {
        bird.updatePosition();
        bird.speedUp();
        bird.nextState();
    }
}
