package control;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import model.EntitiesInfoToControl;
import model.Position;
import utility.Pair;
import view.ControlComunication;
import view.ViewPhysicalProperties;

public final class Translator {

    private Translator() {
        
    }
    
    //TODO eventualmente da spostare dentro controller impl
    //TODO cambiare nome in translate key event
    public static Pair<model.Actions, Optional<model.Directions>> tranViewEvents(final ViewEvents event) {
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
    
 // eventualmente da sostare dentro controller impl
    public static List<ControlComunication> entitiesListFromModelToView(final List<EntitiesInfoToControl> listInfo, final EntitiesDatabase database) {
        final List<ControlComunication> viewList = new LinkedList<>();
        listInfo.stream().forEach(e -> {
            viewList.add(new ControlComunication(e.getCode(), database.getViewEntity(e.getCode()), e.getLife(),
                    positionFromModeltoView(e, database), getViewActionsForEntities(e)));
        });
        return viewList;
    }

    public static ViewPhysicalProperties positionFromModeltoView(final EntitiesInfoToControl info, final EntitiesDatabase database) {
        // TODO mi serve una interfaccia position senza setter, anche un point
        final Position p = info.getPosition();
        return new ViewPhysicalProperties(p.getPoint().getX() * ViewDecoratorImpl.screenMoltiplicatorFactor,
                (database.getArenaDimension().getHeight() - p.getPoint().getY() - p.getDimension().getHeight()) * ViewDecoratorImpl.screenMoltiplicatorFactor,
                p.getDimension().getWidth() * ViewDecoratorImpl.screenMoltiplicatorFactor, p.getDimension().getHeight() * ViewDecoratorImpl.screenMoltiplicatorFactor, info.getSpeed().isPresent() ? info.getSpeed().get() * ViewDecoratorImpl.screenMoltiplicatorFactor : 0,
                directionFromModeltoView(p.getDirection()));
    }

    private static view.Directions directionFromModeltoView(final model.Directions direction) {
        switch (direction) {
        case LEFT:
            return view.Directions.LEFT;
        case RIGHT:
            return view.Directions.RIGHT;
        case NONE:
            return view.Directions.NONE;
        default:
            // TODO cambia exception in IllegalEventException
            throw new IllegalArgumentException();
        }
    }

    private static view.Actions actionsFromModeltoView(final model.Actions action) {
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
    
    private static view.Actions getViewActionsForEntities(final EntitiesInfoToControl entity) {
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
