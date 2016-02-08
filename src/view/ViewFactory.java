package view;

import java.util.Optional;

import control.Control;
import control.Dimension;
import control.Pair;
import javafx.stage.Stage;

public class ViewFactory {
    
    public static Optional<DynamicView> createNewScene(final Stage stage, final Control listener, final Pair<SceneType, Dimension> settings) {
        
        switch(settings.getX()) {
   
        case DRAWABLE:
            final GenericView gv = new DynamicViewImpl(stage, listener);
            gv.initView(settings.getY());
            gv.display();
            return Optional.of((DynamicView)gv);
        case MENU:
            final GenericView sv = new StaticView(stage, listener);
            sv.initView(settings.getY());
            sv.display();
            return Optional.empty();
        default:
 
        }
        
        return Optional.empty();
    }
    
}
