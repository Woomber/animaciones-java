package animation.scenes;

import animation.Animation;
import animation.Polygon;
import matrix.*;

import java.awt.*;
import java.awt.image.ImageObserver;

public class RocketAttacked extends Animation {
    public RocketAttacked(int width, int height, ImageObserver observer) {
        super(width, height, observer);

        setDelay(30);
        setOrigin(new Coordenadas2D(width/2 - 240, height/2 + 100));
        setupForeground();
    }

    protected void setupForeground() {
        Color detail = new Color(100, 100, 220), detail2 = new Color(1, 0, 120);

        Matriz escala = new MatrizEscalado(2);

        // Nave espacial
        timer.addPolygon(new animation.Polygon(new Coordenadas2D[] {
                new Coordenadas2D(new Coordenadas2D(-25, 0).product(escala)),
                new Coordenadas2D(new Coordenadas2D(-25, -75).product(escala)),
                new Coordenadas2D(new Coordenadas2D(25, -75).product(escala)),
                new Coordenadas2D(new Coordenadas2D(25, 0).product(escala)),
        }, new Color(180, 180, 223), Color.WHITE));
        timer.addPolygon(new animation.Polygon(new Coordenadas2D[] {
                new Coordenadas2D(new Coordenadas2D(-25, -75).product(escala)),
                new Coordenadas2D(new Coordenadas2D(25, -75).product(escala)),
                new Coordenadas2D(new Coordenadas2D(0, -100).product(escala)),
        }, detail, detail2));
        timer.addPolygon(new animation.Polygon(new Coordenadas2D[] {
                new Coordenadas2D(new Coordenadas2D(-25, -50).product(escala)),
                new Coordenadas2D(new Coordenadas2D(-25, 0).product(escala)),
                new Coordenadas2D(new Coordenadas2D(-50, 0).product(escala)),
        }, detail, detail2));
        timer.addPolygon(new animation.Polygon(new Coordenadas2D[] {
                new Coordenadas2D(new Coordenadas2D(25, -50).product(escala)),
                new Coordenadas2D(new Coordenadas2D(25, 0).product(escala)),
                new Coordenadas2D(new Coordenadas2D(50, 0).product(escala)),
        },  detail, detail2));
        timer.addPolygon(new Polygon(new Coordenadas2D[] {
                new Coordenadas2D(new Coordenadas2D(-20, 0).product(escala)),
                new Coordenadas2D(new Coordenadas2D( 20, 0).product(escala)),
                new Coordenadas2D(new Coordenadas2D(25, 12.5).product(escala)),
                new Coordenadas2D(new Coordenadas2D(-25, 12.5).product(escala)),
        }, new Color(50, 51, 80), new Color(50, 51, 81)));

        Matriz mini = new MatrizEscalado(0.8);
        for(int i = 0; i < 10; i++) {
            timer.addOperation(mini);
        }

        for(int i = 0; i < 100; i++) {
            timer.addOperation(new MatrizIdentidad(3));
        }


    }
}
