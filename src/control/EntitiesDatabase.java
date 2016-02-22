package control;

import java.util.List;

import model.EntitiesInfo;
import view.Entities;

interface EntitiesDatabase {
	
	void putEntity(Integer code, Entities entity);
	Entities getViewEntity(Integer code);
	Dimension getArenaDimension();
	void putArenaDimension(Dimension dimension);
	List<EntitiesInfo> putEntitiesAndSetCodes(List<EntitiesInfo> entities, Entities entity);
	public EntitiesInfo putEntityAndSetCode(EntitiesInfo modelEnt, Entities entity);
	List<EntitiesInfo> putBulletsAndSetCodes(List<EntitiesInfo> entities);
}
