package FlappyBird;

import FlappyBird.controller.Controller;
import FlappyBird.events.EventManager;
import FlappyBird.models.Model;
import FlappyBird.view.View;

public class Main {

    public static void main(String[] args) {
        Config config = new Config();

        EventManager eventManager = new EventManager();

        Model model = new Model(eventManager, config);
        Controller controller = new Controller(eventManager, model);
        View view = new View(eventManager, model).setController(controller);

        model.run();
    }
}
