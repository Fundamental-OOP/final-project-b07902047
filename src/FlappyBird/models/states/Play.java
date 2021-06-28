package FlappyBird.models.states;

import FlappyBird.events.*;
import FlappyBird.models.Model;
import FlappyBird.models.objects.*;

public class Play extends State {
    public Play() {
        super("Play");
    }

    @Override
    public void action(Model model) {
        if (model.scoreIsUpdated()) {
            EventManager.post(new ScoreEvent());
        }

        Bird bird = model.getBird();
        Ground ground = model.getGround();
        PipeList pipeList = model.getPipeList();

        bird.updatePosition(ground.getY());;
        bird.speedUp();
        ground.updatePosition();
        bird.nextState();
        pipeList.updatePipes(ground.getY());

        if (model.isCrashed()) {
            EventManager.post(new HitEvent());
            EventManager.post(new StateChangeEvent(null));
            EventManager.post(new StateChangeEvent(new Dead()));
        }
    }
}
