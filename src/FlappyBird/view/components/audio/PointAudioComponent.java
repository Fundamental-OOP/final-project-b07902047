package FlappyBird.view.components.audio;

import FlappyBird.events.BaseEvent;
import FlappyBird.events.ScoreEvent;
import FlappyBird.models.states.PlayState;
import FlappyBird.models.states.State;

public class PointAudioComponent extends AudioComponent {
    public PointAudioComponent() {
        this.audioDirectoryPath = "./src/FlappyBird/view/audio/point.wav";
    }

    @Override
    public void onEvent(BaseEvent event, State state) {
        if (event instanceof ScoreEvent && state instanceof PlayState) {
            play();
        }
    }
}