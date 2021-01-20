package animation;

import dibujante.Dibujante;
import matrix.Coordenadas2D;
import timer.MovementTimer;
import timer.PaintListener;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Animation implements PaintListener {

    protected final MovementTimer timer;
    protected final List<AnimationListener> listeners;
    protected final ImageObserver observer;

    protected BufferedImage foregroundBuffer, backgroundBuffer;
    protected BufferedImage drawableBuffer;

    protected Dibujante foreground, background;

    protected Color color = Color.BLACK;

    protected AtomicInteger currentFrame;

    public Animation(int width, int height, ImageObserver observer) {
        this.timer = new MovementTimer();
        this.timer.addPaintListener(this);
        this.listeners = new ArrayList<>();
        this.observer = observer;

        foregroundBuffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        backgroundBuffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        drawableBuffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        drawableBuffer.createGraphics();

        foreground = new Dibujante(foregroundBuffer, observer);
        background = new Dibujante(backgroundBuffer, observer);
        background.clear();
    }

    public void setOrigin(Coordenadas2D origin) {
        background.setOrigin(origin.getIntX(), origin.getIntY());
        foreground.setOrigin(origin.getIntX(), origin.getIntY());
    }

    public void setDelay(int delay) {
        this.timer.setDelay(delay);
    }

    public void setInitialDelay(int initialDelay) {
        this.timer.setInitialDelay(initialDelay);
    }

    public void setFillColor(Color c) {
        this.color = c;
    }

    public int getWidth() {
        return drawableBuffer.getWidth();
    }

    public int getHeight() {
        return drawableBuffer.getHeight();
    }

    public void addAnimationListener(AnimationListener listener) {
        listeners.add(listener);
    }

    protected void drawFrame() {
        Graphics g = drawableBuffer.getGraphics();
        g.drawImage(backgroundBuffer, 0, 0, observer);
        g.drawImage(foregroundBuffer, 0, 0, observer);
    }

    public void sendFrame() {
        for (AnimationListener l : listeners) {
            l.drawFrame(drawableBuffer);
        }
    }

    public synchronized void animate() throws IllegalThreadStateException {
        currentFrame = new AtomicInteger(0);
        timer.start();
    }

    @Override
    public void drawPolygons(List<Polygon> polygons, int order) {
        while(currentFrame.get() < order);
        foregroundBuffer = foreground.resetBuffer(drawableBuffer.getWidth(), drawableBuffer.getHeight(), BufferedImage.TYPE_INT_ARGB);
        for (Polygon polygon : polygons) {
            foreground.setColor(polygon.getBorderColor());
            foreground.drawAndFillPolygon(polygon.getPath(), polygon.getFillColor());
        }
        drawFrame();
        sendFrame();
        postFrameOperations();
        currentFrame.getAndIncrement();
    }

    protected void postFrameOperations() { }

    @Override
    public void finished(Object sender) {
        for(AnimationListener l: listeners) {
            l.animationFinished(this, drawableBuffer);
        }
    }
}
