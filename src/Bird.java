public class Bird {
    // bird flap state (image 0/1/2/3)
    private int state, totState;
    // bird type (red/green)
    private int type;
    // bird position and speed
    private int x, y, vel;

    public Bird(int initState, int totState, int type, int initX, int initY) {
        this.state = initState;
        this.totState = totState;
        this.type = type;
        this.x = initX;
        this.y = initY;
        this.vel = 0;
    }

    public int nextState() {
        state = (state + 1) % totState;
        this.y += this.vel;
        return state;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setVel(int vel) {
        this.vel = vel;
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

    public int getVel() {
        return vel;
    }
}
