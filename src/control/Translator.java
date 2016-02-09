package control;

import java.util.HashMap;
import java.util.Map;

import model.PGActions;
import view.Entities;

class Translator {
	
	//eventualmente da sostare dentro controller impl
	public static PGActions tranViewEvents(ViewEvents event) {
		switch(event) {
		case MLEFT: return PGActions.MLEFT;
		case MRIGHT: return PGActions.MRIGHT;
		case JUMP: return PGActions.JUMP;
		default:
			//TODO cambia exception in IllegalEventException
			throw(new IllegalArgumentException());
		}
	}
	
	//eventualmente da sostare dentro controller impl
	public static Map<Integer, Pair<Entities, Pair<Integer, Position>>> mapFromModelToView(Map<Integer, Pair<Integer, Position>> modelMap, EntitiesDatabase database) {
		Map<Integer, Pair<Entities, Pair<Integer, Position>>> viewMap = new HashMap<>();
		modelMap.entrySet().forEach(e -> {
			viewMap.put(e.getKey(), new Pair<Entities, Pair<Integer,Position>>(database.getViewEntity(e.getKey()), e.getValue()));
		});
		return viewMap;
	}

}
