package FlappyBird.view.components.audio;

import FlappyBird.events.BaseEvent;
import FlappyBird.events.HitEvent;
import FlappyBird.models.states.PlayState;
import FlappyBird.models.states.State;

public class HitAudioComponent extends AudioComponent {
    public HitAudioComponent() {
        this.audioDirectoryPath = "./src/FlappyBird/view/audio/hit.wav";
    }

    @Override
    public void onEvent(BaseEvent event, State state) {
        if (event instanceof HitEvent && state instanceof PlayState) {
            play();
        }
    }
}
