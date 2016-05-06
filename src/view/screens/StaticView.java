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
import javafx.scene.effect.DropShadow;
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
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Font;
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
    
    private static final int AMIMATIONTIME = 125;
    
    private final Pane p1 = new Pane();
    private final Pane p2 = new Pane();
    private final double shadowRadius = super.getSceneDimension().getWidth() / 68;
    private final double shadowX = super.getSceneDimension().getWidth() / 76.8;
    private final double ShadowY = super.getSceneDimension().getHeight() / 72;
    
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
        DropShadow shadowTitle = new DropShadow(10, Color.GRAY);
        txt.setEffect(shadowTitle);
        txt.setId("title");
        txt.setFont(new Font(super.getSceneDimension().getWidth() / 5.052));
        top.getChildren().add(txt);
        
        /* INIZIO EDIT */
        final VBox menuBox  = new VBox();
        final Map<MenuCategory, MenuCategoryEntries> buttons = super.getListener().getButtons();
        buttons.keySet().forEach(category -> {
            if (category == MenuCategory.DEFAULT) {
                buttons.get(category).getEntries().forEach(button -> {
                    menuBox.getChildren().add(new MenuItem(button.getName(), this, button.getEvent(), super.getListener(), super.getSceneDimension()));
                });
            }
        });
        
        menuBox.setMaxWidth(super.getSceneDimension().getWidth() / 2.55);
        menuBox.setPadding(new Insets(super.getSceneDimension().getHeight() / 10));      
        
        final Image bug = new Image("images/bug/fall-dx0.png");
        final ImageView bugImg = new ImageView(bug);
        bugImg.setFitWidth(super.getSceneDimension().getWidth() / 1.667);
        bugImg.setFitHeight(super.getSceneDimension().getHeight() / 1.807);
        bugImg.setEffect(new javafx.scene.effect.DropShadow(this.shadowRadius, this.shadowX, this.ShadowY, Color.GREY));
        p1.setTranslateY(super.getSceneDimension().getHeight() / 1.82);
        p1.setTranslateX(-super.getSceneDimension().getWidth() / 5.05);
        p1.getChildren().add(bugImg);
        p1.setMouseTransparent(true);
        System.out.println(super.getSceneDimension().getWidth());
        System.out.println(super.getSceneDimension().getHeight());
        
        final Image bug2 = new Image("images/bug/fall-sx0.png");
        final ImageView bugImg2 = new ImageView(bug2);
        bugImg2.setFitWidth(super.getSceneDimension().getWidth() / 1.667);
        bugImg2.setFitHeight(super.getSceneDimension().getHeight() / 1.807);
        bugImg2.setEffect(new javafx.scene.effect.DropShadow(this.shadowRadius, -this.shadowX, this.ShadowY, Color.GREY));
        p2.setTranslateY(super.getSceneDimension().getHeight() / 1.82);
        p2.setTranslateX(super.getSceneDimension().getWidth() / 1.65);
        p2.getChildren().add(bugImg2);
        p2.setMouseTransparent(true);
        /* FINE EDIT */
        
        border.setCenter(menuBox);
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
                im.setFitWidth(super.getSceneDimension().getWidth() / 1.667);
                im.setFitHeight(super.getSceneDimension().getHeight() / 1.807);
                p.getChildren().add(im);
            });
            timeline.getKeyFrames().add(key);
            cont += AMIMATIONTIME;
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
