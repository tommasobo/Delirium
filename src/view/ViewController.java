package view;

import java.util.List;

import control.Control;
import javafx.geometry.Dimension2D;
import utility.Pair;

public interface ViewController {
    
    void updateScene(final List<ControlComunication> entities);
    void changeScene(final Pair<SceneType, Dimension2D> settings);
    void notifySceneEvent(final Notifications notification);
    void setListener(final Control listener);
    
}
