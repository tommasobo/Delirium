package launcher;

import control.Control;
import control.ControlImpl;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import view.ViewController;
import view.ViewControllerImpl;

public class Launcher extends Application {

	

    @Override
    public void start(Stage primaryStage) throws Exception {
        
        primaryStage.getIcons().add(new Image("images/gameicon.png"));
        primaryStage.setOnCloseRequest(e -> System.exit(0));
        primaryStage.setTitle("Delirium");
        final ViewController view = new ViewControllerImpl(primaryStage);
        final Control control = new ControlImpl(view);
        control.startGame();
        primaryStage.show();
        
    }
    
    public static void main(String...args) {
        launch();
    }

}
