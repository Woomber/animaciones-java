import animation.*;
import animation.scenes.EarthRotation;
import animation.scenes.RocketLaunch;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class MainWindow extends JFrame implements AnimationListener {

    protected Animation earth;
    protected Animation rocket;

    public MainWindow() {
        super("Transformaciones");
        this.setSize(800, 600);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        earth = new EarthRotation(getWidth(), getHeight(), this);
        earth.addAnimationListener(this);

        rocket = new RocketLaunch(getWidth(), getHeight(), this);
        rocket.addAnimationListener(this);

        this.setVisible(true);
    }

    protected synchronized void startAnimation(Animation animation) {
        try {
            animation.animate();
        } catch (IllegalThreadStateException ignored) {}
    }

    @Override
    public void paint(Graphics g) {
        startAnimation(earth);
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
            rocket.setFrameAsBackground(lastFrame);
            startAnimation(rocket);
        } else if(sender.equals(rocket)) {
            System.out.println("Finalizado");
        }
    }
}
