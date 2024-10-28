package Repository;

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
    public ProgState getCurrentProgram() {
        return repository.getLast();
    }

}
