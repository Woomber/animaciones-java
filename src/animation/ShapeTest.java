package animation;

import matrix.Coordenadas2D;
import matrix.MatrizEscalado;
import matrix.MatrizRotacion;
import matrix.MatrizTraslacion;

import java.awt.*;
import java.awt.image.ImageObserver;

public class ShapeTest extends Animation {

    public ShapeTest(int width, int height, ImageObserver observer) {
        super(new Coordenadas2D[] {
                new Coordenadas2D(0, 90),
                new Coordenadas2D(-40, 0),
                new Coordenadas2D(0, -90),
                new Coordenadas2D(40, 0),
        }, width, height, observer);

        setDelay(50);
        setOrigin(new Coordenadas2D(width/2, height/2));

        final double steps = 20, deg = 120;

        MatrizRotacion R = new MatrizRotacion(deg/steps, true);
        MatrizTraslacion T = new MatrizTraslacion(10, -10);
        MatrizEscalado E = new MatrizEscalado(1.01);

        for(int i = 0; i < 20; i++) {
            timer.addOperation(E);
        }
        for(int i = 0; i < 20; i++) {
            timer.addOperation(R);
        }
        for(int i = 0; i < 20; i++) {
            timer.addOperation(T);
        }

        background.clear();
        background.setColor(new Color(200, 200, 200));
        background.drawLine(0, getHeight()/2, getWidth(), getHeight()/2);
        background.drawLine(getWidth()/2, 0, getWidth()/2, getHeight());
    }
}
