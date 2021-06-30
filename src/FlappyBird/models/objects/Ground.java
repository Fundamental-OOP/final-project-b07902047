package FlappyBird.models.objects;

import FlappyBird.Const;
import FlappyBird.models.Model;
import FlappyBird.models.states.MenuState;
import FlappyBird.models.states.PlayState;
import FlappyBird.models.states.State;

public class Ground extends Object {
    public Ground(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    public void updatePosition() {
        x = (x - Const.forwardSpeed + width) % width;
    }

    @Override
    public void actToState(Model model, State state) {
        if (state instanceof PlayState || state instanceof MenuState) {
            this.updatePosition();
        }
    }
}
