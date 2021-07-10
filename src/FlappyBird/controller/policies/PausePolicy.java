package FlappyBird.controller.policies;

import FlappyBird.events.BaseEvent;
import FlappyBird.events.StateChangeEvent;
import FlappyBird.models.states.PlayState;
import FlappyBird.models.states.State;
import FlappyBird.models.states.StopState;

import java.awt.event.KeyEvent;
import java.util.Optional;

public class PausePolicy extends KeyboardEventPolicy {
    @Override
    public boolean match(int keyCode, State state) {
        return state instanceof PlayState && keyCode == KeyEvent.VK_P;
    }

    @Override
    public Optional<BaseEvent> getEvent() {
        return Optional.of(new StateChangeEvent(new StopState()));
    }

}
