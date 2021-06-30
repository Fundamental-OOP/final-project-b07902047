package FlappyBird.models.states;

import java.util.List;

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

        List<SelfControlled> selfControlledEntities = model.getSelfControlledEntities();
        for (SelfControlled selfControlledEntity : selfControlledEntities) {
            selfControlledEntity.updatePosition();
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
