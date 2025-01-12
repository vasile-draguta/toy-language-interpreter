package Repository;

import Exceptions.RepositoryException;
import Model.States.ProgState;

import java.util.List;

public interface IRepository {
    void addProgramState(ProgState state);
    List<ProgState> getProgramList() throws RepositoryException;
    void setProgramList(List<ProgState> programList);
    void logProgramState(ProgState programState);

    void clear();
}
