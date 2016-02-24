package model;

import java.util.List;

public interface Arena {

    void add(Entities entities);

    void add(EntitiesImpl entitiesImpl);

    void add(HeroImpl hero);

    void add(Bullet bullet);

    Hero getHero();

    Entities getGoal();

    List<Entities> getEntities();

    List<Bullet> getBullets();

}