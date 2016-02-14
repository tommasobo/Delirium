package view;

public enum Entities {

    JOY(0, "joy", 2, Actions.values()),
    //MONSTER1(1, "monster1", 2, Actions.values()),
    //COFFER(1, "coffer", 2, Actions.IDLE, Actions.DEATH),
    GROUND(2, "ground", 3, Actions.IDLE),
    PLATFORM(3, "platform", 3, Actions.IDLE);
    
    private final int code;
    private final String name;
    private final int nAssets;
    private final Actions[] actions;
    
    private Entities(final int code, final String name, final int nAssets, final Actions...actions) {
        this.code = code;
        this.name = name;
        this.nAssets = nAssets;
        this.actions = actions;
    }

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public int getnAssets() {
        return nAssets;
    }

    public Actions[] getActions() {
        return actions;
    }
    
}
