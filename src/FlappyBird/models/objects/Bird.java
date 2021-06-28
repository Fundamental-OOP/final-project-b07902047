package FlappyBird.models.objects;

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
        this.y += this.velocity;
        return state;
    }

    public void setVelocity(int velocity) {
        this.velocity = velocity;
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

    // Override the object's method as we check the bird's y position cannot be negative,
    // That is, it cannot fly INTO the sky.
    @Override
    public void setY(int y) {
        this.y = Math.max(y, 0);
    }
}
