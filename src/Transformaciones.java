import animation.Animation;
import animation.AnimationListener;
import animation.ShapeTest;
import dibujante.Dibujante;
import matrix.*;
import timer.PaintListener;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Transformaciones extends JFrame implements AnimationListener {

    protected Animation shapes;

    public Transformaciones() {
        super("Transformaciones");
        this.setSize(800, 600);
        this.setResizable(false);

        shapes = new ShapeTest(getWidth(), getHeight(), this);
        shapes.setFillColor(Color.RED);
        shapes.addAnimationListener(this);

        this.setVisible(true);
    }

    protected synchronized void startAnimation() {
        try {
            shapes.animate();
        } catch (IllegalThreadStateException ignored) {}
    }

    @Override
    public void paint(Graphics g) {
        startAnimation();
    }

    public static void main(String[] args) {
        new Transformaciones();
    }

    @Override
    public void draw(BufferedImage frame) {
        getGraphics().drawImage(frame, 0, 0, this);
    }
}
