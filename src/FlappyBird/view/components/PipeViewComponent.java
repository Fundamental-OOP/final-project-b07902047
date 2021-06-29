package FlappyBird.view.components;

import FlappyBird.models.objects.Ground;
import FlappyBird.models.objects.PipeList;
import FlappyBird.view.ImageNotFoundException;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class PipeViewComponent implements ViewComponent {
    private PipeList pipeList;
    private final String imagePath = "./src/FlappyBird/view/images/pipes/";
    private final String[] pipeColors = {"green", "red"};
    private final String[] pipePositions = {"lower", "upper"};
    private Image[][] images;

    private Random rnd = new Random();

    public PipeViewComponent() {
        loadImages();
        // TODO: delete this
    }

    public PipeViewComponent(PipeList pipeList) {
        loadImages();
        this.pipeList = pipeList;
    }

    @Override
    public void paint(Graphics g) {
        int x = rnd.nextInt(200);
        int upperBottom = rnd.nextInt(200);
        int lowerTop = upperBottom + 50;

        int color = rnd.nextInt(2);

        int upperTop = upperBottom - images[color][1].getHeight(null);

        g.drawImage(images[color][0], x, lowerTop, 52, 320, null);
        g.drawImage(images[color][1], x, upperTop, 52, 320, null);
    }

    private void loadImages() {
        images = new Image[pipeColors.length][pipePositions.length];
        try {
            for (int i = 0; i < pipeColors.length; ++i) {
                for (int j = 0; j < pipePositions.length; ++j) {
                    String filepath = imagePath + "pipe-" + pipeColors[i] + "-" + pipePositions[j] + ".png";
                    images[i][j] = ImageIO.read(new File(filepath));
                }
            }
        } catch (IOException ignore) { throw new ImageNotFoundException(); }
    }
}
