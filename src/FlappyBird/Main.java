package FlappyBird;

import FlappyBird.controller.Controller;
import FlappyBird.events.EventManager;
import FlappyBird.models.Model;
import FlappyBird.view.View;

public class Main {
    public static void main(String[] args) {
        Model model = new Model();
        Controller controller = new Controller(model);
        View view = new View(model, controller);

        model.run();
    }
}
