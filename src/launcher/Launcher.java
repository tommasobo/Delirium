package launcher;

import control.Control;
import control.ControlImpl;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import view.ViewControllerImpl;

public class Launcher extends Application {

	

    @Override
    public void start(Stage primaryStage) throws Exception {
        
        //primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.getIcons().add(new Image("gameicon.png"));
        primaryStage.setOnCloseRequest(e -> System.exit(0));
        primaryStage.setTitle("Delirium");
        ViewControllerImpl.setStage(primaryStage);
        Control control = new ControlImpl();
        control.startGame();
        primaryStage.show();
        
    }
    
    public static void main(String...args) {
        launch();
    }

}
