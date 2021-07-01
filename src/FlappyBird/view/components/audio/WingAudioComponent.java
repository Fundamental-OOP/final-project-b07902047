package FlappyBird.view.components.audio;

import FlappyBird.events.BaseEvent;
import FlappyBird.events.JumpEvent;
import FlappyBird.models.states.PlayState;
import FlappyBird.models.states.State;

public class WingAudioComponent extends AudioComponent {
    public WingAudioComponent() {
        this.audioDirectoryPath = "./src/FlappyBird/view/audio/wing.wav";
    }

    @Override
    public void onEvent(BaseEvent event, State state) {
        if (event instanceof JumpEvent && state instanceof PlayState) {
            play();
        }
    }
}
