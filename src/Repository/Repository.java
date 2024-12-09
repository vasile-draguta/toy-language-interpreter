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
    private final String logFilePath;

    public Repository(String logFilePath) {
        this.repository = new ArrayList<>();
        this.logFilePath = logFilePath;
    }

    @Override
    public void addProgramState(ProgState state) {
        repository.add(state);
    }

    @Override
    public List<ProgState> getProgramList() throws RepositoryException {
        return repository;
    }

    @Override
    public void setProgramList(List<ProgState> programList) {
        repository = programList;
    }

    @Override
    public void logProgramState(ProgState programState) {
        if(logFilePath != null) {
            PrintWriter logFile;
            try {
                logFile = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, true)));
            } catch (IOException e) {
                throw new RepositoryException("Error when logging the program state");
            }

            logFile.println(programState);
            logFile.close();
        }
    }

    @Override
    public void clear() {
        repository.clear();
    }

}
