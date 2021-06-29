package FlappyBird.view;

import FlappyBird.Const;
import FlappyBird.events.BaseEvent;
import FlappyBird.events.EventManager;
import FlappyBird.events.Listener;
import FlappyBird.events.ResetEvent;
import FlappyBird.models.Model;
import FlappyBird.view.components.BackgroundViewComponent;
import FlappyBird.view.components.GroundViewComponent;
import FlappyBird.view.components.ViewComponent;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

class Renderer extends JPanel {
    Model model;

    BackgroundViewComponent background;
    GroundViewComponent ground;

    final private List<ViewComponent> components = new ArrayList<>();

    Renderer(Model model) {
        super();

        this.model = model;

        setSize(Const.screenX, Const.screenY);
        setLayout(new BorderLayout());
    }

    public void addViewComponent(ViewComponent component) {
        components.add(component);
    }

    public void removeViewComponent(ViewComponent component) {
        components.remove(component);
    }

    public void render() {
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        components.forEach(components -> components.paint(g));
    }
}
