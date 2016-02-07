package view;

import javafx.application.Application;
import javafx.stage.Stage;

public class MainStageGenerator extends Application {
    
    private Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Delirium");
        this.stage = primaryStage;
    }
    
    public Stage getStage() {
        return this.stage;
    }

}
