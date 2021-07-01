package FlappyBird.controller.policies;

import FlappyBird.events.BaseEvent;
import FlappyBird.events.ResetEvent;
import FlappyBird.models.states.DeadState;
import FlappyBird.models.states.PlayState;
import FlappyBird.models.states.State;

import java.awt.event.KeyEvent;
import java.util.Optional;

public class RestartPolicy extends KeyboardEventPolicy {
    @Override
    public boolean match(int keyCode, State state) {
        if (state instanceof PlayState && keyCode == KeyEvent.VK_ESCAPE) {
            return true;
        }

        return state instanceof DeadState && keyCode == KeyEvent.VK_SPACE;
    }

    @Override
    public Optional<BaseEvent> getEvent() {
        return Optional.of(new ResetEvent());
    }
}
