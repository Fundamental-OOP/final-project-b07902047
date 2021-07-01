package FlappyBird.view.components.view;

import FlappyBird.Const;
import FlappyBird.models.BackgroundTheme;
import FlappyBird.view.ImageNotFoundException;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BackgroundViewComponent implements ViewComponent {
    private final String pathToImageDir = "./src/FlappyBird/view/images/";
    private final List<String> backgroundImages = new ArrayList<>() {{
        add("background-day.png");
        add("background-night.png");
    }};

    private Image currentImage;

    public BackgroundViewComponent(BackgroundTheme backgroundTheme) {
        initializeImage(backgroundTheme);
    }

    public void initializeImage(BackgroundTheme backgroundTheme) {
        int imageIndex = backgroundTheme.ordinal();
        String imageFile = pathToImageDir + backgroundImages.get(imageIndex);
        try {
            currentImage = ImageIO.read(new File(imageFile));
        } catch (IOException e) {
            throw new ImageNotFoundException();
        }
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(currentImage, 0, 0, Const.screenX, Const.screenY, null);
    }
}
