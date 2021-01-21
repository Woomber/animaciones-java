import animation.*;
import animation.scenes.*;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class MainWindow extends JFrame implements AnimationListener {

    protected Animation dark;
    protected Animation earth;
    protected Animation rocket;
    protected Animation vivo;
    protected Animation arrival;
    protected Animation admiracion;
    protected Animation laser;
    protected Animation attacked;

    public MainWindow() {
        super("Proyecto Animación 2D - Yael Chavoya");
        this.setSize(800, 600);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setupAnimations();

        this.setVisible(true);
    }

    protected void setupAnimations() {
        dark = new DarkScreen(getWidth(), getHeight(), this);
        dark.addAnimationListener(this);

        earth = new EarthRotation(getWidth(), getHeight(), this);
        earth.addAnimationListener(this);

        rocket = new RocketLaunch(getWidth(), getHeight(), this);
        rocket.addAnimationListener(this);

        vivo = new AlienVivo(getWidth(), getHeight(), this);
        vivo.addAnimationListener(this);

        arrival = new RocketArrival(getWidth(), getHeight(), this);
        arrival.addAnimationListener(this);

        admiracion = new Admiracion(getWidth(), getHeight(), this);
        admiracion.addAnimationListener(this);

        laser = new Laser(getWidth(), getHeight(), this);
        laser.addAnimationListener(this);

        attacked = new RocketAttacked(getWidth(), getHeight(), this);
        attacked.addAnimationListener(this);
    }

    protected synchronized void startAnimation(Animation animation) {
        try {
            animation.animate();
        } catch (IllegalThreadStateException ignored) {}
    }

    @Override
    public void paint(Graphics g) {
        startAnimation(dark);
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
        if(sender.equals(dark)) {
            startAnimation(earth);

        } else if(sender.equals(earth)) {
            rocket.setFrameAsBackground(lastFrame);
            startAnimation(rocket);

        } else if(sender.equals(rocket)) {
            startAnimation(vivo);

        } else if(sender.equals(vivo)) {
            arrival.setFrameAsBackground(lastFrame);
            attacked.setFrameAsBackground(lastFrame);
            startAnimation(arrival);

        } else if(sender.equals(arrival)) {
            admiracion.setFrameAsBackground(lastFrame);
            laser.setFrameAsBackground(lastFrame);
            startAnimation(admiracion);

        } else if(sender.equals(admiracion)) {
            startAnimation(laser);
        } else if(sender.equals(laser)) {
            startAnimation(attacked);
        } else if(sender.equals(attacked)) {
            System.out.println("Animación concluida");
            setupAnimations();
            startAnimation(dark);
        }
    }
}
