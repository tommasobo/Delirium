package view.screens;

import control.Control;
import javafx.geometry.Dimension2D;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import view.AudioManager;
import view.Music;
import view.Visitor;

public class StaticView extends AbstractGenericView {

    public StaticView(final Stage stage, final Control listener, final Dimension2D dimension) {
        super(stage, listener, dimension);
    }

    @Override
    protected void completeInitialization() {

        if (AudioManager.getAudioManager().isAudioAvailable()) {
            AudioManager.getAudioManager().playTheme(Music.MENUTHEME);
        }
        final BorderPane border = new BorderPane();
        border.setPrefSize(super.getSceneDimension().getWidth(), super.getSceneDimension().getHeight());
        border.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        final HBox top = new HBox();
        top.setAlignment(Pos.CENTER);
        final Text txt = new Text("DELIRIUM");
        txt.setId("title");
        top.getChildren().add(txt);
        border.setCenter(new ButtonsPane(super.getListener()).getButtonPane(super.getSceneDimension().getWidth()));
        border.setTop(top);
        super.getRoot().getChildren().add(border);

    }

    public void accept(final Visitor visitor) {
        visitor.visit(this);
    }

}
