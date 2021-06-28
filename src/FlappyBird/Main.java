package FlappyBird;

import FlappyBird.controller.Controller;
import FlappyBird.events.EventManager;
import FlappyBird.models.Model;
import FlappyBird.view.View;

public class Main {
    public static void main(String[] args) {
        Config config = new Config();

        Model model = new Model(config);
        Controller controller = new Controller(model, config);
        View view = new View(model, controller, config);

        model.run();
    }
}
