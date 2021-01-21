package animation.scenes;

import animation.Animation;
import animation.Polygon;
import matrix.*;

import java.awt.*;
import java.awt.image.ImageObserver;

public class Admiracion extends Animation {

    public Admiracion(int width, int height, ImageObserver observer) {
        super(width, height, observer);

        setDelay(30);
        setOrigin(new Coordenadas2D(width * 7 / 8, height/2));

        setupForeground();
    }

    protected void setupForeground() {

        Color bubbleColor = new Color(255, 255, 254);
        Coordenadas2D[] bubble = new Coordenadas2D[45];
        for(int i = 0; i < 45; i++) {
            bubble[i] = new Coordenadas2D(
                    new Coordenadas2D(50 * Math.cos(i * 8 * LandscapeBuilder.DEG_TO_RAD), 40 * Math.sin(i * 8 * LandscapeBuilder.DEG_TO_RAD)).product(new MatrizTraslacion(-250, -100))
            );
        }
        timer.addPolygon(new Polygon(bubble, bubbleColor, bubbleColor));
        timer.addPolygon(new Polygon(new Coordenadas2D[] {
                new Coordenadas2D(-250,-80),
                new Coordenadas2D(-180, -30),
                new Coordenadas2D(-210, -80),
        }, bubbleColor, bubbleColor));

        Color warningColor = new Color(120, 0, 1);
        timer.addPolygon(new Polygon(new Coordenadas2D[]{
                new Coordenadas2D(-260, -125),
                new Coordenadas2D(-240, -125),
                new Coordenadas2D(-250, -95),
        }, warningColor, warningColor));

        Coordenadas2D[] dot = new Coordenadas2D[45];
        for(int i = 0; i < 45; i++) {
            dot[i] = new Coordenadas2D(
                    new Coordenadas2D(5 * Math.cos(i * 8 * LandscapeBuilder.DEG_TO_RAD), 5 * Math.sin(i * 8 * LandscapeBuilder.DEG_TO_RAD)).product(new MatrizTraslacion(-250, -80))
            );
        }
        timer.addPolygon(new Polygon(dot, warningColor, warningColor));


        Matriz left = new MatrizRotacion(-1, true);
        Matriz right = new MatrizRotacion(1, true);
        for(int i = 0; i < 2; i++) {
            for(int j = 0; j < 15; j++) {
                timer.addOperation(left);
            }
            for(int j = 0; j < 5; j++) {
                timer.addOperation(new MatrizIdentidad(3));
            }
            for(int j = 0; j < 15; j++) {
                timer.addOperation(right);
            }
            for(int j = 0; j < 5; j++) {
                timer.addOperation(new MatrizIdentidad(3));
            }
        }
    }
}
