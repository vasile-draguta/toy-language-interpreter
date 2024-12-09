package Controller;

import Exceptions.AppException;
import Model.States.ProgState;
import Repository.IRepository;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class Controller {
    private final IRepository repository;
    private Boolean displayFlag;
    private ExecutorService executor;

    public Controller(IRepository repository, Boolean displayFlag) {
        this.repository = repository;
        this.executor = Executors.newSingleThreadExecutor();
        this.displayFlag = displayFlag;
    }

    private void printState() {
        if (displayFlag) {
            List<ProgState> programList = repository.getProgramList();
            programList.forEach(System.out::println);
        }
    }

    private void removeCompletedPrograms() {
        repository.setProgramList(repository.getProgramList().stream().filter(ProgState::isNotCompleted).collect(Collectors.toList()));
    }


    public void executeOneStep() throws AppException{
        this.removeCompletedPrograms();
        List<Callable<ProgState>> stepList = repository.getProgramList().stream()
                .map(program -> (Callable<ProgState>) (program::oneStep))
                .toList();

        List<ProgState> newPrograms;
        try {
            newPrograms = executor.invokeAll(stepList).stream()
                    .map(future -> {
                        try {
                            return future.get();
                        } catch (InterruptedException | ExecutionException e) {
                            throw new RuntimeException(e);
                        }

                    })
                    .filter(Objects::nonNull)
                    .toList();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        newPrograms.forEach(repository::addProgramState);

        GarbageCollector.garbageCollector(repository.getProgramList());

        if(displayFlag){
            printState();
        }
        repository.getProgramList().forEach(repository::logProgramState);
    }


    public void allSteps() throws AppException{
        executor = Executors.newFixedThreadPool(2);
        while(true){
            this.removeCompletedPrograms();
            if(this.repository.getProgramList().isEmpty()){
                break;
            }
            this.executeOneStep();
        }
        executor.shutdown();
    }

    public void setDisplayFlag(Boolean displayFlag) {
        this.displayFlag = displayFlag;
    }

    public Boolean getDisplayFlag() {
        return displayFlag;
    }

    public void addProgram(ProgState program) {
        repository.addProgramState(program);
    }
}
