package FlappyBird.view;

import FlappyBird.Const;
import FlappyBird.models.Model;
import FlappyBird.view.components.view.ViewComponent;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;



class Renderer extends JPanel {
    Model model;

    final private List<ViewComponent> components = new ArrayList<>();

    Renderer(Model model) {
        super();

        this.model = model;

        setSize(Const.screenX, Const.screenY);
        setLayout(new BorderLayout());
    }

    public Renderer addViewComponent(ViewComponent component) {
        components.add(component);
        return this;
    }

    public Renderer removeViewComponent(ViewComponent component) {
        components.remove(component);
        return this;
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
