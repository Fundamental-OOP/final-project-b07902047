package FlappyBird.models.states;

import FlappyBird.models.Model;
import FlappyBird.models.objects.Object;

public class StopState extends State {
    public StopState() {
        super("Stop");
    }

    @Override
    public void runTick(Model model) {
        for (Object object : model.getObjects()) {
            object.actToState(model, this);
        }
    }
}
