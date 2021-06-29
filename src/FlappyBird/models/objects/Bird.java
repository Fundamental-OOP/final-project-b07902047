package FlappyBird.models.objects;

import FlappyBird.Const;

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
}
