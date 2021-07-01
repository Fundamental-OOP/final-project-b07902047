package FlappyBird.view.components.view;

import FlappyBird.models.BirdType;
import FlappyBird.models.objects.Bird;
import FlappyBird.view.ImageNotFoundException;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class BirdViewComponent implements ViewComponent {
    private final String imageDirectoryPath = "./src/FlappyBird/view/images/birds/";
    private final String[] birdColors = BirdType.getNames();
    private final String[] birdActions = {"down", "mid", "up"};
    private Image[][] images;
    private Bird bird;

    public BirdViewComponent(Bird bird) {
        this.bird = bird;
        loadImages();
    }

    @Override
    public void paint(Graphics g) {
        Image image = images[bird.getType().ordinal()][bird.getState()];
        g.drawImage(image, bird.getX(), bird.getY(), 34, 24, null);
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
