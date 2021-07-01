package FlappyBird.view.components.audio;

public class AudioPlayer implements AudioPlayerInterface{

    private AudioComponent DieAudio;
    private AudioComponent HitAudio;
    private AudioComponent PointAudio;
    private AudioComponent WingAudio;
    private AudioComponent SwooshAudio;

    public AudioPlayer(){
        this.DieAudio = new DieAudioComponent();
        this.HitAudio = new HitAudioComponent();
        this.PointAudio = new PointAudioComponent();
        this.WingAudio = new WingAudioComponent();
        this.SwooshAudio = new SwooshAudioComponent();
    }

    @Override
    public void playGetPointSound() {
        PointAudio.play();
    }

    @Override
    public void playJumpSound() {
        WingAudio.play();
    }

    @Override
    public void playHitSound() {
        HitAudio.play();
        DieAudio.play();
    }

}
