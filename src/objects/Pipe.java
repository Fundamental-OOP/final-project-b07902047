package objects;

public class Pipe {
    private int type;
    private int x;
    private int width;
    Object upperPipe, bottomPipe;

    /*
    |  |
    |  |
    |  |
    ---- -> upperPipe

    ---- -> bottomPipe
    |  |
    |  |
     */

    public Pipe(int type, int x, int width, int upperPipeHeight, int gap, int bottomPipeHeight) {
        this.type = type;
        this.x = x;
        this.width = width;
        this.upperPipe = new Object(x, 0, width, upperPipeHeight);
        this.bottomPipe = new Object(x, upperPipeHeight + gap, width, bottomPipeHeight);
    }

    public void setX(int x) {
        this.x = x;
        upperPipe.setX(x);
        bottomPipe.setX(x);
    }

    public int getX() {
        return x;
    }

    public int getType() {
        return type;
    }

    public int getWidth() {
        return width;
    }

    public Object getUpperPipe() {
        return upperPipe;
    }

    public Object getBottomPipe() {
        return bottomPipe;
    }

    public boolean isCollided(Object other) {
        return upperPipe.isCollided(other) || bottomPipe.isCollided(other);
    }
}
