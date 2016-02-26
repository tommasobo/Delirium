package control;

import java.util.List;

import utility.Dimension;
import view.configs.Notifications;
import view.configs.SceneType;
import view.utilities.ControlComunication;

public interface ViewDecorator {
    void changeScene(SceneType sceneType);
    void notifySceneEvent(Notifications notification);
    void updateScene(final List<ControlComunication> entities);
    void setLevelDimension(Dimension levelDimension);
    int getScreenMoltiplicatorFactor();
}
