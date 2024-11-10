package Repository;

import Exceptions.RepositoryException;
import Model.States.ProgState;

import java.io.IOException;

public interface IRepository {
    public void addProgramState(ProgState state);
    public ProgState getCurrentProgram() throws RepositoryException;
    public void clearCompletedPrograms();
    public void logProgramState(ProgState programState);
}
