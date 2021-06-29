package FlappyBird.models.states;

import FlappyBird.events.*;
import FlappyBird.models.Model;
import FlappyBird.models.objects.*;
import FlappyBird.models.objects.Object;

public class PlayState extends State {
    public PlayState() {
        super("Play");
    }

    @Override
    public void runTick(Model model) {
        if (model.scoreIsUpdated()) {
            EventManager.post(new ScoreEvent());
        }

        PipeList pipeList = model.getPipeList();
        pipeList.updatePipes();

        for (Object object : model.getObjects()) {
            object.actToState(model, this);
        }

        if (model.isCrashed()) {
            EventManager.post(new HitEvent());
            EventManager.post(new StateChangeEvent(null));
            EventManager.post(new StateChangeEvent(new DeadState()));
        }
    }
}
