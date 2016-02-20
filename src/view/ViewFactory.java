package view;

import control.Control;
import control.Pair;
import javafx.geometry.Dimension2D;
import javafx.stage.Stage;

public class ViewFactory {
    
    public static GenericView createNewScene(final Stage stage, final Control listener, final Pair<SceneType, Dimension2D> settings) {
        
        switch(settings.getX()) {
   
        case DRAWABLE:
            return new DynamicViewImpl(stage, listener, settings.getY());
        case MENU:
            return new StaticView(stage, listener, settings.getY());
        default:
            throw new IllegalArgumentException();
        }   
    }
}
