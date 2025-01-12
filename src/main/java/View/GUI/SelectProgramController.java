package View.GUI;

import Controller.Controller;
import Hardcoded.Hardcoded;
import Model.Statement.IStatement;
import Model.Utils.Pair;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import java.util.ArrayList;
import java.util.List;

public class SelectProgramController {
    Controller controller;
    MainWindowController mainWindowController;

    @FXML
    private ListView<String> programsListView;

    @FXML
    private Button selectProgramButton;

    public SelectProgramController(Controller controller, MainWindowController mainWindowController) {
        this.controller = controller;
        this.mainWindowController = mainWindowController;
    }

    @FXML
    public void initialize() {
        List<String> hardcodedProgramNames = new ArrayList<>();
        List<IStatement> hardcodedPrograms = new ArrayList<>();
        Integer num = 1;
        for ( Pair<String, IStatement> pair : Hardcoded.getHardcodedPrograms() ) {
            hardcodedProgramNames.add(num + ". " + pair.getKey());
            hardcodedPrograms.add(pair.getValue());
            num++;
        }
        programsListView.setItems(FXCollections.observableArrayList(hardcodedProgramNames));
        selectProgramButton.setOnAction(actionEvent -> {
            try{
                int index = programsListView.getSelectionModel().getSelectedIndex();
                if (index < 0) {
                    System.out.println("No index selected");
                } else if (index >= hardcodedPrograms.size()) {
                    System.out.println("No program at selected index");
                } else {
                    System.out.println("Selected program " + index);
                    controller.setProgram(hardcodedPrograms.get(index));
                    mainWindowController.refresh();
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });
        mainWindowController.refresh();
    }
}