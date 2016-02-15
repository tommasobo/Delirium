package control;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import model.EntitiesInfo;
import model.Position;
import view.Actions;
import view.ControlComunication;
import view.Entities;
import view.ViewPhysicalProperties;

class Translator {
	
	//eventualmente da sostare dentro controller impl
	public static Pair<model.Actions, Optional<model.Directions>> tranViewEvents(ViewEvents event) {
		switch(event) {
		case STOPMLEFT:
		case MLEFT: return new Pair<>(model.Actions.MOVE, Optional.of(model.Directions.LEFT));
		case STOPMRIGHT:
		case MRIGHT: return new Pair<>(model.Actions.MOVE, Optional.of(model.Directions.RIGHT));
		case STOPJUMP:
		case JUMP: return new Pair<>(model.Actions.JUMP, Optional.empty());
		default:
			throw(new IllegalArgumentException());
		}
	}
	
	public static ViewPhysicalProperties positionFromModeltoView (EntitiesInfo info, EntitiesDatabase database) {
		//TODO mi serve una interfaccia position senza setter, anche un point
		Position p = info.getPosition();
		return new ViewPhysicalProperties(p.getPoint().getX(), database.getArenaDimension().getHeight() - p.getPoint().getY() - p.getDimension().getHeight(), p.getDimension().getWidth(), p.getDimension().getHeight(), info.getSpeed(), directionFromModeltoView(p.getDirection()));
	}
	
	private static view.Directions directionFromModeltoView (model.Directions direction) {
		switch(direction) {
		case LEFT: return view.Directions.LEFT;
		case RIGHT: return view.Directions.RIGHT;
		case NONE: return view.Directions.NONE;
		default:
			//TODO cambia exception in IllegalEventException
			throw(new IllegalArgumentException());
		}
	}
	
	private static view.Actions actionsFromModeltoView (model.Actions action) {
		switch(action) {
		case FALL:
			return view.Actions.FALL;
		case JUMP:
			return view.Actions.JUMP;
		case MOVE:
			return view.Actions.MOVE;
		case STOP:
			return view.Actions.IDLE;
		default:
			throw(new IllegalArgumentException());
		}
	}
	
	//eventualmente da sostare dentro controller impl
	public static List<ControlComunication> mapFromModelToView(List<EntitiesInfo> listInfo, EntitiesDatabase database) {
		List<ControlComunication> viewList = new LinkedList<>();
		listInfo.stream().forEach(e -> {
			viewList.add(new ControlComunication(e.getCode(), database.getViewEntity(e.getCode()),e.getLife(), positionFromModeltoView(e, database), actionsFromModeltoView(e.getAction())));
		});
		return viewList;
	}

}
