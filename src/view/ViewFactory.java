package view;

import control.Control;
import control.Pair;
import javafx.geometry.Dimension2D;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class ViewFactory {
    
    public static GenericView createNewScene(final Stage stage, final Control listener, final Pair<SceneType, Dimension2D> settings) {
        
        switch(settings.getX()) {
   
        case DRAWABLE:
            return new DynamicViewImpl(stage, listener,calculateSceneDimension(settings.getY()) , settings.getY());
        case MENU:
            return new StaticView(stage, listener, settings.getY());
        default:
            throw new IllegalArgumentException();
        }   
    }
    
    private static Dimension2D calculateSceneDimension(final Dimension2D worldDimension) {
        final int screenWidth = (int) Screen.getPrimary().getVisualBounds().getWidth();
        if (worldDimension.getWidth() > screenWidth / 2) {
            return new Dimension2D(screenWidth / 2 , worldDimension.getHeight());
        }
        return new Dimension2D(worldDimension.getWidth(), worldDimension.getHeight());
    }
}
