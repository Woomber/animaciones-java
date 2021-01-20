package timer;

import animation.Polygon;
import matrix.Coordenadas2D;
import matrix.Matriz;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MovementTimer extends Thread {

    protected final List<Polygon> polygons;
    protected final List<Matriz> operations;

    protected List<PaintListener> listeners;

    protected int delay = 150;
    protected int initialDelay = 0;

    public MovementTimer() {
        this(new Matriz[0]);
    }

    public MovementTimer(Matriz[] operations) {
        this.polygons = new ArrayList<>();
        this.operations = new ArrayList<>();
        this.listeners = new ArrayList<>();
        Collections.addAll(this.operations, operations);
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public void setInitialDelay(int initialDelay) {
        this.initialDelay = initialDelay;
    }

    public void addPaintListener(PaintListener listener) {
        listeners.add(listener);
    }

    public void addOperation(Matriz op) {
        operations.add(op);
    }

    public void addPolygon(Polygon polygon) {
        polygons.add(polygon);
    }

    protected void drawPolygon(List<Polygon> polygons, int order) {
        for (PaintListener l: listeners) {
            l.drawPolygons(polygons, order);
        }
    }

    protected void finished() {
        for(PaintListener l: listeners) {
            l.finished(this);
        }
    }

    @Override
    public void run() {
        try {
            List<Polygon> figurasPrimas = new ArrayList<>();

            int order = 0;

            sleep(initialDelay);

            for (Polygon figura : polygons) {
                Polygon copia = new Polygon(figura);
                figurasPrimas.add(copia);
            }
            drawPolygon(figurasPrimas, order++);


            for (Matriz op : operations) {
                for(Polygon figura: figurasPrimas) {
                    for(int i = 0; i < figura.getPath().length; i++) {
                        figura.getPath()[i] = new Coordenadas2D(figura.getPath()[i].product(op));
                    }
                }
                sleep(delay);
                drawPolygon(figurasPrimas, order++);
            }

            finished();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}