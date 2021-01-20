package timer;

import animation.Polygon;

import java.util.List;

public interface PaintListener {
    void drawPolygons(List<Polygon> polygon, int order);
    void finished(Object sender);
}
