package control;

public interface EntityStatsModifier {
    Integer getSpeedIncremented(Integer speed);
    Integer getLifeIncremented(Integer life);
    Integer getDamageIncremented(Integer damage);
}
