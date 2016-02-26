package model.arena;

import java.util.List;

import model.arena.entities.Bullet;
import model.arena.entities.Entities;
import model.arena.entities.EntitiesImpl;
import model.arena.entities.Hero;
import model.arena.entities.HeroImpl;

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