package Model.States.Output;

import Model.Utils.MyIList;
import Model.Utils.MyList;


public class Output implements IOutput {
    private MyIList<String> output;


    public Output() {
        this.output = new MyList<>();
    }

    @Override
    public MyIList<String> getOutputAsList() {
        return this.output;
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
        StringBuilder result = new StringBuilder();
        for (String line : output) {
            result.append(line);
            if(output.indexOf(line) != output.size() - 1) {
                result.append(", ");
            }
        }
        return result.toString();
    }
}
