package animation.scenes;

import animation.Animation;
import animation.Polygon;
import dibujante.Dibujante;
import matrix.Coordenadas2D;
import matrix.MatrizIdentidad;
import matrix.MatrizTraslacion;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

public class AlienVivo extends Animation {

    public AlienVivo(int width, int height, ImageObserver observer) {
        super(width, height, observer);

        setDelay(30);
        setInitialDelay(1400);
        setOrigin(new Coordenadas2D(width/2, height/2));

        setupBackground();
        setupForeground();
    }

    protected void setupBackground() {
        BufferedImage moon = LandscapeBuilder.buildMoon(getWidth(), getHeight(), observer);

        BufferedImage alien = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
        Dibujante d = new Dibujante(alien, observer);
        d.setOrigin(getWidth()/2, getHeight()/2);

        Color alienColor = new Color(0, 110, 40);
        d.setColor(alienColor);
        d.fillCircle(200, 0, 75);
        d.drawAndFillPolygon(new Coordenadas2D[]{
                new Coordenadas2D(150, 45),
                new Coordenadas2D(200, 55),
                new Coordenadas2D(185, 120),
        }, alienColor);
        d.drawAndFillPolygon(new Coordenadas2D[]{
                new Coordenadas2D(250, 45),
                new Coordenadas2D(200, 55),
                new Coordenadas2D(215, 120),
        }, alienColor);
        d.drawRectangle(197, -120, 6, 70);
        d.fill(200, -115);

        d.setColor(new Color(20, 140, 80));
        d.fillCircle(200, -121, 10);
        d.fill(200, -117);

        setFrameAsBackground(moon);
        setFrameAsBackground(alien);
    }

    protected void setupForeground() {
        Coordenadas2D[] eyeWhite = new Coordenadas2D[45];
        Coordenadas2D[] eyeBlack = new Coordenadas2D[45];
        for(int i = 0; i < 45; i++) {
            eyeWhite[i] = new Coordenadas2D(
                    new Coordenadas2D(30 * Math.cos(i*8 * LandscapeBuilder.DEG_TO_RAD), 30 * Math.sin(i*8 * LandscapeBuilder.DEG_TO_RAD)).product(new MatrizTraslacion(200, 0))
            );
            eyeBlack[i] = new Coordenadas2D(
                    new Coordenadas2D(6 * Math.cos(i*8*LandscapeBuilder.DEG_TO_RAD), 6 * Math.sin(i*8 * LandscapeBuilder.DEG_TO_RAD)).product(new MatrizTraslacion(200, 0))
            );
        }

        timer.addPolygon(new Polygon(eyeWhite, new Color(255, 255, 254), new Color(255, 254, 254)));
        timer.addPolygon(new Polygon(eyeBlack, new Color(0, 1, 2), new Color(1, 1, 2)));

        for (int i = 0; i < 30; i+=2) {
            timer.addOperation(new MatrizTraslacion(2, 0.4));
        }
        for (int i = 0; i < 80; i+=2) {
            timer.addOperation(new MatrizIdentidad(3));
        }
        for (int i = 0; i < 60; i+=2) {
            timer.addOperation(new MatrizTraslacion(-2, -0.6));
        }

    }
}
