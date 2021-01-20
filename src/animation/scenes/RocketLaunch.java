package animation.scenes;

import animation.Animation;
import animation.Polygon;
import matrix.Coordenadas2D;
import matrix.MatrizEscalado;
import matrix.MatrizRotacion;
import matrix.MatrizTraslacion;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

public class RocketLaunch extends Animation {

    public RocketLaunch(int width, int height, ImageObserver observer) {
        super(width, height, observer);

        setDelay(30);
        setOrigin(new Coordenadas2D(width/2, height/2));

        setupForeground();
    }

    protected void setupForeground() {
        Color detail = new Color(220, 100, 100), detail2 = new Color(120, 0, 1);

        // Nave espacial

        timer.addPolygon(new animation.Polygon(new Coordenadas2D[] {
                new Coordenadas2D(-5, 0),
                new Coordenadas2D(-5, -15),
                new Coordenadas2D(5, -15),
                new Coordenadas2D(5, 0),
        }, new Color(223, 222, 223), Color.WHITE));
        timer.addPolygon(new animation.Polygon(new Coordenadas2D[] {
                new Coordenadas2D(-5, -15),
                new Coordenadas2D(5, -15),
                new Coordenadas2D(0, -20),
        }, detail, detail2));
        timer.addPolygon(new animation.Polygon(new Coordenadas2D[] {
                new Coordenadas2D(-5, -10),
                new Coordenadas2D(-5, 0),
                new Coordenadas2D(-10, 0),
        }, detail, detail2));
        timer.addPolygon(new animation.Polygon(new Coordenadas2D[] {
                new Coordenadas2D(5, -10),
                new Coordenadas2D(5, 0),
                new Coordenadas2D(10, 0),
        },  detail, detail2));
        timer.addPolygon(new Polygon(new Coordenadas2D[] {
                new Coordenadas2D(-4, 0),
                new Coordenadas2D( 4, 0),
                new Coordenadas2D(5, 2.5),
                new Coordenadas2D(-5, 2.5),
        }, new Color(80, 81, 80), new Color(80, 81, 81)));

        for(int i = 0; i < 24; i++) {
            timer.addOperation(new MatrizRotacion(2, true));
        }

        for(int i = 0; i < 50; i++) {
            Coordenadas2D delta = LandscapeBuilder.polarToRect(2, 240 - i);
            timer.addOperation(new MatrizTraslacion(delta.getX(), delta.getY()));
            timer.addOperation(new MatrizRotacion(2, true));
            timer.addOperation(new MatrizEscalado(1.02));
        }
        for(int i = 0; i < 58; i++) {
            timer.addOperation(new MatrizTraslacion(-0.5, 3.6));
            timer.addOperation(new MatrizRotacion(0.15, true));
            timer.addOperation(new MatrizEscalado(0.99));
        }
    }
}
