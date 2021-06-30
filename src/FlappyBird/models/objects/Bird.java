package FlappyBird.models.objects;

import java.util.List;

import FlappyBird.Const;
import FlappyBird.models.stateHandlers.StateHandler;
import FlappyBird.models.states.State;

public class Bird extends Object {
    // bird flap state (image 0/1/2/3)
    private int state, totalState;
    // bird type (red/green)
    private int type;
    // bird position and speed
    private int velocity;
    // When displaying the menu, make the bird move around a little
    // The bird will only move between [initY - boundary, initY + boundary]
    private int boundary;
    // The direction the bird is flying towards. Positive for downward and negative for upward.
    // Using only 0.5 of its normal velocity to fly a little slower.
    private double directionAndScale;
    // The distance of movement.
    private int deltaY;
    // Impose a limit on the bird's maximum y position
    private int maxPositionY;
    // Handler for each state
    List<StateHandler> stateHandlers;

    public Bird(int totalState, List<StateHandler> stateHandlers) {
        super(Const.birdInitX, Const.birdInitY, Const.birdWidth, Const.birdHeight);
        this.totalState = totalState;
        // variables for actToState
        this.boundary = 16;
        this.directionAndScale = 0.5;
        this.deltaY = 0;
        this.stateHandlers = stateHandlers;
    }

    public void initialize(int type, int initState) {
        this.x = Const.birdInitX;
        this.y = Const.birdInitY;
        this.type = type;
        this.state = initState;
        this.velocity = 0;
    }

    public int nextState() {
        state = (state + 1) % totalState;
        return state;
    }

    public void setVelocity(int velocity) {
        this.velocity = velocity;
        this.velocity = Math.min(this.velocity, Const.birdMaxVelocity);
    }

    public int getTotalState() {
        return totalState;
    }

    public int getState() {
        return state;
    }

    public int getType() {
        return type;
    }

    public int getVelocity() {
        return velocity;
    }

    public void speedUp() {
        setVelocity(velocity + 1);
    }

    public void updatePosition() {
        y += this.velocity;
        // The bird's y position cannot be greater than maxPositionY. That is, it cannot fall UNDER the ground.
        y = Math.min(maxPositionY, y);
        // The bird's y position cannot be negative. That is, it cannot fly INTO the sky.
        y = Math.max(0, y);
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setMaxPositionY(int maxPositionY) {
        this.maxPositionY = maxPositionY;
    }

    // Move up and down a little
    public void oscillate() {
        deltaY += (int)(directionAndScale * this.getVelocity());
        this.setY(this.getY() + deltaY);

        if (Math.abs(deltaY) >= boundary) {
            // If the bird has moved out of the boundary, change the direction.
            directionAndScale *= -1.0;
        }
    }

    public void actToState(State state) {
        for (StateHandler stateHandler : stateHandlers) {
            if (stateHandler.isHandlerApplicable(state)) {
                stateHandler.handleState(this);
            }
        }
    }
}
