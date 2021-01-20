package animation;

import matrix.Coordenadas2D;
import matrix.Matriz;
import matrix.MatrizRotacion;
import matrix.MatrizTraslacion;

import java.awt.*;
import java.awt.image.ImageObserver;

public class EarthRotation extends Animation {

    protected static final double MOON_RADIUS = 30;
    protected static final double DEG_TO_RAD = Math.PI / 180;
    protected static Coordenadas2D[] moon = new Coordenadas2D[180];
    static {
        for(int i = 0; i < 180; i++) {
            moon[i] = new Coordenadas2D(MOON_RADIUS * Math.cos(i*2 * DEG_TO_RAD), MOON_RADIUS * Math.sin(i*2 * DEG_TO_RAD));
        }
    }

    public EarthRotation(int width, int height, ImageObserver observer) {
        super(moon, width, height, observer);

        setDelay(50);
        setOrigin(new Coordenadas2D(width/2, height/2));
        setFillColor(new Color(200, 200, 200));

        setupBackground();
        setupForeground();
    }

    protected void setupBackground() {
        background.setColor(Color.BLACK);
        background.fill(1, 1);
        background.setColor(Color.BLUE);
        background.drawCircle(0, 0, 110);
        background.fill(0, 0);

        Coordenadas2D[] earthContinents = new Coordenadas2D[]{
            new Coordenadas2D(0, -70),
            new Coordenadas2D(-10, -60),
            new Coordenadas2D(-30, -65),
            new Coordenadas2D(-50, -58),
            new Coordenadas2D(0, 10),
            new Coordenadas2D(30, -10),
        };
        background.setColor(new Color(0, 120, 0));
        background.drawAndFillPolygon(earthContinents, Color.GREEN);

    }

    protected void setupForeground() {
        timer.addOperation(new MatrizTraslacion(250, 0));

        Matriz R = new MatrizRotacion(2, true);

        for(int i = 0; i < 360; i++) {
            timer.addOperation(R);
        }
    }
}
