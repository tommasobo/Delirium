package view;

import java.util.List;

import control.Buttons;
import control.Control;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class ButtonsPane {
    
    private final VBox box;
    private final Control listener;
    
    public ButtonsPane(final Control listener) {
        this.box = new VBox();
        this.listener = listener;
        
    }
    
    public VBox getButtonPane() {
        
        box.setAlignment(Pos.CENTER);
        final List<Buttons> buttons = this.listener.getButtons();
        for (final Buttons b : buttons) {          
            final Button but = new Button(b.name());
            but.setId("button");
            but.setOnAction(e -> this.listener.notifyEvent(b.getEvent()));
            box.getChildren().add(but);
        }
        
        return box;
    }
}
