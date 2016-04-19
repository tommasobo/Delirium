package control.viewcomunication.translation;

import java.util.LinkedList;
import java.util.List;

import model.arena.entities.Position;
import model.transfertentities.EntitiesInfoToControl;
import view.utilities.ControlCommunication;
import view.utilities.ViewPhysicalProperties;

/**
 * Class that translate model's entities to view entities, translate positions
 * for a correct view print.
 * 
 * @author Matteo Magnani
 *
 */
public class GameWorldTranslatorImpl implements GameWorldTranslator {
    private final EntitiesDatabase database;
    private final Double screenMultiplierFactor;

    /**
     * 
     * @param database
     *            The database that contains the view representation for each
     *            model entity
     * @param screenMultiplierFactor
     *            The necessary multiplier factor to visualize the game world on
     *            screen
     */
    public GameWorldTranslatorImpl(final EntitiesDatabase database, final Double screenMultiplierFactor) {
        super();
        this.database = database;
        this.screenMultiplierFactor = screenMultiplierFactor;
    }

    @Override
    public List<ControlCommunication> entitiesListFromModelToView(final List<EntitiesInfoToControl> listInfo) {
        final List<ControlCommunication> viewList = new LinkedList<>();
        listInfo.stream().forEach(e -> {
            viewList.add(new ControlCommunication(e.getCode(), this.database.getViewEntity(e.getCode()), e.getLife(),
                    positionFromModeltoView(e), Translator.getViewActionsForEntities(e)));
        });
        return viewList;
    }

    /**
     * The method translates the model's entity to view's entity. It takes the
     * view representation from database and translate the entity position
     * according to view needs and screen multiplier factor
     * 
     * @param info
     *            The model entity
     * @return The view entity
     */
    private ViewPhysicalProperties positionFromModeltoView(final EntitiesInfoToControl info) {
        final Position p = info.getPosition();
        return new ViewPhysicalProperties((int) Math.round(p.getPoint().getX() * this.screenMultiplierFactor),
                (int) Math.round((this.database.getArenaDimension().getHeight() - p.getPoint().getY()
                        - p.getDimension().getX().getHeight()) * this.screenMultiplierFactor),
                (int) Math.round(p.getDimension().getX().getWidth() * this.screenMultiplierFactor),
                (int) Math.round(p.getDimension().getX().getHeight() * this.screenMultiplierFactor),
                info.getSpeed().isPresent() ? (int) Math.round(info.getSpeed().get() * this.screenMultiplierFactor) : 0,
                Translator.directionFromModeltoView(p.getDirection()));
    }
}
