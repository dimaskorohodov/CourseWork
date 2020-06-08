package algoritm;

public class DataMaodel {

    public int fuel;
    int a;
    int b;
    double r;
    int iterations;
    Double[][] matrix;
    int lmin = 0;
    int amount;

    Double[][] feromons;
    Double[] meanings;


    public DataMaodel(int a, int b, double r, Double[][] matrix, Double[][] feromons, Double[] meanings, int fuel, int iterations,int amount) {
        this.a = a;
        this.b = b;
        this.r = r;
        this.matrix = matrix;
        this.feromons = feromons;
        this.meanings = meanings;
        this.fuel = fuel;
        this.iterations = iterations;
        this.amount = amount;
    }

    public DataMaodel() {

    }
}
