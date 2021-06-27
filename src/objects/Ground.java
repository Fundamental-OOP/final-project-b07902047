package objects;

public class Ground extends Object {
    private int forwardSpeed;

    public Ground(int x, int y, int width, int height, int forwardSpeed) {
        super(x, y, width, height);
        this.forwardSpeed = forwardSpeed;
    }

    public void updateCoord() {
        x = (x - forwardSpeed + width) % width;
    }
}
