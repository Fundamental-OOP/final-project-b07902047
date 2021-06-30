package FlappyBird.models.objects;

import FlappyBird.Const;
import FlappyBird.models.PipeType;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class PipeList implements SelfControlled {
    private List<Pipe> pipes;
    private int nextPipeGapX;
    private int groundY;
    private Random rnd;

    public PipeList(int groundY) {
        pipes = new ArrayList<Pipe>();
        nextPipeGapX = 100; // The first pipe will be 100 distance away
        this.groundY = groundY; // The y coordinate of the ground. Pipes should be above it.
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
        while (pipes.size() != 0 && pipes.get(0).getX() + pipes.get(0).getWidth() < 0) {
            pipes.remove(0);
        }
    }

    private void getNewPipe() {
        if (pipes.size() == 0 || pipes.get(pipes.size() - 1).getX() + nextPipeGapX < Const.screenX) {
            int gapY = getNextPipeGapY(Const.minPipeGapY, Const.maxPipeGapY);
            int upperY = rnd.nextInt(groundY - gapY); // heigher than ground and leave enough space for gap
            pipes.add(
                new Pipe(
                    PipeType.GREEN,
                    Const.screenX,
                    Const.pipeWidth,
                    upperY,
                    gapY,
                    groundY - upperY - gapY
                )
            );
            nextPipeGapX = getNextPipeGapX(Const.minPipeGapX, Const.maxPipeGapX);
        }
    }

    @Override
    public void updatePosition() {
        updatePipePosition(Const.forwardSpeed);
        removeOutOfBoundPipes();
        getNewPipe();
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

    public List<Pipe> getPipes(){
        return pipes;
    }

    public void removeAllPipes() {
        pipes.clear();
    }

    @Override
    public List<Object> getObjects() {
        List<Object> objects = new ArrayList<Object>();
        for (Pipe pipe : pipes) {
            objects.add(pipe.getUpperPipe());
            objects.add(pipe.getBottomPipe());
        }
        return objects;
    }
}
