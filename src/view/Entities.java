package view;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum Entities {

    MAGNO("magno", true, 4, Actions.values()),
    BOCC("bocc", true, 4, Actions.values()),
    MAGNOBULLET("magnobullet",true, 3, Actions.MOVE, Actions.DEATH),
    BOCCBULLET("boccbullet",true, 3, Actions.MOVE, Actions.DEATH),
    DINO("dino",true, 4, Actions.IDLE, Actions.SHOOT, Actions.DEATH),
    VOLPE("volpe", true, 3, Actions.values()),
    BUG("bug", true, 3, Actions.MOVE, Actions.IDLE, Actions.SHOOT, Actions.DEATH),
    BULLET("bullet", true, 3, Actions.MOVE, Actions.DEATH),
    GROUND("ground", false, 3, Actions.IDLE),
    PLATFORM("platform", false, 3, Actions.MOVE, Actions.IDLE, Actions.JUMP, Actions.FALL);
    
    private final String name;
    private final boolean animated;
    private final int nAssets;
    private final Actions[] allowedActions;
    
    private Entities(final String name, final boolean animated, final int nAssets, final Actions...actions) {
        this.name = name;
        this.animated = animated;
        this.nAssets = nAssets;
        this.allowedActions = actions;
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
    
    public List<Actions> getAllowedActions() {
        return Collections.unmodifiableList(Arrays.asList(this.allowedActions));
    }
    
}
