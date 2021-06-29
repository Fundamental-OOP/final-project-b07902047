package FlappyBird.models.states;

import FlappyBird.models.Model;

public class StopState extends State {
    public StopState() {
        super("Stop");
    }

    @Override
    public void runTick(Model model) {
        // Do nothing when stopped
    }
}
