package control;

import java.util.HashMap;
import java.util.Map;

import view.Entities;

class EntitiesDatabaseImpl implements EntitiesDatabase {

	private Map<Integer, Entities> viewEntitiesCodes;
	private Dimension arenaDimension;
	
	public EntitiesDatabaseImpl() {
		this.viewEntitiesCodes = new HashMap<>();
		this.arenaDimension = null;
	}
	
	public void putEntity(Integer code, Entities entity) {
		this.viewEntitiesCodes.put(code, entity);
	}
	
	@Override
	public Entities getViewEntity(Integer code) {
		return this.viewEntitiesCodes.get(code);
	}
	
	public void putArenaDimension(Dimension dimension) {
		this.arenaDimension = dimension;
	}
	
	public Dimension getArenaDimension() {
		return this.arenaDimension;
	}

}
