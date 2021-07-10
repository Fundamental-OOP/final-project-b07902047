package FlappyBird.models.objects;

import FlappyBird.models.BackgroundTheme;
import FlappyBird.models.Model;

import java.util.ArrayList;
import java.util.List;

public class Background extends Object implements SelfControlled {
    private BackgroundTheme backgroundTheme;

    public Background(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    public BackgroundTheme getBackgroundTheme() {
        return backgroundTheme;
    }

    @Override
    public void updatePosition() {
    }

    @Override
    public List<Object> getObjects() {
        List<Object> objects = new ArrayList<Object>();
        objects.add(this);
        return objects;
    }

    @Override
    public void initialize(Model model) {
        this.backgroundTheme = BackgroundTheme.randomBackgroundTheme(model.getRnd());
    }
}
