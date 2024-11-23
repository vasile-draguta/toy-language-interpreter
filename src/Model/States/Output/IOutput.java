package Model.States.Output;

import Model.Utils.MyIList;

public interface IOutput {
    MyIList<String> getOutputAsList();
    void addOutput(String output);
    void setOutput(String output);
    String toString();
    void clear();
}
