package FlappyBird.view.components;

import FlappyBird.Const;
import FlappyBird.events.BaseEvent;
import FlappyBird.events.InitializeEvent;
import FlappyBird.models.Model;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BackgroundViewComponent implements ViewComponent {
    private final String pathToImageDir = "./src/FlappyBird/view/images/";
    private final List<String> backgroundImages = new ArrayList<>(){{
        add("background-day.png");
        add("background-night.png");
    }};

    private Image currentImage;
    private Random random = new Random();

    public BackgroundViewComponent() {
        randomChangeImage();
    }

    public void randomChangeImage() {
        int imageIndex = random.nextInt(backgroundImages.size());
        String imageFile = pathToImageDir + backgroundImages.get(imageIndex);
        try {
            currentImage = ImageIO.read(new File(imageFile));
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(currentImage, 0, 0, Const.screenX, Const.screenY, null);
    }
}
