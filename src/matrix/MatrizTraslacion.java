package matrix;

public class MatrizTraslacion extends MatrizIdentidad {

    public MatrizTraslacion(double dx, double dy) {
        super(3);
        set(2, 0, dx);
        set(2, 1, dy);
    }
}
