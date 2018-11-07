package trainset;

import java.util.ArrayList;

public class TrainSet {

    public final int INPUT_SIZE;
    public final int OUTPUT_SIZE;

    private ArrayList<double[][]> data = new ArrayList<>();

    public TrainSet(int INPUT_SIZE, int OUTPUT_SIZE) {
        this.INPUT_SIZE = INPUT_SIZE;
        this.OUTPUT_SIZE = OUTPUT_SIZE;
    }
}
