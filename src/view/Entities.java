package view;

public enum Entities {

    JOY("joy", true, 4),
    //MONSTER1("monster1", true, 2, Actions.values()),
    //COFFER("coffer", true, 2, Actions.IDLE, Actions.DEATH),
    GROUND("ground", false, 3),
    PLATFORM("platform", false, 3);
    
    private final String name;
    private final boolean animated;
    private final int nAssets;
    
    private Entities(final String name, final boolean animated, final int nAssets) {
        this.name = name;
        this.animated = animated;
        this.nAssets = nAssets;
    }

    public String getName() {
        return this.name;
    }
    
    public boolean isAnimated() {
        return this.animated;
    }

    public int getnAssets() {
        return this.nAssets;
    }
    
}
