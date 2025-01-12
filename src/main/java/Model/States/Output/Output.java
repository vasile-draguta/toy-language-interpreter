package Model.States.Output;

import Model.Utils.MyIList;
import Model.Utils.MyList;

import java.util.ArrayList;
import java.util.List;


public class Output implements IOutput {
    private MyIList<String> output;


    public Output() {
        this.output = new MyList<>();
    }

    @Override
    public List<String> getOutputAsList() {
        List<String> list = new ArrayList<>();
        for (String s : output) {
            list.add(s);
        }
        return list;
    }

    @Override
    public void addOutput(String output) {
        this.output.add(output);
    }

    public int getSize() {
        return output.size();
    }

    @Override
    public void setOutput(String output) {
        this.output = new MyList<>();
        this.output.add(output);
    }

    public void clear() {
        output = new MyList<>();
    }

    @Override
    public String toString() {
        return String.join(", ", output);
    }
}
