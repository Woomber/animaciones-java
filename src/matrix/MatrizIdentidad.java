package matrix;

public class MatrizIdentidad extends Matriz {

    public MatrizIdentidad(int size) {
        super(size, size);
        fill(0);
        for(int i = 0; i < size; i++) {
            set(i, i, 1);
        }
    }

    @Override
    public Matriz add(Matriz m2) {
        return super.add(m2);
    }

    @Override
    public Matriz product(Matriz m2) {
        return super.product(m2);
    }
}
