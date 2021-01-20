import animation.Animation;
import animation.AnimationListener;
import animation.EarthRotation;
import animation.ShapeTest;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class MainWindow extends JFrame implements AnimationListener {

    protected Animation earth;

    public MainWindow() {
        super("Transformaciones");
        this.setSize(800, 600);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        earth = new EarthRotation(getWidth(), getHeight(), this);
        earth.addAnimationListener(this);

        this.setVisible(true);
    }

    protected synchronized void startAnimation() {
        try {
            earth.animate();
        } catch (IllegalThreadStateException ignored) {}
    }

    @Override
    public void paint(Graphics g) {
        startAnimation();
    }

    public static void main(String[] args) {
        new MainWindow();
    }

    @Override
    public void drawFrame(BufferedImage frame) {
        getGraphics().drawImage(frame, 0, 0, this);
    }

    @Override
    public void animationFinished(Object sender, BufferedImage lastFrame) {
        if(sender.equals(earth)) {
            System.out.println("Finalizado");
        }
    }
}
