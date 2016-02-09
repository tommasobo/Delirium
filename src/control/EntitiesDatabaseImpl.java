package control;

import java.util.HashMap;
import java.util.Map;

import view.Entities;

class EntitiesDatabaseImpl implements EntitiesDatabase {

	private Map<Integer, Entities> viewEntitiesCodes;
	
	public EntitiesDatabaseImpl() {
		this.viewEntitiesCodes = new HashMap<>();
	}
	
	public void putEntity(Integer code, Entities entity) {
		this.viewEntitiesCodes.put(code, entity);
	}
	
	@Override
	public Entities getViewEntity(Integer code) {
		return this.viewEntitiesCodes.get(code);
	}

}
