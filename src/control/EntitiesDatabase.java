package control;

import view.Entities;

interface EntitiesDatabase {
	
	void putEntity(Integer code, Entities entity);
	Entities getViewEntity(Integer code);
}
