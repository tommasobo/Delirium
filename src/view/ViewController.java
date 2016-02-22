package view;

import java.util.List;

import control.Control;
import control.Pair;
import javafx.geometry.Dimension2D;

public interface ViewController {
    
    void updateScene(final List<ControlComunication> entities);
    void changeScene(final Pair<SceneType, Dimension2D> settings);
    void notifySceneEvent(final Notifications notification);
    void setListener(final Control listener);
    
}
