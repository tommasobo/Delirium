package view;

import java.util.List;

import control.Buttons;
import control.Control;
import javafx.geometry.Dimension2D;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
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
        
        final BorderPane border = new BorderPane();
        border.setPrefSize(super.getDimension().getWidth(), super.getDimension().getHeight());
        border.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        final HBox top = new HBox();
        top.setAlignment(Pos.CENTER);
        final Text  txt = new Text("DELIRIUM");
        txt.setId("title");
        top.getChildren().add(txt);
        border.setCenter(new ButtonsPane(super.getListener()).getButtonPane());
        border.setTop(top);
        
        super.getRoot().getChildren().add(border);
    }

}
