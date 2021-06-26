public class Pipe {
    private int type;
    // x is not important, should be calculated by View
    private int upperPipeY, bottomPipeY; // x is not important
    /*
    |  |
    |  |
    |  |
    ---- -> upperPipeY

    ---- -> bottomPipeY
    |  |
    |  |
     */

    public Pipe(int type, int upperPipeY, int bottomPipeY) {
        this.type = type;
        this.upperPipeY = upperPipeY;
        this.bottomPipeY = bottomPipeY;
    }

    public int getType() {
        return type;
    }

    public int getUpperPipeY() {
        return upperPipeY;
    }

    public int getBottomPipeY() {
        return bottomPipeY;
    }
}
