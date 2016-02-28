package view.screens;

import control.Control;
import javafx.geometry.Dimension2D;
import javafx.stage.Screen;
import javafx.stage.Stage;
import utility.Pair;
import view.configs.SceneType;

/**
 * This utility class instantiates a new scene using configuration parameters
 * given in input.
 */
public final class ViewFactory {

    private ViewFactory() {
    }

    /**
     * This method instantiates a new scene.
     * 
     * @param stage
     *            The main JavaFX container.
     * @param listener
     *            The controller listener.
     * @param settings
     *            The scene dimension for static ones, and game world for
     *            others.
     * @return The created GenericView.
     * @throws IllegalStateException
     *             If the scene type requested is not supported
     */
    public static GenericView createNewScene(final Stage stage, final Control listener,
            final Pair<SceneType, Dimension2D> settings) {

        switch (settings.getX()) {

        case DRAWABLE:
            return new DynamicViewImpl(stage, listener, calculateSceneDimension(settings.getY()), settings.getY());
        case MENU:
            return new StaticView(stage, listener, settings.getY());
        default:
            throw new IllegalArgumentException("No view to instantiate for this parameter");
        }
    }

    /**
     * This method calculates scene dimension using game world dimension
     * 
     * @param worldDimension
     *            Dimension of the game world
     * @return The dimension of the scene.
     */
    private static Dimension2D calculateSceneDimension(final Dimension2D worldDimension) {
        final int screenWidth = (int) Screen.getPrimary().getVisualBounds().getWidth();
        if (worldDimension.getWidth() > (screenWidth / 3) * 2) {
            return new Dimension2D((screenWidth / 3) * 2, worldDimension.getHeight());
        }
        return new Dimension2D(worldDimension.getWidth(), worldDimension.getHeight());
    }
}
