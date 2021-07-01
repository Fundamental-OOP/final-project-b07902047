package FlappyBird.controller.policies;

import FlappyBird.events.BaseEvent;
import FlappyBird.events.JumpEvent;
import FlappyBird.models.states.PlayState;
import FlappyBird.models.states.State;

import java.awt.event.KeyEvent;
import java.util.Optional;

public class JumpPolicy extends KeyboardEventPolicy {
    @Override
    public boolean match(int keyCode, State state) {
        if (state instanceof PlayState) {
            return keyCode == KeyEvent.VK_SPACE || keyCode == KeyEvent.VK_UP;
        }
        return false;
    }

    @Override
    public Optional<BaseEvent> getEvent() {
        return Optional.of(new JumpEvent());
    }
}
