package view;

import java.util.List;

import control.Buttons;
import control.Control;
import javafx.geometry.Dimension2D;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class StaticView extends AbstractGenericView {

    public StaticView(final Stage stage, final Control listener, final Dimension2D dimension) {
        super(stage, listener, dimension);
    }
    
    @Override
    public void initScene() {
        
        new Scene(super.getRoot(), super.getDimension().getWidth(), super.getDimension().getHeight());
        super.getRoot().getScene().getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        Font.loadFont(this.getClass().getResourceAsStream("Zombie_Holocaust.ttf"),100);
        super.getRoot().getScene().setFill(Color.BLACK);
        
        BorderPane border = new BorderPane();
        HBox g = new HBox();
        g.setAlignment(Pos.CENTER);
        Text  txt = new Text("DELIRIUM");
        txt.setId("fancytext");
        
        g.getChildren().add(txt);
        final List<Buttons> buttons = super.getListener().getButtons();
        final VBox box = new VBox();
        box.setAlignment(Pos.CENTER);
        
        for (Buttons b : buttons) {          
            final Button but = new Button(b.name());
            but.setId("Button");
            but.setOnAction(e -> super.getListener().notifyEvent(b.getEvent()));
            box.getChildren().add(but);
        }
        
        border.setPrefSize(super.getRoot().getScene().getWidth(), super.getRoot().getScene().getHeight());
        border.setCenter(box);
        border.setTop(g);
        
        super.getRoot().getChildren().add(border);
    }

}
