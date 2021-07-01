package FlappyBird.models.states;

import FlappyBird.models.Model;
import FlappyBird.models.objects.Bird;

public class MenuState extends State {
    public MenuState() {
        super("State Menu");
    }

    @Override
    public void runTick(Model model) {
        model.getGround().updatePosition();

        Bird bird = model.getBird();
        bird.actToState(this);
    }
}
