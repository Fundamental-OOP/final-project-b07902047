package FlappyBird.models.states;

import FlappyBird.models.Model;

public class Stop extends State {
    public Stop() {
        super("Stop");
    }

    @Override
    public void action(Model model) {
        // Do nothing when stopped
    }
}
