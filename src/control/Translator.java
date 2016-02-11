package control;

import java.util.HashMap;
import java.util.Map;

import model.ModelDirections;
import model.ModelPosition;
import view.Entities;
import view.ViewPosition;

class Translator {
	
	//eventualmente da sostare dentro controller impl
	public static ModelDirections tranViewEvents(ViewEvents event) {
		switch(event) {
		case MLEFT: return ModelDirections.LEFT;
		case MRIGHT: return ModelDirections.RIGHT;
		case JUMP: return ModelDirections.UP;
		default:
			//TODO cambia exception in IllegalEventException
			throw(new IllegalArgumentException());
		}
	}
	
	public static ViewPosition positionFromModeltoView (ModelPosition position, Dimension arenaDimension) {
		return new ViewPosition(positionNormalizator(position.getPrimitivePosition(), arenaDimension), directionFromModeltoView(position.getDirection()));
	}
	
	private static PhisicalProprieties positionNormalizator(PhisicalProprieties position, Dimension arenaDimension) {
		position.setPoint(new Point(position.getPoint().getX(), arenaDimension.getHeight() - position.getPoint().getY()));
		return position;
	}
	
	private static ViewPosition.Directions directionFromModeltoView (ModelDirections direction) {
		switch(direction) {
		case LEFT: return ViewPosition.Directions.LEFT;
		case RIGHT: return ViewPosition.Directions.RIGHT;
		case UP: return ViewPosition.Directions.UP;
		case DOWN: return ViewPosition.Directions.DOWN;
		case NONE: return ViewPosition.Directions.NONE;
		default:
			//TODO cambia exception in IllegalEventException
			throw(new IllegalArgumentException());
		}
	}
	
	//eventualmente da sostare dentro controller impl
	public static Map<Integer, Pair<Entities, Pair<Integer, ViewPosition>>> mapFromModelToView(Map<Integer, Pair<Integer, ModelPosition>> modelMap, EntitiesDatabase database) {
		Map<Integer, Pair<Entities, Pair<Integer, ViewPosition>>> viewMap = new HashMap<>();
		modelMap.entrySet().forEach(e -> {
			viewMap.put(e.getKey(), new Pair<Entities, Pair<Integer, ViewPosition>>(database.getViewEntity(e.getKey()), new Pair<Integer, ViewPosition>(e.getValue().getX(), positionFromModeltoView(e.getValue().getY(), database.getArenaDimension()))));
		});
		return viewMap;
	}

}
