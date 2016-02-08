package launcher;

import control.Control;
import control.ControlImpl;
import javafx.application.Application;
import javafx.stage.Stage;
import view.ViewControllerImpl;

public class Launcher extends Application {

	

    @Override
    public void start(Stage primaryStage) throws Exception {
        
        primaryStage.setTitle("Delirium");
        ViewControllerImpl.setStage(primaryStage);
        Control control = new ControlImpl();
        control.startGame();
        
    }
    
    public static void main(String...args) {
        launch();
    }

}
