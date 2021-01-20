import animation.*;
import animation.scenes.EarthRotation;
import animation.scenes.AlienVivo;
import animation.scenes.RocketArrival;
import animation.scenes.RocketLaunch;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class MainWindow extends JFrame implements AnimationListener {

    protected Animation earth;
    protected Animation rocket;
    protected Animation vivo;
    protected Animation arrival;

    public MainWindow() {
        super("Proyecto Animación 2D - Yael Chavoya");
        this.setSize(800, 600);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        earth = new EarthRotation(getWidth(), getHeight(), this);
        earth.addAnimationListener(this);

        rocket = new RocketLaunch(getWidth(), getHeight(), this);
        rocket.addAnimationListener(this);

        vivo = new AlienVivo(getWidth(), getHeight(), this);
        vivo.addAnimationListener(this);

        arrival = new RocketArrival(getWidth(), getHeight(), this);
        arrival.addAnimationListener(this);

        this.setVisible(true);
    }

    protected synchronized void startAnimation(Animation animation) {
        try {
            animation.animate();
        } catch (IllegalThreadStateException ignored) {}
    }

    @Override
    public void paint(Graphics g) {
        //startAnimation(earth);
        startAnimation(vivo);
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
            startAnimation(vivo);
        } else if(sender.equals(vivo)) {
            arrival.setFrameAsBackground(lastFrame);
            startAnimation(arrival);
        } else if(sender.equals(arrival)) {
            System.out.println("Finalizado");
        }
    }
}
