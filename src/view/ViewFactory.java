package view;

import java.util.Optional;

import control.Control;
import control.Pair;
import javafx.geometry.Dimension2D;
import javafx.stage.Stage;

public class ViewFactory {
    
    public static Optional<DynamicView> createNewScene(final Stage stage, final Control listener, final Pair<SceneType, Dimension2D> settings) {
        
        switch(settings.getX()) {
   
        case DRAWABLE:
            final GenericView gv = new DynamicViewImpl(stage, listener, settings.getY());
            gv.initScene();
            gv.display();
            return Optional.of((DynamicView)gv);
        case MENU:
            final GenericView sv = new StaticView(stage, listener, settings.getY());
            sv.initScene();
            sv.display();
            return Optional.empty();
        default:
 
        }
        
        return Optional.empty();
    }
    
}
