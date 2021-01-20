package animation.scenes;

import animation.Animation;
import animation.Polygon;
import matrix.Coordenadas2D;
import matrix.Matriz;
import matrix.MatrizRotacion;
import matrix.MatrizTraslacion;

import java.awt.*;
import java.awt.image.ImageObserver;

public class EarthRotation extends Animation {

    protected static final double MOON_RADIUS = 30;

    public EarthRotation(int width, int height, ImageObserver observer) {
        super(width, height, observer);

        setDelay(30);
        setInitialDelay(1000);
        setOrigin(new Coordenadas2D(width/2, height/2));

        setupBackground();
        setupForeground();
    }

    protected void setupBackground() {
        background.setColor(Color.BLACK);
        background.fill(1, 1);

        background.setColor(new Color(254, 254, 253));
        for(int i = 0; i < 50; i++) {
            Coordenadas2D coords = LandscapeBuilder.polarToRect(LandscapeBuilder.random(115, 400), LandscapeBuilder.random(0, 359));
            background.drawCircle(coords.getIntX(), coords.getIntY(), 1);
        }

        background.setColor(Color.BLUE);
        background.drawCircle(0, 0, 110);
        background.fill(0, 0);

        Coordenadas2D[] earthContinents = new Coordenadas2D[45];
        for(int i = 0; i < 45; i++) {
            earthContinents[i] = LandscapeBuilder.polarToRect(53 + LandscapeBuilder.random(0.13, 0.17) * (i % 30), i * 8);
            earthContinents[i] = new Coordenadas2D(earthContinents[i].product(new MatrizTraslacion(-10, 15)));
        }
        Coordenadas2D[] earthIsland = new Coordenadas2D[22];
        for(int i = 0; i < 22; i++) {
            earthIsland[i] = LandscapeBuilder.polarToRect(10 + LandscapeBuilder.random(0.05, 0.07) * (i % 15), i * 16);
            earthIsland[i] = new Coordenadas2D(earthIsland[i].product(new MatrizTraslacion(40, -60)));
        }

        background.setColor(new Color(0, 120, 0));
        background.drawAndFillPolygon(earthContinents, Color.GREEN);
        background.drawAndFillPolygon(earthIsland, Color.GREEN);

    }

    protected void setupForeground() {

        Coordenadas2D[] moon = new Coordenadas2D[180];
        Coordenadas2D[][] crateres = new Coordenadas2D[][]{
                new Coordenadas2D[45],
                new Coordenadas2D[45],
                new Coordenadas2D[45],
        };

        Matriz start = new MatrizTraslacion(250, 0);

        for(int i = 0; i < 180; i++) {
            moon[i] = new Coordenadas2D(
                    new Coordenadas2D(MOON_RADIUS * Math.cos(i*2 * LandscapeBuilder.DEG_TO_RAD), MOON_RADIUS * Math.sin(i*2 * LandscapeBuilder.DEG_TO_RAD)).product(start)
            );
        }
        for(int i = 0; i < 45; i++) {
            crateres[0][i] = new Coordenadas2D(
                    new Coordenadas2D(MOON_RADIUS/4 * Math.cos(i*8 * LandscapeBuilder.DEG_TO_RAD) - 10, MOON_RADIUS/4 * Math.sin(i*8 * LandscapeBuilder.DEG_TO_RAD) - 10).product(start)
            );
        }
        for(int i = 0; i < 45; i++) {
            crateres[1][i] = new Coordenadas2D(
                    new Coordenadas2D(MOON_RADIUS/3.2 * Math.cos(i*8 * LandscapeBuilder.DEG_TO_RAD) + 18, MOON_RADIUS/3.2 * Math.sin(i*8 * LandscapeBuilder.DEG_TO_RAD) + 0).product(start)
            );
        }
        for(int i = 0; i < 45; i++) {
            crateres[2][i] = new Coordenadas2D(
                    new Coordenadas2D(MOON_RADIUS/3.5 * Math.cos(i*8 * LandscapeBuilder.DEG_TO_RAD) - 2, MOON_RADIUS/3.5 * Math.sin(i*8 * LandscapeBuilder.DEG_TO_RAD) + 20).product(start)
            );
        }

        timer.addPolygon(new animation.Polygon(moon, new Color(90, 90, 90), new Color(200, 200, 200)));
        for(int i = 0; i < crateres.length; i++) {
            timer.addPolygon(new Polygon(crateres[i], new Color(110, 110, 110), new Color(100, 100,100)));
        }

        Matriz R = new MatrizRotacion(2, true);

        for(int i = 0; i < 120; i++) {
            timer.addOperation(R);
        }
    }
}
