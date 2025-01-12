package View.GUI;

import Controller.Controller;
import Model.Statement.IStatement;
import Model.States.FileTable.IFileTable;
import Model.States.HeapTable.IHeapTable;
import Model.States.Output.IOutput;
import Model.States.ProgState;
import Model.Values.IValue;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.Pair;

import java.util.List;
import java.util.NoSuchElementException;

public class MainWindowController {
    Controller controller;
    IHeapTable heap;
    IOutput output;
    IFileTable fileTable;

    public MainWindowController(Controller controller) {
        this.controller = controller;
    }

    @FXML
    private Label programStatesLabel;

    @FXML
    private ListView<Integer> programStatesListView;

    @FXML
    private ListView<String> executionStackListView;

    @FXML
    private ListView<String> fileTableListView;

    @FXML
    private ListView<String> outListView;

    @FXML
    private TableView<Pair<Integer, IValue>> heapTableTableView;

    @FXML
    private TableColumn<Pair<Integer, IValue>, Integer> heapAddressesColumn;

    @FXML
    private TableColumn<Pair<Integer, IValue>, String> heapValuesColumn;

    @FXML
    private TableView<Pair<String, IValue>> symbolTableTableView;

    @FXML
    private TableColumn<Pair<String, IValue>, String> symbolNameColumn;

    @FXML
    private TableColumn<Pair<String, IValue>, String> symbolValueColumn;

    @FXML
    private Button runButton;

    @FXML
    private Button oneStepButton;

    public void refresh() {
        Integer selectedProgramId = programStatesListView.getSelectionModel().getSelectedItem();

        programStatesLabel.setText("Program states: " + controller.getProgStates().size());

        List<Integer> currentIds = controller.getProgStates().stream().map(ProgState::getId).toList();
        programStatesListView.getItems().setAll(currentIds);

        if (!controller.getProgStates().isEmpty()) {
            heap = controller.getProgStates().get(0).getHeapTable();
            output = controller.getProgStates().get(0).getOutput();
            fileTable = controller.getProgStates().get(0).getFileTable();
        }

        if (heap != null) {
            heapTableTableView.getItems().setAll(
                    heap.toMap().entrySet().stream()
                            .map(entry -> new Pair<>(entry.getKey(), entry.getValue()))
                            .toList()
            );
        }

        if (output != null) {
            outListView.getItems().setAll(
                    output.getOutputAsList().stream()
                            .map(Object::toString)
                            .toList()
            );
        }

        if (fileTable != null) {
            fileTableListView.getItems().setAll(
                    fileTable.getFileList().stream()
                            .map(Object::toString)
                            .toList()
            );
        }

        ProgState current = controller.getProgStates().stream()
                .filter(x -> x.getId().equals(selectedProgramId))
                .findAny()
                .orElse(null);

        if (current != null) {
            symbolTableTableView.getItems().setAll(
                    current.getSymTable().toMap().entrySet().stream()
                            .map(entry -> new Pair<>(entry.getKey(), entry.getValue()))
                            .toList()
            );

            executionStackListView.getItems().setAll(
                    current.getExecutionStack().getStatementsString().stream()
                            .toList()
            );
        }

        programStatesListView.getSelectionModel().select(selectedProgramId);

        programStatesListView.refresh();
        heapTableTableView.refresh();
        outListView.refresh();
        fileTableListView.refresh();
        symbolTableTableView.refresh();
        executionStackListView.refresh();
    }


    @FXML
    public void initialize() {
        heapAddressesColumn.setCellValueFactory(p -> new SimpleIntegerProperty(p.getValue().getKey()).asObject());
        heapValuesColumn.setCellValueFactory(p -> new SimpleObjectProperty<>(p.getValue().getValue().toString()));
        symbolNameColumn.setCellValueFactory(p -> new SimpleObjectProperty<>(p.getValue().getKey()));
        symbolValueColumn.setCellValueFactory(p -> new SimpleObjectProperty<>(p.getValue().getValue().toString()));
        refresh();

        oneStepButton.setOnAction(actionEvent -> {
            try {
                controller.executeOneStep();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
                alert.show();
            }
            refresh();
        });

        runButton.setOnAction(actionEvent -> {
            try {
                controller.allSteps();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
                alert.show();
            }
            refresh();
        });
        programStatesListView.setOnMouseClicked(x -> refresh());
    }
}
