package view;

import java.util.List;

public interface DynamicView {
    
    void updateScene(final List<ControlComunication> entities);
    void pauseScene();
    void playScene();
    
}
