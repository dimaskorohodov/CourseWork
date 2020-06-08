import algoritm.AntController;
import algoritm.DataMaodel;
import algoritm.FileManager;

import java.io.File;
import java.io.IOException;

public class Runner {
    public static void main(String[] args) throws IOException {
        File file = new File("C:\\Users\\User\\Desktop\\antData.txt");

       // DataMaodel dataMaodel = new FileManager().getInfo(file);

        Double[] meanings = new Double[]{4.0, 9.0, 5.0, 6.0, 5.0, 4.0, 7.0};

        AntAlgoritm antAlgoritm = new AntAlgoritm();
        antAlgoritm.initialization();
        DataMaodel dataMaodel = new DataMaodel(1,1,0.05,antAlgoritm.matrix,antAlgoritm.feromons,meanings,25,2,7);

        AntController antController = new AntController(dataMaodel);

        antController.calculate();
    }
}
