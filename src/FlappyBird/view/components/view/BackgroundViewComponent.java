package FlappyBird.view.components.view;

import FlappyBird.models.BackgroundTheme;
import FlappyBird.models.objects.Background;
import FlappyBird.view.ImageNotFoundException;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class BackgroundViewComponent implements ViewComponent {
    private final Background background;
    private final String pathToImageDir = "./src/FlappyBird/view/images/";
    private final String[] backgroundImages = BackgroundTheme.getNames();
    private BufferedImage[] images;

    public BackgroundViewComponent(Background background) {
        loadImage();
        this.background = background;
    }

    private void loadImage() {
        images = new BufferedImage[backgroundImages.length];
        try {
            for (int i = 0; i < 2; i++) {
                images[i] = ImageIO.read(new File(pathToImageDir + backgroundImages[i] + ".png"));
            }
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
