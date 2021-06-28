package FlappyBird.models.states;

import FlappyBird.models.Model;
import FlappyBird.models.objects.*;

public class Menu extends State {
    // When displaying the menu, make the bird move around a little
    // The bird will only move between [initY - boundary, initY + boundary]
    private int boundary;
    // The direction the bird is flying towards. Positive for downward and negative for upward.
    // Using only 0.5 of its normal velocity to fly a little slower.
    private double directionAndScale;
    // The distance of movement.
    private int deltaY;

    public Menu() {
        super("Menu");
        boundary = 16;
        directionAndScale = 0.5;
        deltaY = 0;
    }

    @Override
    public void action(Model model) {
        Bird bird = model.getBird();
        Ground ground = model.getGround();

        // The distance of movement.
        deltaY += (int)(directionAndScale * bird.getVelocity());
        bird.setY(bird.getY() + deltaY);

        if (Math.abs(deltaY) >= boundary) {
            // If the bird has moved out of the boundary, change the direction.
            directionAndScale *= -1.0;
        }

        ground.updatePosition();
        bird.nextState();
    }
}
