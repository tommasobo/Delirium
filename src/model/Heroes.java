package model;


public enum Heroes {
    
    JOY(Hero.STANDARD_CODE, Hero.MAX_LIFE, Hero.INITIAL_POSITION, Hero.INITIAL_SPEED);
    
    final private int code;
    final private int life;
    final private ModelPosition position;
    final private int speed;
    
    Heroes(final int code, final int life, final ModelPosition position, final int speed) {
        this.code = code;
        this.life = life;
        this.position = position;
        this.speed = speed;
    }

    public int getCode() {
        return code;
    }

    public int getLife() {
        return life;
    }

    public ModelPosition getPosition() {
        return position;
    }
    
    public int gestSpeed() {
        return speed;
    }

}
