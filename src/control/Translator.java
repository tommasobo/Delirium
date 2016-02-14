package control;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import view.Actions;
import model.Directions;
import model.EntitiesInfo;
import view.Entities;
import view.ViewPhysicalProperties;

class Translator {
	
	//eventualmente da sostare dentro controller impl
	public static Directions tranViewEvents(ViewEvents event) {
		switch(event) {
		case MLEFT: return Directions.LEFT;
		case MRIGHT: return Directions.RIGHT;
		case JUMP: return Directions.UP;
		case STOPMOVEMENT : return Directions.NONE;
		default:
			//TODO cambia exception in IllegalEventException
			throw(new IllegalArgumentException());
		}
	}
	
	public static ViewPhysicalProperties positionFromModeltoView (EntitiesInfo info, Dimension arenaDimension) {
		return new ViewPhysicalProperties(info.getPosition().getPoint().getX(), info.getPosition().getPoint().getY() - info.getPosition().getDimension().getHeight() - arenaDimension.getHeight(), info.getPosition().getDimension().getWidth(), info.getPosition().getDimension().getHeight(), info.getSpeed(), directionFromModeltoView(info.getPosition().getDirection()));
	}
	
	/*private static PhisicalProprieties positionNormalizator(PhisicalProprieties position, Dimension arenaDimension) {
		position.setPoint(new Point(position.getPoint().getX(), arenaDimension.getHeight() - position.getPoint().getY() - position.getDimension().getHeight()));
		return position;
	}*/
	
	private static Actions directionFromModeltoView (Directions direction) {
		switch(direction) {
		case LEFT: return Actions.LEFT;
		case RIGHT: return Actions.RIGHT;
		case UP: return Actions.UP;
		case DOWN: return Actions.DOWN;
		case NONE: return Actions.IDLE;
		default:
			//TODO cambia exception in IllegalEventException
			throw(new IllegalArgumentException());
		}
	}
	
	//eventualmente da sostare dentro controller impl
	public static Map<Integer, Pair<Entities, Pair<Integer, ViewPhysicalProperties>>> mapFromModelToView(List<EntitiesInfo> listInfo, EntitiesDatabase database) {
		//System.out.println(listInfo);
		Map<Integer, Pair<Entities, Pair<Integer, ViewPhysicalProperties>>> viewMap = new HashMap<>();
		listInfo.stream().forEach(e -> {
			viewMap.put(e.getCode(), new Pair<Entities, Pair<Integer, ViewPhysicalProperties>>(database.getViewEntity(e.getCode()), new Pair<Integer, ViewPhysicalProperties>(e.getLife(), positionFromModeltoView(e, database.getArenaDimension()))));
		});
		//System.out.println(viewMap);
		return viewMap;
	}

}
