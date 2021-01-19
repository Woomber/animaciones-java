package matrix;

import java.util.Arrays;

public class Matriz {

    protected double[][] datos;
    protected int width;
    protected int height;

    public static final int minSize = 1;
    public static final int maxSize = Integer.MAX_VALUE;

    public Matriz(int width, int height) {
        if(width < minSize || width > maxSize || height < minSize || height > maxSize) {
            throw new ArrayIndexOutOfBoundsException();
        }
        this.width = width;
        this.height = height;
        this.datos = new double[width][height];
    }

    public Matriz(Matriz copia) {
        this.datos = copia.datos.clone();
        this.width = copia.width;
        this.height = copia.height;
    }

    public double get(int width, int height) {
        return datos[width][height];
    }

    public void set(int width, int height, double val) {
        datos[width][height] = val;
    }

    public void fill(double val) {
        for(int i = 0; i < width; i++) {
            for(int j = 0; j < height; j++) {
                datos[i][j] = val;
            }
        }
    }

    public void add(int width, int height, double val) {
        datos[width][height] += val;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

     public Matriz add(Matriz m2) {
        if(width != m2.width && height != m2.height) {
            throw new ArithmeticException();
        }

        Matriz resultado = new Matriz(this);
        for(int i = 0; i < width; i++) {
            for(int j = 0; j < height; j++) {
                resultado.add(i, j, m2.get(i, j));
            }
        }

        return resultado;
     }

     public Matriz product(Matriz m2) {
         if(this.height != m2.width) {
             throw new ArithmeticException();
         }

         Matriz resultado = new Matriz(width, height);
         resultado.fill(0);
         for(int i = 0; i < width; i++) {
             for(int j = 0; j < m2.height; j++) {
                 for(int k = 0; k < height; k++) {
                     resultado.add(i, j, datos[i][k] * m2.datos[k][j]);
                 }
             }
         }

         return resultado;
     }

    @Override
    public String toString() {
        String data = "";
        for(int i = 0; i < width; i++) {
            data += "{ ";
            for(int j = 0; j < height - 1; j++) {
                data += datos[i][j] + ", ";
            }
            data += datos[i][height - 1] + "}\n";
        }
        return  getClass().getName() + "{\n" + data + '}';
    }
}
