package Model.States.Output;

import Model.Utils.MyIList;
import Model.Utils.MyList;

public class Output implements IOutput {
    private MyIList<String> output;


    public Output() {
        this.output = new MyList<String>();
    }

    @Override
    public MyIList<String> getOutputAsList() {
        return this.output;
    }

    @Override
    public void addOutput(String output) {
        this.output.add(output);
    }

    @Override
    public void setOutput(String output) {
        this.output = new MyList<String>();
        this.output.add(output);
    }

    public void clear() {
        output = new MyList<String>();
    }

    @Override
    public String toString() {
        return this.output.toString();
    }
}
