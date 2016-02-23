package control;

import java.util.List;

import javafx.geometry.Dimension2D;
import view.ControlComunication;
import view.Notifications;
import view.SceneType;

public interface ViewDecorator {
    void changeScene(SceneType sceneType);
    void notifySceneEvent(Notifications notification);
    void updateScene(final List<ControlComunication> entities);
    void setLevelDimension(Dimension levelDimension);
}
