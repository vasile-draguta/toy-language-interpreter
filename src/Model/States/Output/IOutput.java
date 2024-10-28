package Model.States.Output;

import Model.Utils.MyIList;

public interface IOutput {
    public MyIList<String> getOutputAsList();
    public void addOutput(String output);
    public void setOutput(String output);
    public String toString();
}
