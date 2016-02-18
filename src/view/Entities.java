package view;

public enum Entities {

    MAGNO("magno", true, 4),
    BOCC("bocc", true, 4),
    MAGNOBULLET("magnobullet",true, 3),
    BOCCBULLET("boccbullet",true, 3),
    DINO("dino",true,3),
    VOLPE("volpe",true,3),
    BUG("bug",true,3),
    BULLET("bullet",true,3),
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
