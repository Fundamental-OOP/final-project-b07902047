package objects;

import constants.Const;

public class Ground extends Object {
    public Ground(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    public void updateCoord() {
        x = (x - Const.forwardSpeed + width) % width;
    }
}
