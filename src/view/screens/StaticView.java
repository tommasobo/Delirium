package view.screens;

import java.util.Map;

import control.Control;
import control.viewcomunication.MenuCategory;
import control.viewcomunication.MenuCategoryEntries;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Dimension2D;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.Paint;
import javafx.scene.paint.Stop;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import view.configs.Entities;
import view.configs.Music;
import view.utilities.AudioManager;
import view.utilities.ResourcesManager;

/**
 * Static implementation of GenericView. Used for menus.
 */
class StaticView extends AbstractGenericView {
    
    private final Pane p1 = new Pane();
    private final Pane p2 = new Pane();
    
    /**
     * StaticView constructor.
     * 
     * @param stage
     *            Main stage of JavaFX application
     * @param listener
     *            Controller listener
     * @param dimension
     *            Scene request dimension
     */
    StaticView(final Stage stage, final Control listener, Dimension2D dimension) {
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
        javafx.scene.paint.LinearGradient gradient = new javafx.scene.paint.LinearGradient(0, 0, 0, 1 , true, CycleMethod.NO_CYCLE, new Stop[]{
                new Stop(0.36, Color.BLACK),
                new Stop(1, Color.FIREBRICK),
        });
        txt.setFill(gradient);
        javafx.scene.effect.DropShadow shadow = new javafx.scene.effect.DropShadow(3, 3, 3, Color.GREY);
        
        txt.setId("title");
        top.getChildren().add(txt);
        
        /* INIZIO EDIT */
        VBox pane  = new VBox();
        final Map<MenuCategory, MenuCategoryEntries> buttons = super.getListener().getButtons();
        buttons.keySet().forEach(category -> {
            if (category == MenuCategory.DEFAULT) {
                buttons.get(category).getEntries().forEach(button -> {
                    pane.getChildren().add(new MenuItem(button.getName(), this, button.getEvent(), super.getListener()));
                });
            }
        });
        
        pane.setMaxWidth(350);
        pane.setPadding(new Insets(42));        
        
        Image bug = new Image("images/bug/fall-dx0.png");
        ImageView bugImg = new ImageView(bug);
        bugImg.setEffect(new javafx.scene.effect.DropShadow(28, 25, 15, Color.GREY));
        p1.setTranslateY(280);
        p1.setTranslateX(-190);
        p1.getChildren().add(bugImg);
        p1.setMouseTransparent(true);
        
        
        
        Image bug2 = new Image("images/bug/fall-sx0.png");
        ImageView bugImg2 = new ImageView(bug2);
        bugImg2.setEffect(new javafx.scene.effect.DropShadow(28, -25, 15, Color.GREY));
        p2.setTranslateY(280);
        p2.setTranslateX(580);
        p2.getChildren().add(bugImg2);
        p2.setMouseTransparent(true);
        /* FINE EDIT */
        
        border.setCenter(pane);
        border.autosize();
        border.setTop(top);
        super.getRoot().getChildren().addAll(border, p1, p2 );

    }
    
    @Override
    public void accept(final Visitor visitor) {
        visitor.visit(this);
    }
        
    public void animateMenu(Pane p, String string) {
        final Timeline timeline = new Timeline();
        timeline.setCycleCount(1);
        timeline.setAutoReverse(false);

        int cont = 0;
        if (string.equals("shoot-dx")) {
            p.setEffect(new javafx.scene.effect.DropShadow(28, 25, 15, Color.GREY));

        } else {
            p.setEffect(new javafx.scene.effect.DropShadow(28, -25, 15, Color.GREY));
        }
        
        for (final ImageView im : ResourcesManager.getResourceManager().getResources(Entities.BUG, string,
                new Dimension2D(p.getPrefWidth(), p.getPrefHeight()))) {
            final KeyFrame key = new KeyFrame(Duration.millis(cont), bx -> {
                p.getChildren().clear();
                p.getChildren().add(im);
            });
            timeline.getKeyFrames().add(key);
            cont += 125;
        }

        timeline.play();
    }
    
    public Pane getLeftPane() {
        return this.p1;
    }
    
    public Pane getRightPane() {
        return this.p2;
    }

}
