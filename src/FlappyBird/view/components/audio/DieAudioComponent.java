package FlappyBird.view.components.audio;

import FlappyBird.events.BaseEvent;
import FlappyBird.events.HitEvent;
import FlappyBird.models.states.PlayState;
import FlappyBird.models.states.State;

public class DieAudioComponent extends AudioComponent {
    public DieAudioComponent() {
        this.audioDirectoryPath = "./src/FlappyBird/view/audio/die.wav";
    }

    @Override
    public void onEvent(BaseEvent event, State state) {
        if (event instanceof HitEvent && state instanceof PlayState) {
            play();
        }
    }
}
