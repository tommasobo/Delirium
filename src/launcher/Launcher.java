package launcher;

import control.Control;
import control.ControlImpl;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import view.ViewController;
import view.ViewControllerImpl;

/**
 * This class launches the JavaFX application and connects Control and View.
 */
public class Launcher extends Application {

    @Override
    public void start(final Stage primaryStage) {
        
        primaryStage.getIcons().add(new Image("images/gameicon.png"));
        primaryStage.setOnCloseRequest(e -> System.exit(0));
        primaryStage.setTitle("Delirium");
        try {
            final ViewController view = new ViewControllerImpl(primaryStage);
            final Control control = new ControlImpl(view);
            control.startGame();
        } catch (final Exception e) {
            e.printStackTrace();
            this.generateErrorWindow(primaryStage, e.toString());
        }
        
        primaryStage.show();
        
    }
    
    private void generateErrorWindow(final Stage primaryStage, final String error) {
        final Group root = new Group();
        final Scene errorScene = new Scene(root);
        final VBox box = new VBox(new Text("There was a problem in the game, try to start it again."), new Text(error)); 
        box.setAlignment(Pos.CENTER);
        root.getChildren().add(box);
        primaryStage.setScene(errorScene);
    }
    
    /**
     * Main method.
     * @param args Parameters
     */
    public static void main(final String...args) {
        launch();
    }

}
