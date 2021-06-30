package FlappyBird.view.components;

import FlappyBird.models.Model;
import FlappyBird.models.objects.Ground;
import FlappyBird.models.states.DeadState;
import FlappyBird.view.ImageNotFoundException;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class DeadViewComponent implements ViewComponent {
    private Model model;
    private final String imagePath = "./src/FlappyBird/view/images/gameover.png";
    private Image image;

    {
        try {
            image = ImageIO.read(new File(imagePath));
        } catch (IOException ignore) { throw new ImageNotFoundException(); }
    }

    public DeadViewComponent(Model model) {
        this.model = model;
    }

    @Override
    public void paint(Graphics g) {
        if (model.getState() instanceof DeadState) {
            g.drawImage(image, 48, 150, 192 , 42, null);
        }
    }
}
