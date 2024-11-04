package Repository;

import Exceptions.RepositoryException;
import Model.States.ProgState;

import java.util.ArrayList;
import java.util.List;


public class Repository implements IRepository {
    private List<ProgState> repository;

    public Repository() {
        this.repository = new ArrayList<>();
    }

    @Override
    public void addProgramState(ProgState state) {
        repository.add(state);
    }

    @Override
    public ProgState getCurrentProgram() throws RepositoryException {
        if (repository.isEmpty())
            throw new RepositoryException("Repository is empty");
        return repository.getLast();
    }

}
