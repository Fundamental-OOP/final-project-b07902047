package FlappyBird.models.states;

import FlappyBird.events.EventManager;
import FlappyBird.events.HitEvent;
import FlappyBird.events.ScoreEvent;
import FlappyBird.events.StateChangeEvent;
import FlappyBird.models.Model;
import FlappyBird.models.objects.Bird;
import FlappyBird.models.objects.SelfControlled;

import java.util.List;

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

        List<SelfControlled> selfControlledEntities = model.getSelfControlledEntities();
        for (SelfControlled selfControlledEntity : selfControlledEntities) {
            selfControlledEntity.updatePosition();
        }

        if (model.isCrashed()) {
            EventManager.post(new HitEvent());
            EventManager.post(new StateChangeEvent(null));
            EventManager.post(new StateChangeEvent(new DeadState()));
        }
    }
}
