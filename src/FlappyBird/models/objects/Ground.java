package FlappyBird.models.objects;

import FlappyBird.Const;
import FlappyBird.models.Model;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Ground extends Object implements SelfControlled {
    public Ground(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    @Override
    public void updatePosition() {
        x = (x - Const.forwardSpeed + width) % width;
    }

    @Override
    public Rectangle getRectangle() {
        return new Rectangle(0, y, width, height);
    }

    @Override
    public List<Object> getObjects() {
        List<Object> objects = new ArrayList<Object>();
        objects.add(this);
        return objects;
    }

    @Override
    public void initialize(Model model) {
        this.setX(0);
    }
}
