package FlappyBird.models.objects;

import FlappyBird.Const;

public class Ground extends Object {
    public Ground(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    public void updatePosition() {
        x = (x - Const.forwardSpeed + width) % width;
    }
}
