package timer;

import matrix.Coordenadas2D;
import matrix.Matriz;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MovementTimer extends Thread {

    protected final Coordenadas2D[] figuraInicial;
    protected final List<Matriz> operations;

    protected List<PaintListener> listeners;

    protected int delay = 150;
    protected int initialDelay = 0;

    public MovementTimer(Coordenadas2D[] figuraInicial) {
        this(figuraInicial, new Matriz[0]);
    }

    public MovementTimer(Coordenadas2D[] figuraInicial, Matriz[] operations) {
        this.figuraInicial = figuraInicial;
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

    protected void drawPolygon(Coordenadas2D[] polygon, int order) {
        for (PaintListener l: listeners) {
            l.drawPolygon(polygon, order);
        }
    }

    @Override
    public void run() {
        try {
            Coordenadas2D[] figuraPrima = new Coordenadas2D[figuraInicial.length];
            int order = 0;

            for (int i = 0; i < figuraInicial.length; i++) {
                figuraPrima[i] = new Coordenadas2D(figuraInicial[i]);
            }

            sleep(initialDelay);

            drawPolygon(figuraPrima, order++);


            for (Matriz op : operations) {
                for(int i = 0; i < figuraPrima.length; i++) {
                    figuraPrima[i] = new Coordenadas2D(figuraPrima[i].product(op));
                }
                sleep(delay);
                drawPolygon(figuraPrima, order++);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}