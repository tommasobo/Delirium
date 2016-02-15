package view;


public class ControlComunication {
	private final int code;
	private final Entities entity;
	private final ViewPhysicalProperties property;
	private final Actions action;
	
	public ControlComunication(int code, Entities entity, ViewPhysicalProperties property, Actions action) {
		super();
		this.code = code;
		this.entity = entity;
		this.property = property;
		this.action = action;
	}

	public int getCode() {
		return code;
	}

	public Entities getEntity() {
		return entity;
	}

	public ViewPhysicalProperties getProperty() {
		return property;
	}

	public Actions getAction() {
		return action;
	}
	
	

}
