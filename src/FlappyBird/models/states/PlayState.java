package FlappyBird.models.states;

import FlappyBird.events.*;
import FlappyBird.models.Model;
import FlappyBird.models.objects.*;

public class PlayState extends State {
    public PlayState() {
        super("State Play");
    }

    @Override
    public void runTick(Model model) {
        if (model.scoreIsUpdated()) {
            EventManager.post(new ScoreEvent());
        }

        Bird bird = model.getBird();
        bird.actToState(this);

        if (model.isCrashed()) {
            EventManager.post(new HitEvent());
            EventManager.post(new StateChangeEvent(null));
            EventManager.post(new StateChangeEvent(new DeadState()));
        }
    }
}
