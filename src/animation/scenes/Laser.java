package animation.scenes;

import animation.Animation;
import animation.Polygon;
import matrix.Coordenadas2D;
import matrix.Matriz;
import matrix.MatrizTraslacion;

import java.awt.*;
import java.awt.image.ImageObserver;

public class Laser extends Animation {
    public Laser(int width, int height, ImageObserver observer) {
        super(width, height, observer);

        setDelay(30);
        setInitialDelay(500);
        setOrigin(new Coordenadas2D(width/2 + 160, height/2 - 14));

        setupForeground();
    }

    protected void setupForeground() {
        Color laser = new Color(0, 130, 170);

        timer.addPolygon(new Polygon(new Coordenadas2D[]{
                new Coordenadas2D(-30, -3),
                new Coordenadas2D(-30, 3),
                new Coordenadas2D(0, 3),
                new Coordenadas2D(0, -3),
        }, laser, laser));


        Matriz T = new MatrizTraslacion(-10, 0);
        for(int i = 0; i < 33; i++) {
            timer.addOperation(T);
        }
    }
}
