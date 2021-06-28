package FlappyBird;
public final class Const {
    private Const() {} // Avoid others from initializing this class
    public static final int screenX = 288;
    public static final int screenY = 512;

    public static final int birdInitX = 40;
    public static final int birdInitY = 244;
    public static final int birdWidth = 34;
    public static final int birdHeight = 24;
    public static final int birdMaxVelocity = 10;
    public static final int birdFlapVelocity = -10;

    public static final int pipeWidth = 52;
    public static final int minPipeGapY = 50;
    public static final int maxPipeGapY = 150;
    public static final int minPipeGapX = 50;
    public static final int maxPipeGapX = 150;
    
    public static final int forwardSpeed = 4;
}
