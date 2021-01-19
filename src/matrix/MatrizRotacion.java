package matrix;

public class MatrizRotacion extends MatrizIdentidad {

    public MatrizRotacion(double grados) {
        this(grados, false);
    }

    public MatrizRotacion(double grados, boolean useDeg) {
        super(3);
        double theta = grados;
        if(useDeg) {
            theta *= Math.PI / 180.0;
        }

        set(0, 0, Math.cos(theta));
        set(0, 1, -Math.sin(theta));
        set(1, 0, Math.sin(theta));
        set(1, 1, Math.cos(theta));
    }

}
