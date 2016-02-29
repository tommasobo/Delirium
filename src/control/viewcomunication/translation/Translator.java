package control.viewcomunication.translation;

import java.util.Optional;

import control.viewcomunication.ViewEvents;
import model.transfertentities.EntitiesInfoToControl;
import utility.Pair;

/**
 * Utility class that contains static methods to translate elements between view
 * and model
 * 
 * @author Matteo Magnani
 *
 */
final class Translator {

    private Translator() {

    }

    /**
     * The method takes a view input and translates it in a pair that represents
     * the PG action in model environment. Both stop and start key's press are
     * translated in same way, the method is for the functions of input manager
     * 
     * @param event
     *            The view key event
     * @return pair that represent the PG action in model environment
     */
    public static Pair<model.arena.utility.Actions, Optional<model.arena.utility.Directions>> translateViewInput(
            final ViewEvents event) {
        switch (event) {
        case STOPMLEFT:
        case MLEFT:
            return new Pair<>(model.arena.utility.Actions.MOVE, Optional.of(model.arena.utility.Directions.LEFT));
        case STOPMRIGHT:
        case MRIGHT:
            return new Pair<>(model.arena.utility.Actions.MOVE, Optional.of(model.arena.utility.Directions.RIGHT));
        case STOPJUMP:
        case JUMP:
            return new Pair<>(model.arena.utility.Actions.JUMP, Optional.empty());
        case SHOOT:
        case STOPSHOOT:
            return new Pair<>(model.arena.utility.Actions.SHOOT, Optional.empty());
        default:
            throw new IllegalArgumentException(event.toString());
        }
    }

    /**
     * The method translates directions from model to view environment
     * 
     * @param direction
     *            the model direction
     * @return the view direction
     */
    public static view.configs.Directions directionFromModeltoView(final model.arena.utility.Directions direction) {
        switch (direction) {
        case LEFT:
            return view.configs.Directions.LEFT;
        case RIGHT:
            return view.configs.Directions.RIGHT;
        case NONE:
            return view.configs.Directions.NONE;
        default:
            throw new IllegalArgumentException(direction.toString());
        }
    }

    /**
     * The method translates actions from model to view environment, fall and
     * jump have priority over move for animations's necessities
     * 
     * @param action
     *            the model direction
     * @return the view action
     */
    public static view.configs.Actions actionsFromModeltoView(final model.arena.utility.Actions action) {
        switch (action) {
        case MOVEONFALL:
        case FALL:
            return view.configs.Actions.FALL;
        case MOVEONJUMP:
        case JUMP:
            return view.configs.Actions.JUMP;
        case MOVE:
            return view.configs.Actions.MOVE;
        case SHOOT:
            return view.configs.Actions.SHOOT;
        case STOP:
            return view.configs.Actions.IDLE;
        default:
            throw new IllegalArgumentException(action.toString());
        }
    }

    /**
     * The method returns the view's action of an entity.
     * 
     * @param entity
     *            Model's entity
     * @return Entity's action in view environment
     */
    public static view.configs.Actions getViewActionsForEntities(final EntitiesInfoToControl entity) {
        if (entity.getLife() == 0) {
            return view.configs.Actions.DEATH;
        } else {
            return actionsFromModeltoView(entity.getAction());
        }
    }

    /**
     * The method returns the appropriate bullet's rapresentation
     * 
     * @param entity
     *            View's entity representation
     * @return View's representation of entity bullet
     */
    public static view.configs.Entities getEntityBulletType(final view.configs.Entities entity) {
        switch (entity) {
        case BOCC:
            return view.configs.Entities.BOCCBULLET;
        case MAGNO:
            return view.configs.Entities.MAGNOBULLET;
        case JOY:
            return view.configs.Entities.JOYBULLET;
        default:
            return view.configs.Entities.BULLET;

        }
    }

}
