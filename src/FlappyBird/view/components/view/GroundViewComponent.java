package FlappyBird.view.components.view;

import FlappyBird.Const;
import FlappyBird.models.objects.Ground;
import FlappyBird.view.ImageNotFoundException;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class GroundViewComponent implements ViewComponent {
    private final Ground ground;
    private final String imagePath = "./src/FlappyBird/view/images/ground.png";
    private final Image image;

    {
        try {
            image = ImageIO.read(new File(imagePath));
        } catch (IOException ignore) {
            throw new ImageNotFoundException();
        }
    }

    public GroundViewComponent(Ground ground) {
        this.ground = ground;
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(image, this.ground.getX(), this.ground.getY(), this.ground.getWidth(), this.ground.getHeight(), null);
        g.drawImage(image, this.ground.getX() - Const.screenX, this.ground.getY(), this.ground.getWidth(), this.ground.getHeight(), null);
    }
}
