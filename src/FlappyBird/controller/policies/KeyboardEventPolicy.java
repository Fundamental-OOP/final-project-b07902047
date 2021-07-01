package FlappyBird.controller.policies;

import FlappyBird.events.BaseEvent;
import FlappyBird.models.states.State;

import java.util.Optional;

abstract public class KeyboardEventPolicy {
    protected KeyboardEventPolicy nextPolicy;

    public KeyboardEventPolicy setNextPolicy(KeyboardEventPolicy policy) {
        nextPolicy = policy;
        return this;
    }

    public final Optional<BaseEvent> parse(int keyCode, State state) {
        if (match(keyCode, state)) {
            return getEvent();
        }

        if (nextPolicy != null) {
            return nextPolicy.parse(keyCode, state);
        }

        return Optional.empty();
    }

    protected abstract Optional<BaseEvent> getEvent();
    public abstract boolean match(int keyCode, State state);
}
