public class Base {
    private int x;
    private int width;
    private int height;
    private int forwardSpeed;

    public Base(int width, int height, int forwardSpeed) {
        this.x = 0;
        this.width = width;
        this.height = height;
        this.forwardSpeed = forwardSpeed;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getHeight() {
        return height;
    }

    public void updateCoord() {
        x = (x - forwardSpeed + width) % width;
    }
}
