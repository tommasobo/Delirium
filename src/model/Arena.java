package model;

import java.util.List;

public interface Arena {

    void add(final Entities entities);

    void add(final EntitiesImpl entitiesImpl);

    void add(final HeroImpl hero);

    void add(final Bullet bullet);

    Hero getHero();

    Entities getGoal();

    List<Entities> getEntities();

    List<Bullet> getBullets();

}