package view;

import java.util.List;

import control.Buttons;
import control.Control;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class StaticView extends GenericViewImpl {

    public StaticView(final Stage stage, final Control listener) {
        super(stage, listener);
    }
    
    @Override
    protected void firstDraw() {
        
        final List<Buttons> buttons = super.listener.getButtons();
        final VBox box = new VBox();
        
        for (Buttons b : buttons) {          
            final Button but = new Button(b.name());
            but.setOnAction(e -> super.listener.notifyEvent(b.getEvent()));
            box.getChildren().add(but);
        }
        
        box.setPrefSize(root.getScene().getWidth(), root.getScene().getHeight());
        box.setAlignment(Pos.CENTER);
        super.root.getChildren().add(box);
    }

}
