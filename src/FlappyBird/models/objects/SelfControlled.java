package FlappyBird.models.objects;

import java.util.List;

public interface SelfControlled {
    public void updatePosition();
    public List<Object> getObjects();
}
