package view;

import java.util.List;

import control.Control;
import javafx.geometry.Dimension2D;
import utility.Pair;
import view.configs.Notifications;
import view.configs.SceneType;
import view.utilities.ControlComunication;

public interface ViewController {

    void updateScene(final List<ControlComunication> entities);

    void changeScene(final Pair<SceneType, Dimension2D> settings);

    void notifySceneEvent(final Notifications notification);

    void setListener(final Control listener);

}
