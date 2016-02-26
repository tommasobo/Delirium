package control;

import java.util.Optional;

import model.transfertentities.EntitiesInfoToControl;
import utility.Pair;

public final class Translator {

    private Translator() {
        
    }
    
    public static Pair<model.arena.utility.Actions, Optional<model.arena.utility.Directions>> translateViewInput(final ViewEvents event) {
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
    
    public static view.configs.Actions getViewActionsForEntities(final EntitiesInfoToControl entity) {
        if(entity.getLife() == 0) {
            return view.configs.Actions.DEATH;
        } else {
            return actionsFromModeltoView(entity.getAction());
        }
    }

    public static view.configs.Entities getEntityBulletType(final view.configs.Entities entity) {
        switch(entity) {
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
