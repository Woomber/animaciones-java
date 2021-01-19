package matrix;

public class MatrizEscalado extends MatrizIdentidad {

    public MatrizEscalado(double factor) {
        super(3);
        set(0, 0, factor);
        set(1, 1, factor);
    }
}
