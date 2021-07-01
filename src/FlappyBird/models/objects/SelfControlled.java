package FlappyBird.models.objects;

import FlappyBird.models.Model;

import java.util.List;

public interface SelfControlled {
    void updatePosition();

    List<Object> getObjects();

    void initialize(Model model);
}
