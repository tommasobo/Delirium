package view;

public class ControlComunication {
    private final int code;
    private final int life;
    private final Entities entity;
    private final ViewPhysicalProperties property;
    private final Actions action;

    public ControlComunication(final int code, final Entities entity, final int life,
            final ViewPhysicalProperties property, final Actions action) {
        this.code = code;
        this.entity = entity;
        this.life = life;
        this.property = property;
        this.action = action;
    }

    public int getCode() {
        return this.code;
    }

    public Entities getEntity() {
        return this.entity;
    }

    public ViewPhysicalProperties getProperty() {
        return this.property;
    }

    public Actions getAction() {
        return this.action;
    }

    public int getLife() {
        return this.life;
    }

}
