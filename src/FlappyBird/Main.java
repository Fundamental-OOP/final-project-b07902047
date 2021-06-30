package FlappyBird;

import java.util.List;
import java.util.ArrayList;

import FlappyBird.controller.Controller;
import FlappyBird.events.EventManager;
import FlappyBird.models.Model;
import FlappyBird.models.objects.Bird;
import FlappyBird.view.View;
import FlappyBird.models.statesHandler.*;

public class Main {
    public static void main(String[] args) {
        List<StateHandler> stateHandlers = new ArrayList<StateHandler>();
        stateHandlers.add(new DeadStateHandler());
        stateHandlers.add(new MenuStateHandler());
        stateHandlers.add(new PlayStateHandler());
        stateHandlers.add(new StopStateHandler());

        Bird bird = new Bird(3, stateHandlers);
        
        Model model = new Model(bird);
        Controller controller = new Controller(model);
        View view = new View(model, controller);

        model.run();
    }
}
