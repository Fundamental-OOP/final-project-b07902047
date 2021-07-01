package FlappyBird.view.components.audio;

import FlappyBird.events.BaseEvent;
import FlappyBird.models.Model;

import java.util.ArrayList;
import java.util.List;

public class AudioPlayer implements AudioPlayerInterface {

    private Model model;
    private List<AudioComponent> audioComponents;

    public AudioPlayer(Model model) {
        this.model = model;
        audioComponents = new ArrayList<>();
    }

    @Override
    public AudioPlayerInterface addAudioComponent(AudioComponent component) {
        audioComponents.add(component);
        return this;
    }

    @Override
    public void onEvent(BaseEvent event) {
        for (AudioComponent audioComponent : audioComponents) {
            audioComponent.onEvent(event, model.getState());
        }
    }
}
