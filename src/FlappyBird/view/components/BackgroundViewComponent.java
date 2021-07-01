package FlappyBird.view.components;

import FlappyBird.Const;
import FlappyBird.models.BackgroundTheme;
import FlappyBird.models.objects.Background;
import FlappyBird.view.ImageNotFoundException;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BackgroundViewComponent implements ViewComponent {
    private final Background background;
    private final String pathToImageDir = "./src/FlappyBird/view/images/";
    private final List<String> backgroundImages = new ArrayList<>() {{
        add("background-day.png");
        add("background-night.png");
    }};
    private BufferedImage[] images;

    public BackgroundViewComponent(Background background) {
        loadImage();
        this.background = background;
    }

    private void loadImage() {
        images = new BufferedImage[backgroundImages.size()];
        try {
            images[0] = ImageIO.read(new File(pathToImageDir + backgroundImages.get(0)));
            images[1] = ImageIO.read(new File(pathToImageDir + backgroundImages.get(1)));
        } catch (IOException e) {
            throw new ImageNotFoundException();
        }
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(
                images[background.getBackgroundTheme().ordinal()],
                background.getX(),
                background.getY(),
                background.getWidth(),
                background.getHeight(),
                null
        );
    }
}
