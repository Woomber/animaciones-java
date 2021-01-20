package animation.scenes;

import animation.Animation;
import animation.Polygon;
import matrix.Coordenadas2D;
import matrix.Matriz;
import matrix.MatrizEscalado;
import matrix.MatrizTraslacion;

import java.awt.*;
import java.awt.image.ImageObserver;

public class RocketArrival extends Animation {

    public RocketArrival(int width, int height, ImageObserver observer) {
        super(width, height, observer);
        setDelay(30);
        setOrigin(new Coordenadas2D(width/2, height/2));

        setupForeground();
    }

    protected void setupForeground() {
        Color detail = new Color(220, 100, 100), detail2 = new Color(120, 0, 1);

        Matriz start = new MatrizTraslacion(-120, -250);
        Matriz escala = new MatrizEscalado(2);

        // Nave espacial
        timer.addPolygon(new animation.Polygon(new Coordenadas2D[] {
                new Coordenadas2D(new Coordenadas2D(-25, 0).product(start).product(escala)),
                new Coordenadas2D(new Coordenadas2D(-25, -75).product(start).product(escala)),
                new Coordenadas2D(new Coordenadas2D(25, -75).product(start).product(escala)),
                new Coordenadas2D(new Coordenadas2D(25, 0).product(start).product(escala)),
        }, new Color(223, 222, 223), Color.WHITE));
        timer.addPolygon(new animation.Polygon(new Coordenadas2D[] {
                new Coordenadas2D(new Coordenadas2D(-25, -75).product(start).product(escala)),
                new Coordenadas2D(new Coordenadas2D(25, -75).product(start).product(escala)),
                new Coordenadas2D(new Coordenadas2D(0, -100).product(start).product(escala)),
        }, detail, detail2));
        timer.addPolygon(new animation.Polygon(new Coordenadas2D[] {
                new Coordenadas2D(new Coordenadas2D(-25, -50).product(start).product(escala)),
                new Coordenadas2D(new Coordenadas2D(-25, 0).product(start).product(escala)),
                new Coordenadas2D(new Coordenadas2D(-50, 0).product(start).product(escala)),
        }, detail, detail2));
        timer.addPolygon(new animation.Polygon(new Coordenadas2D[] {
                new Coordenadas2D(new Coordenadas2D(25, -50).product(start).product(escala)),
                new Coordenadas2D(new Coordenadas2D(25, 0).product(start).product(escala)),
                new Coordenadas2D(new Coordenadas2D(50, 0).product(start).product(escala)),
        },  detail, detail2));
        timer.addPolygon(new Polygon(new Coordenadas2D[] {
                new Coordenadas2D(new Coordenadas2D(-20, 0).product(start).product(escala)),
                new Coordenadas2D(new Coordenadas2D( 20, 0).product(start).product(escala)),
                new Coordenadas2D(new Coordenadas2D(25, 12.5).product(start).product(escala)),
                new Coordenadas2D(new Coordenadas2D(-25, 12.5).product(start).product(escala)),
        }, new Color(80, 81, 80), new Color(80, 81, 81)));

        Matriz down = new MatrizTraslacion(0, 6);
        for(int i = 0; i < 100; i++) {
            timer.addOperation(down);
        }
    }

}
