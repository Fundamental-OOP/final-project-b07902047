package FlappyBird;

public class Config {
    int screenWidth;
    int screenHeight;
    int maxVelocity;

    public Config() {
        setScreenWidth(288);
        setScreenHeight(512);
        setMaxVelocity(10);
    }

    public Config(int screenWidth, int screenHeight, int maxVelocity) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.maxVelocity = maxVelocity;
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public Config setScreenWidth(int screenWidth) {
        this.screenWidth = screenWidth;
        return this;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public Config setScreenHeight(int screenHeight) {
        this.screenHeight = screenHeight;
        return this;
    }

    public int getMaxVelocity() {
        return maxVelocity;
    }

    public Config setMaxVelocity(int maxVelocity) {
        this.maxVelocity = maxVelocity;
        return this;
    }
}
