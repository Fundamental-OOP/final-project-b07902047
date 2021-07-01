package FlappyBird.controller.policies;

import FlappyBird.events.BaseEvent;
import FlappyBird.events.QuitEvent;
import FlappyBird.models.states.DeadState;
import FlappyBird.models.states.MenuState;
import FlappyBird.models.states.State;

import java.awt.event.KeyEvent;
import java.util.Optional;

public class QuitEventPolicy extends KeyboardEventPolicy {
    @Override
    public boolean match(int keyCode, State state) {
        if (state instanceof MenuState && keyCode == KeyEvent.VK_ESCAPE) {
            return true;
        }

        if (state instanceof DeadState && keyCode == KeyEvent.VK_ESCAPE) {
            return true;
        }

        return false;
    }

    @Override
    public Optional<BaseEvent> getEvent() {
        return Optional.of(new QuitEvent());
    }
}
