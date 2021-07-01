package FlappyBird.view.components.view;

import FlappyBird.models.Model;
import FlappyBird.view.ImageNotFoundException;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class MenuViewComponent implements ViewComponent {
    private final Model model;
    private final String imagePath = "./src/FlappyBird/view/images/menu.png";
    private final Image image;

    {
        try {
            image = ImageIO.read(new File(imagePath));
        } catch (IOException ignore) {
            throw new ImageNotFoundException();
        }
    }

    public MenuViewComponent(Model model) {
        this.model = model;
    }

    @Override
    public void paint(Graphics g) {
        if (model.showMenu()) {
            g.drawImage(image, 52, 50, 184, 267, null);
        }
    }
}
