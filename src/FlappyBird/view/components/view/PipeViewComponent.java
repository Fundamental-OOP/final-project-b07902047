package FlappyBird.view.components.view;

import FlappyBird.models.PipeType;
import FlappyBird.models.objects.Object;
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
    private final String[] pipeColors = PipeType.getNames();
    private BufferedImage[] upperPipeImages, lowerPipeImages;

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
        paintUpperPipe(g, pipe);
        paintLowerPipe(g, pipe);
    }

    private void paintLowerPipe(Graphics g, Pipe pipe) {
        int color = pipe.getType().ordinal();
        Object halfPipe = pipe.getBottomPipe();
        if (halfPipe.getHeight() > 0) {
            BufferedImage image = lowerPipeImages[color];
            BufferedImage subImage = image.getSubimage(0, 0, image.getWidth(), Math.min(halfPipe.getHeight(), image.getHeight()));
            g.drawImage(subImage, pipe.getX(), halfPipe.getY(), pipe.getWidth(), halfPipe.getHeight(), null);
        }
    }

    private void paintUpperPipe(Graphics g, Pipe pipe) {
        int color = pipe.getType().ordinal();
        Object halfPipe = pipe.getUpperPipe();
        if (halfPipe.getHeight() > 0) {
            BufferedImage image = upperPipeImages[color];
            BufferedImage subImage = image.getSubimage(0, Math.max(image.getHeight() - halfPipe.getHeight(), 0), image.getWidth(), Math.min(halfPipe.getHeight(), image.getHeight()));
            g.drawImage(subImage, pipe.getX(), halfPipe.getY(), pipe.getWidth(), halfPipe.getHeight(), null);
        }
    }

    private void loadImages() {
        upperPipeImages = new BufferedImage[pipeColors.length];
        lowerPipeImages = new BufferedImage[pipeColors.length];
        try {
            for (int i = 0; i < pipeColors.length; ++i) {
                String upperPipeImagePath = imagePath + "pipe-" + pipeColors[i] + "-upper" + ".png";
                upperPipeImages[i] = ImageIO.read(new File(upperPipeImagePath));

                String lowerPipeImagePath = imagePath + "pipe-" + pipeColors[i] + "-lower" + ".png";
                lowerPipeImages[i] = ImageIO.read(new File(lowerPipeImagePath));
            }
        } catch (IOException ignore) {
            throw new ImageNotFoundException();
        }
    }
}
