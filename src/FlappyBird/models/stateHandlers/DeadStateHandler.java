package FlappyBird.models.stateHandlers;

import FlappyBird.Const;
import FlappyBird.models.objects.Bird;
import FlappyBird.models.states.DeadState;
import FlappyBird.models.states.State;

public class DeadStateHandler extends StateHandler {
    @Override
    public boolean isHandlerApplicable(State state) {
        return state instanceof DeadState;
    }

    @Override
    public void handleState(Bird bird) {
        bird.setVelocity(Const.birdMaxVelocity);
        bird.updatePosition();
    }
}
