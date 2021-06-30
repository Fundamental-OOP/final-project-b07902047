package FlappyBird.models.objects;

import FlappyBird.models.Model;
import FlappyBird.models.states.State;

import java.awt.Rectangle;

public class Object {
    protected int x;
    protected int y;
    protected int width;
    protected int height;

    public Object(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Rectangle getRectangle() {
        return new Rectangle(x, y, width, height);
    }

    public boolean isCollided(Object other) {
        Rectangle selfRectangle = getRectangle();
        Rectangle otherRectangle = other.getRectangle();
        return selfRectangle.intersects(otherRectangle);
    }

    // Change self's position in different state.
    public void actToState(Model model, State state) {};
}