package control;

public class EntityStatsModifierImpl implements EntityStatsModifier{
    private final GameDifficulty gameDifficulty;
    
    public EntityStatsModifierImpl(final GameDifficulty gameDifficulty) {
        this.gameDifficulty = gameDifficulty;
    }
    
    public int getSpeedIncremented(int speed) {
        switch(this.gameDifficulty) {
        case EASY:
            return (int) (speed * 0.5);
        case HARD:
            return (int) (speed * 1.5);
        case NORMAL:
            return (int) (speed * 1.0);
        case DELIRIUM:
            return (int) (speed * 3);
        default:
            //TODO eccezione opportuna
            throw new IllegalArgumentException();
        }
    }
    
    public int getLifeIncremented(int life) {
        switch(this.gameDifficulty) {
        case EASY:
            return (int) (life * 0.5);
        case HARD:
            return (int) (life * 1.5);
        case NORMAL:
            return (int) (life * 1.0);
        case DELIRIUM:
            return (int) (life * 3);
        default:
            //TODO eccezione opportuna
            throw new IllegalArgumentException();
        }
    }

    //TODO tipo primitivo sopra int?
    public int getDamageIncremented(int damage) {
        switch(this.gameDifficulty) {
        case EASY:
            return (int) (damage * 0.5);
        case HARD:
            return (int) (damage * 1.5);
        case NORMAL:
            return (int) (damage * 1.0);
        case DELIRIUM:
            return (int) (damage * 3);
        default:
            //TODO eccezione opportuna
            throw new IllegalArgumentException();
        }
    }
}
