package Repository;

import Exceptions.RepositoryException;
import Model.States.ProgState;

public interface IRepository {
    void addProgramState(ProgState state);
    ProgState getCurrentProgram() throws RepositoryException;
}
