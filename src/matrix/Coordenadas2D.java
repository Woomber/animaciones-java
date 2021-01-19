package matrix;

import java.util.Arrays;

public class Coordenadas2D extends Matriz {

    public Coordenadas2D() {
        this(0, 0);
    }

    public Coordenadas2D(double x, double y) {
        super(1, 3);
        setX(x);
        setY(y);
        set(0, 2, 1);
    }

    public Coordenadas2D(Coordenadas2D copia) {
        this(copia.getX(), copia.getY());
    }

    public Coordenadas2D(Matriz matriz) {
        this();
        if(matriz.getWidth() != 1 || matriz.getHeight() != 3) {
            throw new ArithmeticException();
        }
        setX(matriz.get(0, 0));
        setY(matriz.get(0, 1));
    }

    public double getX() {
        return get(0, 0);
    }

    public double getY() {
        return  get(0, 1);
    }

    public int getIntX() {
        return (int) Math.round(getX());
    }

    public int getIntY() {
        return (int) Math.round(getY());
    }

    public void setX(double val) {
        set(0, 0, val);
    }

    public void setY(double val) {
        set(0, 1, val);
    }

}
