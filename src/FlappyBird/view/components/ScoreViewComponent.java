package FlappyBird.view.components;

import FlappyBird.models.Model;
import FlappyBird.view.ImageNotFoundException;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class ScoreViewComponent implements ViewComponent {
    private final String imageDirectoryPath = "./src/FlappyBird/view/images/numbers/";
    private Image[] images;
    private final int scoreFontMargin = 3;
    private final Model model;

    public ScoreViewComponent(Model model) {
        this.model = model;
        loadImages();
    }

    @Override
    public void paint(Graphics g) {
        int currentScore = model.getScore();
        List<Integer> scoreDigits = seperateScoreToDigit(currentScore);
        Collections.reverse(scoreDigits);
        int scoreWidth = getScoreWidth(scoreDigits);
        int startX = 144 - scoreWidth / 2;
        for (Integer i : scoreDigits) {
            g.drawImage(images[i], startX, 100, null);
            startX += images[i].getWidth(null) + scoreFontMargin;
        }
        ;
    }

    private List<Integer> seperateScoreToDigit(int score) {
        Stack<Integer> stack = new Stack<>();
        if (score == 0) stack.push(0);
        while (score > 0) {
            stack.push(score % 10);
            score /= 10;
        }
        return new ArrayList<>(stack);
    }

    private int getScoreWidth(List<Integer> digits) {
        return digits
                .stream()
                .mapToInt(i -> images[i].getWidth(null))
                .sum()
                + (digits.size() - 1) * scoreFontMargin;
    }

    private void loadImages() {
        images = new Image[10];
        try {
            for (int i = 0; i < 10; ++i) {
                String filename = imageDirectoryPath + i + ".png";
                images[i] = ImageIO.read(new File(filename));
            }
        } catch (IOException e) {
            throw new ImageNotFoundException();
        }
    }
}
