public class Bird {
    private int width, height;
    // bird flap state (image 0/1/2/3)
    private int state, totalState;
    // bird type (red/green)
    private int type;
    // bird position and speed
    private int x, y, velocity;

    public Bird(int initState, int totalState, int type, int initX, int initY) {
        this.state = initState;
        this.totalState = totalState;
        this.type = type;
        this.x = initX;
        this.y = initY;
        this.velocity = 0;
        this.width = 34;
        this.height = 48;
    }

    public int nextState() {
        state = (state + 1) % totalState;
        this.y += this.velocity;
        return state;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setVelocity(int velocity) {
        this.velocity = velocity;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
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

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
