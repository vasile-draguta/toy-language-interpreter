package Model.States.Output;

import Model.Utils.MyIList;

import java.util.List;

public interface IOutput {
    List<String> getOutputAsList();
    void addOutput(String output);
    void setOutput(String output);
    String toString();
    void clear();
}
