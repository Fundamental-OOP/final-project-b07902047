package FlappyBird.view;

import FlappyBird.Config;
import FlappyBird.events.BaseEvent;
import FlappyBird.events.Listener;
import FlappyBird.events.ResetEvent;
import FlappyBird.models.Model;

import javax.swing.*;
import java.awt.*;

class Renderer extends JPanel implements Listener {
    Model model;

    Background background;

    Renderer(Model model, Config config) {
        super();

        this.model = model;
        this.background = new Background();

        setSize(config.getScreenWidth(), config.getScreenHeight());
        setLayout(new BorderLayout());
        add(background);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    public void render() {
    }

    @Override
    public void onEvent(BaseEvent event) {
        if (event instanceof ResetEvent) {
            background.randomChangeImage();
        }
    }
}
