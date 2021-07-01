package FlappyBird.view.components;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class HitAudioComponent implements AudioComponent {
    private final String audioDirectoryPath = "./src/FlappyBird/view/audio/hit.wav";
    private Clip clip;

    @Override
    public void play() {
        try {
            clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(new File(audioDirectoryPath)));
            clip.start();
        } catch (Exception e) {
            System.err.print(e.getStackTrace());
        }
    }
}
