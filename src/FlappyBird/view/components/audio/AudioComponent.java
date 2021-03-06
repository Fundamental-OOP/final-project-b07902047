package FlappyBird.view.components.audio;

import FlappyBird.events.BaseEvent;
import FlappyBird.models.states.State;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public abstract class AudioComponent {
    protected String audioDirectoryPath;
    private Clip clip;

    public void play() {
        try {
            clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(new File(audioDirectoryPath)));
            clip.start();
        } catch (Exception e) {
            System.err.print(e);
        }
    }

    public abstract void onEvent(BaseEvent event, State state);
}
