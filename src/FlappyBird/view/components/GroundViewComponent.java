package FlappyBird.view.components;

import FlappyBird.events.BaseEvent;
import FlappyBird.models.objects.Ground;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class GroundViewComponent implements ViewComponent {
    private Ground ground;
    private final String imagePath = "./src/FlappyBird/view/images/ground.png";
    private Image image;

    {
        try {
            image = ImageIO.read(new File(imagePath));
        } catch (IOException ignore) { throw new RuntimeException(); }
    }

    public GroundViewComponent() {
        // TODO: delete this
    }

    public GroundViewComponent(Ground ground) {
        this.ground = ground;
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(image, 0, 400, 336, 112, null);
    }
}
