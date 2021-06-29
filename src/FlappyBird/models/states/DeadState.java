package FlappyBird.models.states;

import FlappyBird.Const;
import FlappyBird.models.Model;
import FlappyBird.models.objects.*;

public class DeadState extends State {
    public DeadState() {
        super("Dead");
    }

    @Override
    public void runTick(Model model) {
        Bird bird = model.getBird();
        Ground ground = model.getGround();
        if (!ground.isCollided(bird)) {
            bird.setVelocity(Const.birdMaxVelocity);
            bird.updatePosition(ground.getY());
            bird.nextState();
        }
    }
}
