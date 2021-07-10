package FlappyBird.models.objects;

import FlappyBird.models.PipeType;

public class Pipe {
    private final PipeType type;
    private int x;
    private final int width;
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

    public Pipe(PipeType type, int x, int width, int upperPipeHeight, int gap, int bottomPipeHeight) {
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

    public PipeType getType() {
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
