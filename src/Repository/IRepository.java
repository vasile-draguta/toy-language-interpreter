package Repository;

import Exceptions.RepositoryException;
import Model.States.ProgState;

public interface IRepository {
    public void addProgramState(ProgState state);
    public ProgState getCurrentProgram() throws RepositoryException;
    public void clearCompletedPrograms();
}
