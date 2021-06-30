package FlappyBird.models.objects;

import FlappyBird.Const;
import FlappyBird.models.Model;
import FlappyBird.models.states.DeadState;
import FlappyBird.models.states.MenuState;
import FlappyBird.models.states.PlayState;
import FlappyBird.models.states.State;

public class Bird extends Object{
    // bird flap state (image 0/1/2/3)
    private int state, totalState;
    // bird type (red/green)
    private int type;
    // bird position and speed
    private int velocity;

    public Bird(int x, int y, int width, int height, int type, int initState, int totalState) {
        super(x, y, width, height);
        this.state = initState;
        this.totalState = totalState;
        this.type = type;
        this.x = x;
        this.y = y;
        this.velocity = 0;
        // variables for actToState
        this.boundary = 16;
        this.directionAndScale = 0.5;
        this.deltaY = 0;
    }

    public int nextState() {
        state = (state + 1) % totalState;
        return state;
    }

    public void setVelocity(int velocity) {
        this.velocity = velocity;
        this.velocity = Math.min(this.velocity, Const.birdMaxVelocity);
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

    public void updatePosition(int groundY) {
        y += this.velocity;
        // The bird's y position cannot be greater than groundY. That is, it cannot fall UNDER the ground.
        y = Math.min(groundY, y);
        // The bird's y position cannot be negative. That is, it cannot fly INTO the sky.
        y = Math.max(0, y);
    }

    public void setY(int y) {
        this.y = y;
    }


    // When displaying the menu, make the bird move around a little
    // The bird will only move between [initY - boundary, initY + boundary]
    private int boundary;
    // The direction the bird is flying towards. Positive for downward and negative for upward.
    // Using only 0.5 of its normal velocity to fly a little slower.
    private double directionAndScale;
    // The distance of movement.
    private int deltaY;
    @Override
    public void actToState(Model model, State state) {
        if (state instanceof DeadState) {
            Ground ground = model.getGround();
            if (!ground.isCollided(this)) {
                this.setVelocity(Const.birdMaxVelocity);
                this.updatePosition(ground.getY());
                this.nextState();
            }
        } else if (state instanceof MenuState) {
            Ground ground = model.getGround();

            // The distance of movement.
            deltaY += (int)(directionAndScale * this.getVelocity());
            this.setY(this.getY() + deltaY);

            if (Math.abs(deltaY) >= boundary) {
                // If the bird has moved out of the boundary, change the direction.
                directionAndScale *= -1.0;
            }

            ground.updatePosition();
            this.nextState();
        } else if (state instanceof PlayState) {
            Ground ground = model.getGround();
            this.updatePosition(ground.getY());;
            this.speedUp();
            this.nextState();

        }
    }
}
