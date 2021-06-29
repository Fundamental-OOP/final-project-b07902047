package FlappyBird.view.components;

import FlappyBird.models.Model;
import FlappyBird.models.objects.Bird;
import FlappyBird.view.ImageNotFoundException;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class BirdViewComponent implements ViewComponent {
    private final String imageDirectoryPath = "./src/FlappyBird/view/images/birds/";
    private final String[] birdColors = {"blue", "yellow", "red"};
    private final String[] birdActions = {"down", "mid", "up"};
    private Image[][] images;
    private Bird bird;

    private Random rnd = new Random(); // TODO: remove

    public BirdViewComponent() {
        loadImages();
        // TODO: delete this
    }

    public BirdViewComponent(Model model) {
        // this.bird = model.getBird();
        loadImages();
    }

    @Override
    public void paint(Graphics g) {
        Image image = images[rnd.nextInt(3)][rnd.nextInt(3)];
        g.drawImage(image, 50, 50, 34, 24, null);
    }

    private void loadImages() {
        images = new Image[birdColors.length][birdActions.length];
        try {
            for (int i = 0; i < birdColors.length; ++i) {
                for (int j = 0; j < birdActions.length; ++j) {
                    String filename = imageDirectoryPath + birdColors[i] + "bird-" + birdActions[j] + "flap.png";
                    images[i][j] = ImageIO.read(new File(filename));
                }
            }
        } catch (IOException e) {
            throw new ImageNotFoundException();
        }
    }
}
