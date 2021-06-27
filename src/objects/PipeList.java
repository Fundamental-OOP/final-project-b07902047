package objects;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

public class PipeList {
    private List<Pipe> pipes;
    private int nextPipeGapX;
    private Random rnd;

    public PipeList() {
        pipes = new ArrayList<Pipe>();
        nextPipeGapX = 100; // The first pipe will be 100 distance away
        rnd = new Random();
    }

    private int getNextPipeGapX(int minGapX, int maxGapX) {
        // Determine the gap (distance) between the last column and the column to be created.
        int gap = rnd.nextInt(maxGapX - minGapX + 1) + minGapX;
        return gap;
    }

    private int getNextPipeGapY(int minGapY, int maxGapY) {
        // Determine the gap (distance) between the upper column and the bottom column.
        int gap = rnd.nextInt(maxGapY - minGapY + 1) + minGapY;
        return gap;
    }

    private void updatePipePosition(int amount) {
        for (Pipe pipe : pipes) {
            pipe.setX(pipe.getX() - amount);
        }
    }

    private void removeOutOfBoundPipes() {
        while (pipes.size() != 0) {
            if (pipes.get(0).getX() + pipes.get(0).getWidth() < 0) {
                pipes.remove(0);
            }
        }
    }

    private void getNewPipe(Map<String, Integer> config, int groundHeight) {
        if (pipes.size() == 0 || pipes.get(pipes.size() - 1).getX() + nextPipeGapX < config.get("ScreenX")) {
            int gapY = getNextPipeGapY(config.get("MinPipeGapY"), config.get("MaxPipeGapY"));
            int upperY = rnd.nextInt(config.get("ScreenY") - groundHeight - gapY); // heigher than ground and leave enough space for gap
            pipes.add(
                new Pipe(
                    0,
                    config.get("ScreenX"),
                    config.get("PipeWidth"),
                    upperY,
                    gapY,
                    config.get("ScreenY") - groundHeight - upperY - gapY
                )
            );
            nextPipeGapX = getNextPipeGapX(config.get("MinPipeGapX"), config.get("MaxPipeGapX"));
        }
    }

    public void updatePipes(Map<String, Integer> config, int groundHeight) {
        updatePipePosition(config.get("ForwardSpeed"));
        removeOutOfBoundPipes();
        getNewPipe(config, groundHeight);
    }

    public boolean isCollided(Object object) {
        for (Pipe pipe : pipes) {
            if (pipe.isCollided(object)) {
                return true;
            }
        }
        return false;
    }

    public boolean isObjectPassedThrough(Object object, int forwardSpeed) {
        for (Pipe pipe : pipes) {
            // The center point of the object
            int objectFrontX = object.getX() + object.getWidth() / 2;
            // The center point of the pipe
            int pipeFrontX = pipe.getX() + pipe.getWidth() / 2;

            // Check if the object just passed the pipe
            if (objectFrontX - pipeFrontX > 0 && objectFrontX - pipeFrontX <= forwardSpeed) {
                return true;
            }
        }
        return false;
    }
}
