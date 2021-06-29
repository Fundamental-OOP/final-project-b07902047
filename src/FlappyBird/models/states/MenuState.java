package FlappyBird.models.states;

import FlappyBird.models.Model;
import FlappyBird.models.objects.*;
import FlappyBird.models.objects.Object;

public class MenuState extends State {
    public MenuState() {
        super("Menu");
    }

    @Override
    public void runTick(Model model) {
        for (Object object : model.getObjects()) {
            object.actToState(model, this);
        }
    }
}
