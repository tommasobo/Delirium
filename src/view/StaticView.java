package view;

import java.util.List;

import control.Buttons;
import control.Control;
import javafx.geometry.Dimension2D;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class StaticView extends AbstractGenericView {

    public StaticView(final Stage stage, final Control listener, final Dimension2D dimension) {
        super(stage, listener, dimension);
    }
    
    @Override
    public void initScene() {
        
        new Scene(super.getRoot(), super.getDimension().getWidth(), super.getDimension().getHeight());
        final List<Buttons> buttons = super.getListener().getButtons();
        final VBox box = new VBox();
        
        for (Buttons b : buttons) {          
            final Button but = new Button(b.name());
            but.setOnAction(e -> super.getListener().notifyEvent(b.getEvent()));
            box.getChildren().add(but);
        }
        
        box.setPrefSize(super.getRoot().getScene().getWidth(), super.getRoot().getScene().getHeight());
        box.setAlignment(Pos.CENTER);
        super.getRoot().getChildren().add(box);
    }

}
