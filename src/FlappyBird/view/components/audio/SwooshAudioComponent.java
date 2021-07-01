package FlappyBird.view.components.audio;

import FlappyBird.events.BaseEvent;
import FlappyBird.models.states.State;

public class SwooshAudioComponent extends AudioComponent {
    public SwooshAudioComponent() {
        this.audioDirectoryPath = "./src/FlappyBird/view/audio/swoosh.wav";
    }

    @Override
    public void onEvent(BaseEvent event, State state) {

    }
}
