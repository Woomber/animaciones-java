package animation.scenes;

import dibujante.Dibujante;
import matrix.Coordenadas2D;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

public class LandscapeBuilder {

    public static final double DEG_TO_RAD = Math.PI / 180;

    protected static BufferedImage lastMoon;

    public static Coordenadas2D polarToRect(double radius, double deg) {
        return new Coordenadas2D(radius * Math.cos(deg * DEG_TO_RAD), radius * Math.sin(deg * DEG_TO_RAD));
    }

    public static double random(double min, double max) {
        return  Math.random() * (max - min + 1) + min;
    }

    public static BufferedImage getLastMoon() {
        return lastMoon;
    }

    public static BufferedImage buildMoon(int width, int height, ImageObserver observer) {

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Dibujante d = new Dibujante(image, observer);
        d.setOrigin(width/2, height/2);
        d.fill(0, 0);

        d.setColor(new Color(255, 254, 253));
        for(int i = 0; i < 50; i++) {
            Coordenadas2D coords = LandscapeBuilder.polarToRect(LandscapeBuilder.random(290, 470), LandscapeBuilder.random(190, 350));
            d.drawCircle(coords.getIntX(), coords.getIntY(), 1);
        }
        for(int i = 0; i < 15; i++) {
            Coordenadas2D coords = LandscapeBuilder.polarToRect(LandscapeBuilder.random(170, 270), LandscapeBuilder.random(225, 305));
            d.drawCircle(coords.getIntX(), coords.getIntY(), 1);
        }


        d.setColor(new Color(200, 200, 201));
        d.drawCircle(0, height/2 + 500, width * 23 / 20);
        d.fill(0, height/2 - 10);


        d.setColor(new Color(180, 180, 181));
        d.fillEllipse(-130, -70, 40, 25);
        d.fillEllipse(170, -55, 45, 23);
        d.fillEllipse(2, -12, 33, 14);
        d.fillEllipse(-270, 20, 40, 28);
        d.fillEllipse(385, 40, 45, 30);
        d.fillEllipse(150, 100, 30, 16);
        d.fillEllipse(-50, 250, 70, 39);

        lastMoon = image;
        return image;
    }

}
