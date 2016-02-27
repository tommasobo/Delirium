package control.viewcomunication.translation;

import java.util.LinkedList;
import java.util.List;

import model.arena.entities.Position;
import model.transfertentities.EntitiesInfoToControl;
import view.utilities.ControlComunication;
import view.utilities.ViewPhysicalProperties;

/**
 * Class that translate model's entities to view entities, translate positions
 * for a correct view print
 * 
 * @author Matteo Magnani
 *
 */
public class GameWorldTranslatorImpl implements GameWorldTranslator {
    private final EntitiesDatabase database;
    private final int screenMultiplierFactor;

    /**
     * 
     * @param database The database that contains the view representation for each model entity
     * @param screenMoltiplicatorFactor The necessary multiplier factor to visualize the game world on screen
     */
    public GameWorldTranslatorImpl(final EntitiesDatabase database, final int screenMultiplierFactor) {
        super();
        this.database = database;
        this.screenMultiplierFactor = screenMultiplierFactor;
    }

    @Override
    public List<ControlComunication> entitiesListFromModelToView(final List<EntitiesInfoToControl> listInfo) {
        final List<ControlComunication> viewList = new LinkedList<>();
        listInfo.stream().forEach(e -> {
            viewList.add(new ControlComunication(e.getCode(), this.database.getViewEntity(e.getCode()), e.getLife(),
                    positionFromModeltoView(e), Translator.getViewActionsForEntities(e)));
        });
        return viewList;
    }

    /**
     * The method translate the model's entity to view's entity. It take the
     * view representation to database and translate the entity position
     * according to view needs and screen multiplier factor
     * 
     * @param info
     *            The model entity
     * @return The view entity
     */
    private ViewPhysicalProperties positionFromModeltoView(final EntitiesInfoToControl info) {
        final Position p = info.getPosition();
        return new ViewPhysicalProperties(p.getPoint().getX() * this.screenMultiplierFactor,
                (this.database.getArenaDimension().getHeight() - p.getPoint().getY() - p.getDimension().getHeight())
                        * this.screenMultiplierFactor,
                p.getDimension().getWidth() * this.screenMultiplierFactor,
                p.getDimension().getHeight() * this.screenMultiplierFactor,
                info.getSpeed().isPresent() ? info.getSpeed().get() * this.screenMultiplierFactor : 0,
                Translator.directionFromModeltoView(p.getDirection()));
    }
}
