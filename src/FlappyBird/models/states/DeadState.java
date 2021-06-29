package FlappyBird.models.states;

import FlappyBird.Const;
import FlappyBird.models.Model;
import FlappyBird.models.objects.*;
import FlappyBird.models.objects.Object;

public class DeadState extends State {
    public DeadState() {
        super("Dead");
    }

    @Override
    public void runTick(Model model) {
        for (Object object : model.getObjects()) {
            object.actToState(model, this);
        }
    }
}
