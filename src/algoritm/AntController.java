package algoritm;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class AntController {
    DataMaodel dataMaodel;


    public AntController(DataMaodel dataMaodel) {
        this.dataMaodel = dataMaodel;
        foundLmin();
    }

    Double[] p(int currentIndex, int[] possibleNodes){
        Double[] functionMeanings = new Double[possibleNodes.length];

        Double sum = 0.0;

        for (int i = 0; i < possibleNodes.length; i++) {
            sum = sum += Math.pow(dataMaodel.feromons[currentIndex][i], 1) * Math.pow((dataMaodel.meanings[possibleNodes[i]] / dataMaodel.matrix[currentIndex][possibleNodes[i]]), 1);
        }
        Double curr = 0.0;

        for (int i = 0; i < possibleNodes.length; i++) {
            curr = (Math.pow(dataMaodel.feromons[currentIndex][possibleNodes[i]], 1) * Math.pow((dataMaodel.meanings[possibleNodes[i]] / dataMaodel.matrix[currentIndex][possibleNodes[i]]), 1));
            functionMeanings[i] = curr / sum;
            System.out.println("V in the loop:" + curr + "Its p-value: " + curr / sum);

        }

        System.out.println("Sum is " + sum);
        return functionMeanings;
    }

    int getNextV(Double[] functionMeanings) {
        Random random = new Random();

        for (int i = 1; i < functionMeanings.length; i++) {
            functionMeanings[i] = functionMeanings[i] + functionMeanings[i - 1];
            System.out.println(functionMeanings[i]);
        }
        double randMeaning = Math.random();
        System.out.println("Random " + randMeaning);
        for (int i = 1; i < functionMeanings.length; i++) {
            if (randMeaning < functionMeanings[i] && randMeaning > functionMeanings[i - 1]) {
                return i;
            }
        }
        return 0;
    }

    double getPathCost(Double[][] matrix, List<Integer> path) {
        double sum =0;

        for (int i = 0; i < path.size() - 1; i++) {
            sum = sum + matrix[path.get(i)][path.get(i + 1)];
        }
        return sum;
    }

    void printPath(List<Integer> path) {

        for (Integer v : path) {
            System.out.print(v+"-");
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
        for (int i = 0; i < dataMaodel.amount; i++) {
            if (dataMaodel.matrix[(int) current][i] != 0 && dataMaodel.matrix[(int) current][i] != 99) {
                close.add((double) i);
            }
        }
        return close;
    }

    public void calculate(){
        List<List<Integer>> finalPathes = new ArrayList<>();

        for (int iter = 0; iter < dataMaodel.iterations; iter++) {


            boolean finish = false;
            List<Integer> addedNodes = new ArrayList<>();
            List<Integer> path = new ArrayList<>();
            int currentNode = (int) findMaxNodeIndex(dataMaodel.meanings);
            path.add(currentNode);


            while (!finish) {
                addedNodes.add(currentNode);

                List<Double> close = findClose(currentNode);

                Iterator<Double> jIterator = close.iterator();

                while (jIterator.hasNext()) {
                    double currentToCheck = jIterator.next();
                    if (addedNodes.contains((int) currentToCheck)) {
                        System.out.println("Was removed: " + currentToCheck);
                        jIterator.remove();
                    }
                }


                System.out.println(close.size());

                int[] closeArray = new int[close.size()];

                for (int i = 0; i < close.size(); i++) {
                    closeArray[i] = (int) Math.round(close.get(i));
                }

                if (closeArray.length > 0) {
                    int nextV = closeArray[getNextV(p(currentNode, closeArray))];
                    List<Integer> listToCheck = new ArrayList<>(path);
                    listToCheck.add(nextV);

                    path.add(nextV);
                    currentNode = nextV;



                    if (getPathCost(dataMaodel.matrix, listToCheck) > dataMaodel.fuel) {

                        System.out.println("Fuel was finished");
                        path.remove(path.indexOf(nextV));


                        finish = true;
                    }

                    System.out.println("Next V is: " + nextV);
                }
                else {
                    finish = true;
                }

            }

            System.out.println("We get path:");
            System.out.println("End of the iteration!!!!");

            finalPathes.add(path);
            changeFeromon();
            changeFeromonForPathV(path);
        }

        for (List<Integer> finalPath : finalPathes) {
            printPath(finalPath);
            System.out.println();
        }
        getResult(finalPathes);
    }

    void foundLmin(){
        for (int i = 0; i < dataMaodel.amount; i++) {
            Double min = 99.0;
            for (int j = 0; j < dataMaodel.amount; j++) {
                if ( dataMaodel.matrix[i][j] < min &&  dataMaodel.matrix[i][j] != 0 &&  dataMaodel.matrix[i][j] != 99) {
                    min =  dataMaodel.matrix[i][j];
                }
            }
            dataMaodel.lmin += min;
        }

    }

    public void changeFeromonForPathV(List<Integer> path) {

        for (int i = 0; i < path.size()-1; i++) {
            dataMaodel.feromons[path.get(i)][path.get(i + 1)]= dataMaodel.feromons[path.get(i)][path.get(i + 1)]+ dataMaodel.lmin/ dataMaodel.matrix[path.get(i)][path.get(i + 1)];
        }
    }

    public void changeFeromon() {

        for (int i = 0; i < dataMaodel.feromons.length; i++) {
            for (int j = 0; j < dataMaodel.feromons.length; j++) {
                dataMaodel.feromons[i][j] = (1-dataMaodel.r)*dataMaodel.feromons[i][j];
            }
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

    public void getResult(List<List<Integer>> finalPath) {
        double maxValue = 0;
        int maxIndex =0;
        double currentValue =0;
        int iter = 0;
        for (List<Integer> integers : finalPath) {

            for (int i = 0; i < integers.size(); i++) {
                currentValue = currentValue + dataMaodel.meanings[i];
            }
            System.out.println("Current value - "+currentValue);
            if (currentValue > maxValue) {
                maxValue = currentValue;
                maxIndex = iter;
            }
            currentValue = 0;
            iter++;
        }
        System.out.println("Max index: "+maxIndex);
        System.out.println("Most valuable way is: "+finalPath.get(maxIndex)+" its cost: "+maxValue);
    }




}
