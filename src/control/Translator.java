package control;

import java.util.Optional;

import model.EntitiesInfoToControl;
import utility.Pair;

public final class Translator {

    private Translator() {
        
    }
    
    public static Pair<model.Actions, Optional<model.Directions>> translateViewInput(final ViewEvents event) {
        switch (event) {
        case STOPMLEFT:
        case MLEFT:
            return new Pair<>(model.Actions.MOVE, Optional.of(model.Directions.LEFT));
        case STOPMRIGHT:
        case MRIGHT:
            return new Pair<>(model.Actions.MOVE, Optional.of(model.Directions.RIGHT));
        case STOPJUMP:
        case JUMP:
            return new Pair<>(model.Actions.JUMP, Optional.empty());
        case SHOOT:
        case STOPSHOOT:
            return new Pair<>(model.Actions.SHOOT, Optional.empty());
        default:
            throw new IllegalArgumentException();
        }
    }

    public static view.Directions directionFromModeltoView(final model.Directions direction) {
        switch (direction) {
        case LEFT:
            return view.Directions.LEFT;
        case RIGHT:
            return view.Directions.RIGHT;
        case NONE:
            return view.Directions.NONE;
        default:
            throw new IllegalArgumentException();
        }
    }

    public static view.Actions actionsFromModeltoView(final model.Actions action) {
        switch (action) {
        case MOVEONFALL:
        case FALL:
            return view.Actions.FALL;
        case MOVEONJUMP:
        case JUMP:
            return view.Actions.JUMP;
        case MOVE:
            return view.Actions.MOVE;
        case SHOOT:
            return view.Actions.SHOOT;
        case STOP:
            return view.Actions.IDLE;
        default:
            throw new IllegalArgumentException();
        }
    }
    
    public static view.Actions getViewActionsForEntities(final EntitiesInfoToControl entity) {
        if(entity.getLife() == 0) {
            return view.Actions.DEATH;
        } else {
            return actionsFromModeltoView(entity.getAction());
        }
    }

    public static view.Entities getEntityBulletType(final view.Entities entity) {
        switch(entity) {
        case BOCC:
            return view.Entities.BOCCBULLET;
        case MAGNO:
            return view.Entities.MAGNOBULLET;
        case JOY:
            return view.Entities.JOYBULLET;
        default:
            return view.Entities.BULLET;
        
        }
    }

}
