package view;

import java.util.List;
import java.util.Optional;

import control.Control;
import javafx.application.Platform;
import javafx.geometry.Dimension2D;
import javafx.stage.Stage;
import utility.Pair;

public class ViewControllerImpl implements ViewController {
    
    private final Stage primaryStage;
    private Optional<DynamicView> drawableView = Optional.empty();
    private Control listener;
    
    public ViewControllerImpl(final Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
    
    @Override
    public void changeScene(final Pair<SceneType, Dimension2D> settings) {
        final GenericView newScene = ViewFactory.createNewScene(this.primaryStage, this.listener, settings);
        newScene.initScene();
        newScene.display();
        final VisitorImpl visitor = new VisitorImpl();
        newScene.accept(visitor);
        this.drawableView = visitor.getView();
    }
    
    @Override
    public void updateScene(final List<ControlComunication> entities) {
        final DynamicView dv = this.drawableView.orElseThrow(IllegalStateException::new);
        Platform.runLater(() -> dv.updateScene(entities));
    }
    
    @Override
    public void notifySceneEvent(final Notifications notification) {
        final DynamicView dv = this.drawableView.orElseThrow(IllegalStateException::new);
        if (notification == Notifications.PLAY) {
            Platform.runLater(() -> dv.playScene());
        } else {
            Platform.runLater(() -> dv.pauseScene(notification));
        }
    }
    
    @Override
    public void setListener(final Control listener) {
        this.listener = listener;
    }

}
