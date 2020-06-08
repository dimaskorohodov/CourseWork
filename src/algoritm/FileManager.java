package algoritm;

import java.io.*;

public class FileManager {


    public void readFile() {

    }


    public DataMaodel getInfo(File file) throws IOException {

        int a, b;
        double r;
        double feromons;
        int iterations;
        int amount;
        int fuel;
        Double[][] feromonsMatrix;

        Double[] values;
        Double[][] matrix;

        FileReader fileReader = new FileReader(file);

        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String line = bufferedReader.readLine();

        String[] meanings = line.split(" ");
        a = Integer.valueOf(meanings[0]);
        b = Integer.valueOf(meanings[1]);
        r = Double.valueOf(meanings[2]);
        feromons = Double.valueOf(meanings[3]);
        iterations = Integer.valueOf(meanings[4]);
        amount = Integer.valueOf(meanings[5]);
        fuel = Integer.valueOf(meanings[6]);

        System.out.println(a + " " + b + " " + r + " " + feromons + " " + iterations + " " + amount);

        String[] valuesArray = bufferedReader.readLine().split(" ");
        values = new Double[amount];
        for (int i = 0; i < amount; i++) {
            values[i] = Double.valueOf(valuesArray[i]);
            System.out.println(values[i]);
        }
        matrix = new Double[amount][amount];

        for (int i = 0; i < amount; i++) {
            String[] matrixArray = bufferedReader.readLine().split(" ");
            for (int j = 0; j < amount; j++) {

                matrix[i][j] = Double.valueOf(matrixArray[j]);
            }
        }

        feromonsMatrix = new Double[amount][amount];

        for (int i = 0; i < amount; i++) {
            for (int j = 0; j < amount; j++) {
                feromonsMatrix[i][j] = Double.valueOf(feromons);
            }
        }
        return new DataMaodel(a,b,r,matrix,feromonsMatrix, values,fuel,iterations,amount);
    }


    void printMatrix(Double[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}
