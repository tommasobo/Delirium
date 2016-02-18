package view;

import java.util.List;

import control.Buttons;
import control.Control;
import javafx.geometry.Dimension2D;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
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
        //border.setBackground(new Background(new BackgroundImage(new Image("window.jpg", super.getDimension().getWidth(), super.getDimension().getHeight(), false, true), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, new BackgroundSize(super.getDimension().getWidth(), super.getDimension().getHeight(), false, false, false, false))));
        
        final HBox top = new HBox();
        top.setAlignment(Pos.CENTER);
        final Text  txt = new Text("DELIRIUM");
        txt.setId("fancytext");
        top.getChildren().add(txt);
        final VBox box = new VBox();
        box.setAlignment(Pos.CENTER);
        final List<Buttons> buttons = super.getListener().getButtons();
        for (final Buttons b : buttons) {          
            final Button but = new Button(b.name());
            but.setId("cool1");
            but.setOnAction(e -> super.getListener().notifyEvent(b.getEvent()));
            box.getChildren().add(but);
        }
        border.setCenter(box);
        border.setTop(top);
        
        super.getRoot().getChildren().add(border);
    }

}
