package Controller;

import Exceptions.AppException;
import Model.Statement.IStatement;
import Model.States.ExecutionStack.ExecutionStack;
import Model.States.FileTable.FileTable;
import Model.States.HeapTable.HeapTable;
import Model.States.Output.IOutput;
import Model.States.Output.Output;
import Model.States.ProgState;
import Model.States.SymTable.SymTable;
import Model.Utils.MyDictionary;
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
        IOutput output = new Output();
        while(true){
            output = repository.getProgramList().get(0).getOutput();
            this.removeCompletedPrograms();
            if(this.repository.getProgramList().isEmpty()){
                break;
            }
            this.executeOneStep();
        }
        System.out.println(output);
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

    public List<ProgState> getProgStates() {
        return repository.getProgramList();
    }

    public void setProgram(IStatement statement) {
        statement.typeCheck(new MyDictionary<>());

        this.repository.clear();
        addProgram(new ProgState(
                new ExecutionStack(),
                new SymTable(),
                new Output(),
                new FileTable(),
                new HeapTable(),
                statement));

        this.repository.logProgramState(this.repository.getProgramList().get(0));

    }
}

