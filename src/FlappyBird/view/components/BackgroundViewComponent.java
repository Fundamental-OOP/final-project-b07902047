package FlappyBird.view.components;

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

    void print(Graphics g) {
//        g.drawImage(currentImage, 0, 0, this);
    }

    public void randomChangeImage() {
        int imageIndex = random.nextInt(backgroundImages.size());
        String imageFile = pathToImageDir + backgroundImages.get(imageIndex);
        System.out.println(new File(imageFile).exists());
        // currentImage = getToolkit().getImage(imageFile);
        try {
            currentImage = ImageIO.read(new File(imageFile));
        } catch (IOException e) {
            throw new RuntimeException();
        }
//        repaint();
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(currentImage, 0, 0, null);
    }
}
