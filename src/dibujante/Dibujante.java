package dibujante;

import matrix.Coordenadas2D;
import matrix.Matriz;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.util.LinkedList;
import java.util.Queue;

public class Dibujante {

    protected static final boolean DEBUG_COORDS = false;

    protected final BufferedImage pixel;

    protected BufferedImage image;
    protected Graphics canvas;

    protected final ImageObserver observer;

    protected Color color = Color.BLACK;
    protected Point origin;
    protected int boundX1 = 0, boundY1 = 0, boundX2, boundY2;

    public Dibujante(BufferedImage image, ImageObserver observer) {
        assert image != null;

        this.image = image;
        this.canvas = image.getGraphics();
        this.observer = observer;

        this.origin = new Point(0, 0);
        this.boundX2 = image.getWidth();
        this.boundY2 = image.getHeight();

        pixel = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        pixel.createGraphics();
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setPixel(int x, int y) {
        pixel.setRGB(0,0, color.getRGB());
        if (DEBUG_COORDS) System.out.format("x: %d, y: %d\n", x, y);
        try {
            canvas.drawImage(pixel, x + origin.getX(), y + origin.getY(), observer);
        } catch (ArrayIndexOutOfBoundsException ignored) {}
    }

    public void clear() {
        Color c = new Color(color.getRGB(), true);
        setColor(Color.WHITE);
        for(int i = 0; i < image.getWidth(); i++) {
            for(int j = 0; j < image.getHeight(); j++) {
                setPixel(i, j);
            }
        }
        setColor(c);
    }

    public BufferedImage resetBuffer(int width, int height, int type) {
        image = new BufferedImage(width, height, type);
        canvas = image.createGraphics();

        return image;
    }

    public void setOrigin(int x, int y) {
        origin = new Point(x, y);
        boundX1 -=  x;
        boundX2 -= x;
        boundY1 -= y;
        boundY1 -= y;
    }

    public Color getPixelColor(int x, int y) {
        try {
            return new Color(image.getRGB(x + origin.getX(), y + origin.getY()), true);
        } catch (ArrayIndexOutOfBoundsException exception) {
            return null;
        }
    }

    protected double degToRad(double deg) {
        return deg * Math.PI / 180.0;
    }

    public void drawLine(int x1, int y1, int x2, int y2) {

        int dy = y2 - y1;
        int dx = x2 - x1;

        if(dy == 0 && dx == 0) {
            setPixel(x1, y1);
            return;
        }

        boolean yMain = Math.abs(y2 - y1) > Math.abs(x2 - x1);

        int dMain, dDep, currMain, currDep, goal;
        int step = 1;

        if(yMain) {
            dMain = dy;
            dDep = dx;
            currMain = y1;
            currDep = x1;
            goal = y2;
        } else {
            dMain = dx;
            dDep = dy;
            currMain = x1;
            currDep = y1;
            goal = x2;
        }

        if(dMain < 0) step = -1;

        float m = (float) dDep / (float) dMain;
        float b = currDep - m*currMain;
        do {
            if(yMain) {
                setPixel(currDep, currMain);
            }
            else {
                setPixel(currMain, currDep);
            }
            currMain += step;
            currDep = Math.round(m * currMain + b);
        }  while(currMain != goal);

        setPixel(x2, y2);
    }

    public void drawLine(Coordenadas2D p1, Coordenadas2D p2) {
        drawLine(p1.getIntX(), p1.getIntY(), p2.getIntX(), p2.getIntY());
    }

    @Deprecated
    public void drawBresenhamLine(int x1, int y1, int x2, int y2) throws IllegalArgumentException {
        int incrX = 1;

        if(y2 < y1) {
            int tmp = y2;
            y2 = y1;
            y1 = tmp;
            tmp = x2;
            x2 = x1;
            x1 = tmp;
        }

        int dy = y2 - y1;
        int dx = x2 - x1;

        if (dy == 0 && dx == 0) {
            setPixel(x1, y1);
            return;
        }
        if(dx < 0) {
            throw new IllegalArgumentException("x2 - x1 debe ser positivo");
        }

        double m, b, c, p;
        int xk = x1, yk = y1;

        if(Math.abs(dy) > Math.abs(dx)) {
            m = (double) dx / (double) dy;
            b = x1 - m * y1;
            c = 2*dx + dy*(2*b - 1);

            do {
                setPixel(xk, yk);

                p = 2 * dx * yk - 2 * dy * xk + c;
                yk += 1;

                if(p > 0) {
                    xk+= incrX;
                }

            } while (yk != y2);
        } else {
            m = (double) dy / (double) dx;
            b = y1 - m * x1;
            c = 2*dy + dx*(2*b - 1);

            do {
                setPixel(xk, yk);

                p = 2 * dy * xk - 2 * dx * yk + c;
                xk += incrX;

                if(p > 0) {
                    yk += 1;
                }

            } while (xk != x2);
        }

        setPixel(x2, y2);
    }

    @Deprecated
    public void drawMidpointLine(int x1, int y1, int x2, int y2) {
        int dy = y2 - y1;
        int dx = x2 - x1;
        int xp = x1, yp = y1;

        final double m = (double) dy / (double) dx;
        final double b = y1 - m * x1;

        double p = dy * (xp + 1) - dx * (yp + 0.5) + b * dx;

        do {
            setPixel(xp++, yp);

            if(p > 0) {
                yp++;
                p += dy - dx;
            } else {
                p += dy;
            }
        } while(xp != x2);

        setPixel(x2, y2);
    }

    public void drawRectangle(int x1, int y1, int width, int height) {
        drawLine(x1, y1, x1 + width, y1);
        drawLine(x1 + width, y1, x1 + width, y1 + height);
        drawLine(x1, y1, x1, y1 + height);
        drawLine(x1, y1 + height, x1 + width, y1 + height);
    }

    public void drawRectangle(Coordenadas2D p1, Coordenadas2D p2) {
        final Coordenadas2D p3 = new Coordenadas2D(p2.getX(), p1.getY());
        final Coordenadas2D p4 = new Coordenadas2D(p1.getX(), p2.getY());
        drawLine(p1, p3);
        drawLine(p1, p4);
        drawLine(p4, p2);
        drawLine(p3, p2);
    }

    public void drawSquare(int x1, int y1, int length) {
        drawRectangle(x1, y1, length, length);
    }

    public void drawDiamond(int x1, int y1, int width, int height) {
        int midX = x1 + width/2, midY = y1 + height/2;

        drawLine(x1, midY, midX, y1);
        drawLine(x1, midY, midX, y1 + height);
        drawLine(midX, y1, x1 + width, midY);
        drawLine(midX, y1 + height, x1 + width, midY);
    }

    public void drawTriangle(int x1, int y1, int x2, int y2, int x3, int y3) {
        drawLine(x1, y1, x2, y2);
        drawLine(x1, y1, x3, y3);
        drawLine(x2, y2, x3, y3);
    }

    public void drawIsoscelesTriangle(int x1, int y1, int width, int height) {
        drawTriangle(
                x1, y1 + height,
                x1 + width, y1 + height,
                x1 + width/2, y1
        );
    }

    @Deprecated
    public void drawCircleRoot(int xc, int yc, int radius) {
        for (int x = xc - radius; x <= xc + radius; x++) {
            int yDiff = (int) Math.round(Math.sqrt(radius * radius - (x - xc) * (x - xc)));
            setPixel(x,yc + yDiff);
            setPixel(x, yc - yDiff);
        }
    }

    public void drawCircle(int xc, int yc, int radius) {
        for (double t = 0;  t < 360; t += 0.25) {
            int x = xc + (int) (radius * Math.cos(degToRad(t)));
            int y = yc + (int) (radius * Math.sin(degToRad(t)));

            setPixel(x,y);
        }
    }

    public void drawEllipse(int xc, int yc, int radiusX, int radiusY) {
        for (double t = 0;  t < 360; t += 0.25) {
            int x = xc + (int) (radiusX * Math.cos(degToRad(t)));
            int y = yc + (int) (radiusY * Math.sin(degToRad(t)));

            setPixel(x,y);
        }
    }

    public void drawPolygon(Coordenadas2D[] coordenadas) {
        for(int i = 0; i < coordenadas.length; i++) {
            drawLine(coordenadas[i], coordenadas[(i + 1) % coordenadas.length]);
        }
    }

    public void drawPolygon(Matriz[] coordenadas) {
        Coordenadas2D[] cast = new Coordenadas2D[coordenadas.length];
        for(int i = 0; i < coordenadas.length; i++) {
            cast[i] = new Coordenadas2D(coordenadas[i]);
        }
        drawPolygon(cast);
    }

    public void drawAndFillPolygon(Matriz[] coordenadas, Color fillColor) {
        Color lines = new Color(color.getRGB(), true);
        drawPolygon(coordenadas);
        Coordenadas2D center = getCentroid(coordenadas);
        setColor(fillColor);
        fill(center.getIntX(), center.getIntY());
        setColor(lines);
    }

    public void fill(int x, int y) {
        try {
            Color target = getPixelColor(x, y);
            fill(x, y, target);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void fill(int x, int y, Color target) {
        Queue<Point> points = new LinkedList<>();

        compareAndAddPoints(points, x, y, target);

        while (!points.isEmpty()){
            Point p = points.remove();
            setPixel(p.getX(), p.getY());

            compareAndAddPoints(points, p.getX() - 1, p.getY(), target);
            compareAndAddPoints(points, p.getX() + 1, p.getY(), target);
            compareAndAddPoints(points, p.getX(), p.getY() - 1, target);
            compareAndAddPoints(points, p.getX(), p.getY() + 1, target);
        }
    }

    protected boolean compareColors(Color c1, Color c2) {
        if(c1 == null || c2 == null) return false;
        return c1.equals(c2) && c1.getAlpha() == c2.getAlpha();
    }

    protected void compareAndAddPoints(Queue<Point> points, int x, int y, Color target) {
        try {
            if (x <= boundX1 || y <= boundY1 || x >=  boundX2 || y >= boundY2) {
                return;
            }

            if(compareColors(getPixelColor(x, y), target)) {
                Point newPoint = new Point(x, y);
                for (Point _p: points) {
                    if (_p.equals(newPoint)){
                        return;
                    }
                }
                points.add(newPoint);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public Coordenadas2D getCentroid(Coordenadas2D[] points) {
        double centerX = 0, centerY = 0;
        for(int i = 0; i < points.length; i++) {
            centerX += points[i].getX();
            centerY += points[i].getY();
        }
        centerX /= points.length;
        centerY /= points.length;

        return new Coordenadas2D(centerX, centerY);
    }

    public Coordenadas2D getCentroid(Matriz[] points) {
        Coordenadas2D[] arr = new Coordenadas2D[points.length];
        for(int i = 0; i < points.length; i++) {
            arr[i] = new Coordenadas2D(points[i]);
        }
        return getCentroid(arr);
    }

    public void setBounds(int x1, int y1, int x2, int y2) {
        this.boundX1 = x1;
        this.boundY1 = y1;
        this.boundX2 = x2;
        this.boundY2 = y2;
    }

    protected byte getPointBits(int x, int y) {
        byte res = 0;

        res |= (y > boundY2)? 0b0001 : 0;
        res |= (y < boundY1)? 0b0010 : 0;
        res |= (x > boundX2)? 0b0100 : 0;
        res |= (x < boundX1)? 0b1000 : 0;

        return res;
    }

    protected int[] findCropLimit(int x1, int y1, byte crop, double pendiente) {
        int[] res = {0, 0};

        if ((crop & 0b1) != 0) {
            res[0] = (int) (x1 + (double) (boundY2 - y1) / pendiente);
            res[1] = boundY2;
        }
        if((crop & 0b10) != 0) {
            res[0] = (int) (x1 + (double) (boundY1 - y1) / pendiente);
            res[1] = boundY1;
        }
        if((crop & 0b100) != 0) {
            res[1] = (int) (y1 + (double) (boundX2 - x1) * pendiente);
            res[0] = boundX2;
        }
        if((crop & 0b1000) != 0) {
            res[1] = (int) (y1 + (double) (boundX1 - x1) * pendiente);
            res[0] = boundX1;
        }

        return res;
    }

    public void drawLineCropped(int x1, int y1, int x2, int y2) {
        double pendiente = ((double)(y2 - y1)) / (double) (x2 - x1);
        int[] newP1 = { x1, y1 }, newP2 = { x2, y2 };
        byte p1, p2;

        do {

            p1 = getPointBits(newP1[0], newP1[1]);
            p2 = getPointBits(newP2[0], newP2[1]);

            // Trivialmente invisible
            if((p1 & p2) != 0) {
                return;
            }

            // Trivialmente visible
            if(p1 == 0 && p2 == 0) {
                drawLine(newP1[0], newP1[1], newP2[0], newP2[1]);
                return;
            }

            // Otros casos
            if (p1 != 0) {
                newP1 = findCropLimit(x1, y1, p1, pendiente);
            }
            if(p2 != 0) {
                newP2 = findCropLimit(x1, y1, p2, pendiente);
            }
        } while ((p1 & p2) == 0);
    }

}
