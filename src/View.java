import events.*;

import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Draws the model state onto the screen.
 */
public class View extends Listener {
    EventManager eventManager;
    Model model;
    boolean isInitialized;
    JFrame jFrame;
    Renderer renderer;

    public View(EventManager eventManager, Model model) {
        this.eventManager = eventManager;
        eventManager.registerListener(this);
        this.model = model;
        this.isInitialized = false;
    }

    public void initialize(int height, int width) {
        renderer = new Renderer();
        jFrame = new JFrame("Flappy Bird");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setSize(width, height);
        jFrame.setResizable(false);
        jFrame.setVisible(true);
    }

    public void repaint(Graphics g) {

    }

    @Override
    void notifyEvent(BaseEvent event) {
        if (event instanceof TickEvent && this.isInitialized) {

        } else if (event instanceof InitializeEvent) {
            this.isInitialized = true;
        } else if (event instanceof JumpEvent) {

        } else if (event instanceof ScoreEvent) {

        } else if (event instanceof HitEvent) {

        } else if (event instanceof QuitEvent) {
            this.isInitialized = false;
        }
    }
}

class Renderer extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        // TODO Auto-generated method stub
        super.paintComponent(g);
    }
}
