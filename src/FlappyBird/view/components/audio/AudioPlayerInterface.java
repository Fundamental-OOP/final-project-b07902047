package FlappyBird.view.components.audio;

import FlappyBird.events.BaseEvent;

public interface AudioPlayerInterface {
    void onEvent(BaseEvent event);
    AudioPlayerInterface addAudioComponent(AudioComponent audioComponent);
}
