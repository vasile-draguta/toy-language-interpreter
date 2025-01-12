package View.GUI;

import Controller.Controller;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainWindow extends Application {
    private static Controller controller;

    public static void setController(Controller controller) {
        MainWindow.controller = controller;
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader mainWindowLoader = new FXMLLoader();

        mainWindowLoader.setLocation(getClass().getResource("/GUI/MainWindow.fxml"));
        mainWindowLoader.setControllerFactory(c -> new MainWindowController(controller));

        Parent mainWindowRoot = mainWindowLoader.load();
        MainWindowController mainWindowController = mainWindowLoader.getController();

        stage.setTitle("Interpreter");
        stage.setScene(new Scene(mainWindowRoot));
        stage.show();

        Stage secondaryStage = new Stage();
        FXMLLoader setProgramLoader = new FXMLLoader();

        setProgramLoader.setControllerFactory(c -> new SelectProgramController(controller, mainWindowController));
        setProgramLoader.setLocation(getClass().getResource("/GUI/SelectProgramWindow.fxml"));

        Parent setProgramRoot = setProgramLoader.load();
        SelectProgramController setProgramController = setProgramLoader.getController();

        secondaryStage.setTitle("Select program");
        secondaryStage.setScene(new Scene(setProgramRoot));

        stage.setOnCloseRequest(event -> {
            Platform.exit();
            System.exit(0);
        });

        secondaryStage.setOnCloseRequest(event -> {
            Platform.exit();
            System.exit(0);
        });

        secondaryStage.show();
        secondaryStage.requestFocus();
    }

    public static void run(String[] args) {
        launch(args);
    }
}