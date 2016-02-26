package view;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import control.Control;
import javafx.application.Platform;
import javafx.geometry.Dimension2D;
import javafx.stage.Stage;
import utility.Pair;
import view.screens.DynamicView;
import view.screens.GenericView;
import view.screens.ViewFactory;

public class ViewControllerImpl implements ViewController {

    private final Stage primaryStage;
    private Optional<DynamicView> drawableView = Optional.empty();
    private Optional<Control> listener = Optional.empty();

    public ViewControllerImpl(final Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    @Override
    public void changeScene(final Pair<SceneType, Dimension2D> settings) {
        if (settings == null) {
            throw new IllegalArgumentException();
        }
        final GenericView newScene = ViewFactory.createNewScene(this.primaryStage, this.listener.orElseThrow(() -> new IllegalStateException("Listener not present")), settings);
        newScene.initScene();
        newScene.display();
        final VisitorImpl visitor = new VisitorImpl();
        newScene.accept(visitor);
        this.drawableView = visitor.getView();
    }

    @Override
    public void updateScene(final List<ControlComunication> entities) {
        if (entities == null || !entities.stream().map(e -> e.getCode()).allMatch(new HashSet<>()::add)) {
            throw new IllegalArgumentException("Duplicate code in entities list");
        }
        final DynamicView dv = this.drawableView.orElseThrow(() -> new IllegalStateException("Updatable screen not initialized"));
        Platform.runLater(() -> dv.updateScene(entities));
    }

    @Override
    public void notifySceneEvent(final Notifications notification) {
        if (notification == null) {
            throw new IllegalArgumentException("Illegal notification argument");
        }
        final DynamicView dv = this.drawableView.orElseThrow(() -> new IllegalStateException("Updatable screen not initialized"));
        if (notification == Notifications.PLAY) {
            Platform.runLater(() -> dv.playScene());
        } else {
            Platform.runLater(() -> dv.pauseScene(notification));
        }
    }

    @Override
    public void setListener(final Control listener) {
        if (listener == null) {
            throw new IllegalArgumentException("Illegal listener argument");
        }
        if (this.listener.isPresent()) {
            throw new IllegalStateException("Listener has already been set");
        }
        this.listener = Optional.of(listener);
    }

}
