package animation;

import matrix.Coordenadas2D;

import java.awt.*;

public class Polygon {

    protected Coordenadas2D[] path;
    protected Color borderColor;
    protected Color fillColor;

    public Polygon(Coordenadas2D[] path) {
        this(path, Color.BLACK, Color.WHITE);
    }

    public Polygon(Coordenadas2D[] path, Color borderColor, Color fillColor) {
        this.path = path;
        this.borderColor = borderColor;
        this.fillColor = fillColor;
    }

    public Polygon(Polygon copia) {
        this.borderColor = new Color(copia.borderColor.getRGB(), true);
        this.fillColor = new Color(copia.borderColor.getRGB(), true);
        this.path = new Coordenadas2D[copia.path.length];
        System.arraycopy(copia.path, 0, this.path, 0, copia.path.length);
    }

    public Coordenadas2D[] getPath() {
        return path;
    }

    public void setPath(Coordenadas2D[] path) {
        this.path = path;
    }

    public Color getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
    }

    public Color getFillColor() {
        return fillColor;
    }

    public void setFillColor(Color fillColor) {
        this.fillColor = fillColor;
    }
}
