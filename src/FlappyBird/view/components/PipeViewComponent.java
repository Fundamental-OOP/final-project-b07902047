package FlappyBird.view.components;

import FlappyBird.models.objects.Pipe;
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

    public PipeViewComponent(PipeList pipeList) {
        loadImages();
        this.pipeList = pipeList;
    }

    @Override
    public void paint(Graphics g) {
        for (Pipe pipe : pipeList.getPipes()) {
            paintPipe(g, pipe);
        }
    }

    private void paintPipe(Graphics g, Pipe pipe) {
        int color = pipe.getType().ordinal();
        g.drawImage(images[color][0], pipe.getX(), pipe.getBottomPipe().getY(), pipe.getWidth(), pipe.getBottomPipe().getHeight(), null);
        g.drawImage(images[color][1], pipe.getX(), pipe.getUpperPipe().getY(), pipe.getWidth(), pipe.getUpperPipe().getHeight(), null);
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
        } catch (IOException ignore) {
            throw new ImageNotFoundException();
        }
    }
}
