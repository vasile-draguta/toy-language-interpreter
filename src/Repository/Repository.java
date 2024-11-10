package Repository;

import Exceptions.RepositoryException;
import Model.States.ProgState;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;


public class Repository implements IRepository {
    private List<ProgState> repository;
    private String logFilePath;

    public Repository(String logFilePath) {
        this.repository = new ArrayList<>();
        this.logFilePath = logFilePath;
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

    @Override
    public void clearCompletedPrograms() {
        if(repository.isEmpty()) {
            throw new RepositoryException("No programs to be executed!");
        }

        for(int i = 0; i < repository.size(); i++) {
            if(repository.get(i).getExecutionStack().isEmpty()) {
                repository.remove(repository.get(i));
            }
        }
    }

    @Override
    public void logProgramState(ProgState programState) {
        if(logFilePath != null) {
            PrintWriter logFile = null;
            try {
                logFile = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, true)));
            } catch (IOException e) {
                throw new RepositoryException("Error when logging the program state");
            }

            logFile.println(programState);
            logFile.close();
        }
    }

}
