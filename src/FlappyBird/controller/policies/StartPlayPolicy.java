package FlappyBird.controller.policies;

import FlappyBird.events.BaseEvent;
import FlappyBird.events.StateChangeEvent;
import FlappyBird.models.states.MenuState;
import FlappyBird.models.states.PlayState;
import FlappyBird.models.states.State;

import java.awt.event.KeyEvent;
import java.util.Optional;

public class StartPlayPolicy extends KeyboardEventPolicy {
    @Override
    public boolean match(int keyCode, State state) {
        return state instanceof MenuState && keyCode == KeyEvent.VK_SPACE;
    }

    @Override
    public Optional<BaseEvent> getEvent() {
        return Optional.of(new StateChangeEvent(new PlayState()));
    }
}
