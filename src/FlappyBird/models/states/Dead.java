package FlappyBird.models.states;

import FlappyBird.Const;
import FlappyBird.models.Model;
import FlappyBird.models.objects.*;

public class Dead extends State {
    public Dead() {
        super("Dead");
    }

    @Override
    public void action(Model model) {
        Bird bird = model.getBird();
        Ground ground = model.getGround();
        if (!ground.isCollided(bird)) {
            bird.setVelocity(Const.birdMaxVelocity);
            bird.updatePosition(ground.getY());
            bird.nextState();
        }
    }
}
