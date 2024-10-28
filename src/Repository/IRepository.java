package Repository;

import Model.States.ProgState;

public interface IRepository {
    void addProgramState(ProgState state);
    ProgState getCurrentProgram();
}
