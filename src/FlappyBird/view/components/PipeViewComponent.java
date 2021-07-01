package FlappyBird.view.components;

import FlappyBird.models.objects.Pipe;
import FlappyBird.models.objects.PipeList;
import FlappyBird.view.ImageNotFoundException;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PipeViewComponent implements ViewComponent {
    private final PipeList pipeList;
    private final String imagePath = "./src/FlappyBird/view/images/pipes/";
    private final String[] pipeColors = {"green", "red"};
    private final String[] pipePositions = {"lower", "upper"};
    private BufferedImage[][] images;

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
        BufferedImage bottomPipeImage = images[color][0];
        BufferedImage upperPipeImage = images[color][1];
        if(pipe.getBottomPipe().getHeight()>0) {
            BufferedImage bottomPipe = bottomPipeImage.getSubimage(0, 0, bottomPipeImage.getWidth(), Math.min(pipe.getBottomPipe().getHeight(), bottomPipeImage.getHeight()));
            g.drawImage(bottomPipe, pipe.getX(), pipe.getBottomPipe().getY(), pipe.getWidth(), pipe.getBottomPipe().getHeight(), null);
        }
        if(pipe.getUpperPipe().getHeight()>0) {
            BufferedImage upperPipe = upperPipeImage.getSubimage(0, Math.max(upperPipeImage.getHeight() - pipe.getUpperPipe().getHeight(), 0), upperPipeImage.getWidth(), Math.min(pipe.getUpperPipe().getHeight(), upperPipeImage.getHeight()));
            g.drawImage(upperPipe, pipe.getX(), pipe.getUpperPipe().getY(), pipe.getWidth(), pipe.getUpperPipe().getHeight(), null);
        }
    }

    private void loadImages() {
        images = new BufferedImage[pipeColors.length][pipePositions.length];
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
