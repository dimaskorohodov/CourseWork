import javax.swing.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class AntAlgoritm {
    Double[][] matrix = new Double[7][7];
    Double[][] feromons = new Double[7][7];
    int[] meanings = new int[]{4, 9, 5, 6, 5, 4, 7};


    int a =1;
    int b =1;

    double r =0.05;
    int lmin =0;


    void initialization() {
        matrix[0][0] = 0.0;
        matrix[0][1] = 5.0;
        matrix[0][2] = 99.0;
        matrix[0][3] = 5.0;
        matrix[0][4] = 15.0;
        matrix[0][5] = 99.0;
        matrix[0][6] = 99.0;

        matrix[1][0] = 5.0;
        matrix[1][1] = 0.0;
        matrix[1][2] = 10.0;
        matrix[1][3] = 11.0;
        matrix[1][4] = 99.0;
        matrix[1][5] = 99.0;
        matrix[1][6] = 99.0;

        matrix[2][0] = 99.0;
        matrix[2][1] = 10.0;
        matrix[2][2] = 0.0;
        matrix[2][3] = 12.0;
        matrix[2][4] = 99.0;
        matrix[2][5] = 99.0;
        matrix[2][6] = 99.0;

        matrix[3][0] = 5.0;
        matrix[3][1] = 11.0;
        matrix[3][2] = 12.0;
        matrix[3][3] = 0.0;
        matrix[3][4] = 11.0;
        matrix[3][5] = 13.0;
        matrix[3][6] = 99.0;

        matrix[4][0] = 15.0;
        matrix[4][1] = 99.0;
        matrix[4][2] = 99.0;
        matrix[4][3] = 11.0;
        matrix[4][4] = 0.0;
        matrix[4][5] = 99.0;
        matrix[4][6] = 8.0;

        matrix[5][0] = 99.0;
        matrix[5][1] = 99.0;
        matrix[5][2] = 99.0;
        matrix[5][3] = 13.0;
        matrix[5][4] = 99.0;
        matrix[5][5] = 0.0;
        matrix[5][6] = 5.0;

        matrix[6][0] = 99.0;
        matrix[6][1] = 99.0;
        matrix[6][2] = 99.0;
        matrix[6][3] = 99.0;
        matrix[6][4] = 8.0;
        matrix[6][5] = 5.0;
        matrix[6][6] = 0.0;


        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                feromons[i][j] = 0.5;
            }
        }




    }

    double findMaxNodeIndex(Double w[]) {
        double max = 0;
        double index = 0;
        for (int i = 0; i < w.length; i++) {
            if (w[i] > max) {
                max = w[i];
                index = i;
            }
        }
        return index;
    }


    public List<Double> findClose(double current) {

        List<Double> close = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            if (matrix[(int) current][i] != 0 && matrix[(int) current][i] != 99) {
                close.add((double) i);
            }
        }
        return close;
    }


    public void iterationsAlgo(int iterations) {

        List<int[]> paths = new ArrayList<>();

        for (int i = 0; i < iterations; i++) {


        }
    }

    public void iterate(Double[] w) {
        List<int[]> paths = new ArrayList<>();
        int T = 25;

        double currentNode = findMaxNodeIndex(w);

        List<Integer> addedNodes = new ArrayList<>();

        addedNodes.add((int) currentNode);

        Boolean finish = false;

        System.out.println("First Iteration!!!!!!!!!!!!!!!!!!!!!!!!!!");

        while (!finish) {

            System.out.println("Current node:" + currentNode);

            List<Double> J = findClose(currentNode);

            Iterator<Double> jIterator = J.iterator();

            while (jIterator.hasNext()) {
                double currentToCheck = jIterator.next();
                if (addedNodes.contains(currentToCheck)) {
                    J.remove(currentToCheck);
                }
            }
            int[] closeArray = new int[J.size()];

            for (int i = 0; i < J.size(); i++) {
                closeArray[i] = (int) Math.round(J.get(i));

            }
            Double[] functionMeanings = new Double[closeArray.length];


            for (int i = 0; i < J.size(); i++) {
                functionMeanings[i] = P(1, 1, 1, closeArray[i], closeArray);
            }


            int nextV = getNextV(functionMeanings);
            addedNodes.add(nextV);


            //int nextV =
            int way = 0;

            for (int i = 0; i < addedNodes.size() - 1; i++) {
                way = (int) (way + matrix[addedNodes.get(i)][addedNodes.get(i + 1)]);
            }

            if (way >= T) {
                addedNodes.remove(addedNodes.size());
                finish = true;
            }

            currentNode = nextV;


        }


    }

    void foundLmin(){
        for (int i = 0; i < 7; i++) {
            Double min = 99.0;
            for (int j = 0; j < 7; j++) {
                if (matrix[i][j] < min && matrix[i][j] != 0 && matrix[i][j] != 99) {
                    min = matrix[i][j];
                }
            }
            lmin += min;
        }

    }


    public void found(Double[] w, double a, double b, double start_feromon, double iterations, double ro) {
        initialization();
//        int lmin = 0;
//        for (int i = 0; i < 7; i++) {
//            Double min = 99.0;
//            for (int j = 0; j < 7; j++) {
//                if (matrix[i][j] < min && matrix[i][j] != 0 && matrix[i][j] != 99) {
//                    min = matrix[i][j];
//                }
//            }
//            lmin += min;
//        }


// inintializate feromons;
        for (int i = 0; i < 7; i++) {
            for (int j = 1; j < 7; j++) {
                feromons[i][j] = start_feromon;
            }
        }
        System.out.println(lmin);


        for (int i = 0; i < iterations; i++) {
            double[] ants_V = new double[7];
            double[] ants_curr = new double[7];
            //  List<Integer> ants_curr = new ArrayList<>();

            double start_node = findMaxNodeIndex(w);


            //int[] J = new int[7]; /// Смежные вершины

            // ants_curr[]
            ants_curr[i] = start_node;
            ants_V[i] = start_node;

            Boolean finish = false;


            double currNode = findMaxNodeIndex(w);
            List<Double> addedNodes = new ArrayList<>();

            addedNodes.add(currNode);

            System.out.println("Curr node is:" + currNode);
            while (finish != true) {

                List<Double> J = findClose(currNode);

                Iterator<Double> jIterator = J.iterator();

                while (jIterator.hasNext()) {
                    double currentToCheck = jIterator.next();
                    if (addedNodes.contains(currentToCheck)) {
                        J.remove(currentToCheck);
                    }
                }
                // Random random = new Random();


                J.forEach(System.out::println);


                finish = true;
            }
        }
    }

    Double P(int a, int b, int currentIndex, int nextIndex, int[] possibleNodes) {

        // System.out.println("PROVERKA");

        System.out.println(Math.pow((meanings[possibleNodes[1]] / matrix[currentIndex][possibleNodes[1]]), b));


        // System.out.println("PROVERKA");

        // System.out.println("FOIRST:" + Math.pow((meanings[possibleNodes[1]] / matrix[currentIndex][possibleNodes[1]]), b));
        Double current = (Math.pow(feromons[currentIndex][nextIndex], a) * Math.pow((meanings[nextIndex] / matrix[currentIndex][nextIndex]), b));


        Double sum = 0.0;


        //System.out.println("CURRENT: " + meanings[1] / matrix[currentIndex][possibleNodes[1]]);

        for (int i = 0; i < possibleNodes.length; i++) {
            System.out.println("Checking: " + Math.pow(feromons[currentIndex][possibleNodes[i]], a) * Math.pow((meanings[possibleNodes[i]] / matrix[currentIndex][possibleNodes[i]]), b));
            sum = sum += Math.pow(feromons[currentIndex][i], a) * Math.pow((meanings[possibleNodes[i]] / matrix[currentIndex][possibleNodes[i]]), b);
        }

        //System.out.println("SUM: " + sum);

        System.out.println("The current p: " + current / sum);

        return current / sum;
    }


    Double[] testingP(int currentIndex,  int[] possibleNodes) {
        Double[] functionMeanings = new Double[possibleNodes.length];

        //Double current = (Math.pow(feromons[currentIndex][nextIndex], 1) * Math.pow((meanings[nextIndex] / matrix[currentIndex][nextIndex]), 1));

       // System.out.println("Current V- " + nextIndex + " Its value- " + current);

        Double sum = 0.0;

        for (int i = 0; i < possibleNodes.length; i++) {
            sum = sum += Math.pow(feromons[currentIndex][i], 1) * Math.pow((meanings[possibleNodes[i]] / matrix[currentIndex][possibleNodes[i]]), 1);
        }
        Double curr = 0.0;

        for (int i = 0; i < possibleNodes.length; i++) {
            //  System.out.println();
            curr = (Math.pow(feromons[currentIndex][possibleNodes[i]], 1) * Math.pow((meanings[possibleNodes[i]] / matrix[currentIndex][possibleNodes[i]]), 1));
            functionMeanings[i] = curr / sum;
            System.out.println("V in the loop:" + curr + "Its p-value: " + curr / sum);

        }

        System.out.println("Sum is " + sum);

       // System.out.println("P is " + current / sum);

        return functionMeanings;

    }

    int getNextV(Double[] functionMeanings) {
        Random random = new Random();

        for (int i = 1; i < functionMeanings.length; i++) {
            functionMeanings[i] = functionMeanings[i] + functionMeanings[i - 1];
            System.out.println(functionMeanings[i]);
        }

        // System.out.println(Math.random());
        double randMeaning = Math.random();
        System.out.println("Random " + randMeaning);
        for (int i = 1; i < functionMeanings.length; i++) {
            if (randMeaning < functionMeanings[i] && randMeaning > functionMeanings[i - 1]) {
                return i;
            }
        }
        return 0;
    }

    public void changeFeromon() {

        for (int i = 0; i < feromons.length; i++) {
            for (int j = 0; j < feromons.length; j++) {
                feromons[i][j] = (1-r)*feromons[i][j];
            }
        }


    }

    public void changeFeromonForPathV(List<Integer> path) {

        for (int i = 0; i < path.size()-1; i++) {
            feromons[path.get(i)][path.get(i + 1)]=feromons[path.get(i)][path.get(i + 1)]+lmin/matrix[path.get(i)][path.get(i + 1)];
        }
    }


    public void outputMatrix(Double[][] matrix) {


        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                System.out.print(matrix[i][j]+" ");
            }
            System.out.println();
        }
    }
}
