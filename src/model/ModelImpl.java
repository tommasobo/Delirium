package model;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import model.arena.Arena;
import model.arena.ArenaImpl;
import model.arena.entities.Entities;
import model.arena.entities.Position;
import model.arena.entities.life.LifeManagerImpl;
import model.arena.entities.movement.MovementManager;
import model.arena.entities.movement.MovementManagerFactory;
import model.arena.entities.shoot.ShootManager;
import model.arena.entities.shoot.ShootManagerFactory;
import model.arena.manager.ArenaManager;
import model.arena.manager.ArenaManagerImpl;
import model.arena.utility.Actions;
import model.arena.utility.Directions;
import model.transfertentities.EntitiesInfo;
import model.transfertentities.EntitiesInfoToControl;
import model.transfertentities.EntitiesInfoToControlImpl;
import model.transfertentities.MovementInfo;
import utility.Pair;

public final class ModelImpl implements Model {

    private static final int DEFAULT_OFFSET_X = 500;
    private static final ModelImpl SINGLETON = new ModelImpl();
    private Arena arena;
    private ArenaManager arenaManager;

    private ModelImpl() {
    }

    public static ModelImpl getModel() {
        return SINGLETON;
    }

    @Override
    public void notifyEvent(final Directions direction) {
        this.arena.getHero().setPosition(arena.getHero().getPosition().getPoint(), direction);
    }

    @Override
    public void notifyEvent(final Actions action) {
        this.arena.getHero().setAction(action);
    }

    public List<EntitiesInfo> updateArena() {

        int size = this.arena.getEntities().size();
        for (int i = 0; i < size; i++) {
            if (this.arena.getEntities().get(i).getLifeManager().getLife() == 0) {
                this.arena.getEntities().remove(i);
                i--;
                size--;
            }
        }

        size = this.arena.getBullets().size();
        for (int i = 0; i < size; i++) {
            if (this.arena.getBullets().get(i).getLifeManager().getLife() == 0) {
                this.arena.getBullets().remove(i);
                i--;
                size--;
            }
        }

        this.arenaManager.moveEntities();

        final List<EntitiesInfo> bullets = new LinkedList<>();

        this.arena.getEntities().stream().forEach(t -> {
            if (t.getShootManager().isPresent()
                    && t.getPosition().getPoint().getX() > this.arena.getHero().getPosition().getPoint().getX()
                            - DEFAULT_OFFSET_X
                    && t.getPosition().getPoint().getX() < this.arena.getHero().getPosition().getPoint().getX()
                            + DEFAULT_OFFSET_X) {
                final Optional<EntitiesInfo> bullet = t.getShootManager().get().getBullet(t.getCode(), t.getPosition());
                if (bullet.isPresent()) {
                    bullets.add(bullet.get());
                }
            }

        });

        return bullets;
    }

    @Override
    public List<EntitiesInfoToControl> getState() {
        final List<EntitiesInfoToControl> result = new LinkedList<>();

        Entities ent;
        if (this.arenaManager.isGameWon()) {
            ent = this.arena.getGoal();
        } else if (this.arena.getHero().getLifeManager().getLife() == 0) {
            ent = this.arena.getHero();
        } else {
            Stream.concat(this.arena.getEntities().stream(), this.arena.getBullets().stream()).forEach(t -> {
                final Optional<Integer> speed = t.getMovementManager().isPresent()
                        ? Optional.of(t.getMovementManager().get().getSpeed()) : Optional.empty();
                result.add(new EntitiesInfoToControlImpl(t.getCode(), t.getLifeManager().getLife(), t.getPosition(),
                        t.getAction(), speed));
            });
            return result;
        }

        result.add(new EntitiesInfoToControlImpl(ent.getCode(), ent.getLifeManager().getLife(), ent.getPosition(),
                ent.getAction(), Optional.empty()));

        return result;
    }

    @Override
    public void createArena(final List<EntitiesInfo> entitiesInfo) {

        this.arena = new ArenaImpl();
        this.arenaManager = new ArenaManagerImpl(this.arena);

        entitiesInfo.stream().forEach(t -> {

            final Pair<Optional<Position>, Optional<MovementManager>> pair = MovementManagerFactory
                    .getMovementManager(t.getPosition(), t.getMovementInfo());
            final Optional<ShootManager> shootManager = ShootManagerFactory.getShootManager(t.getShootInfo());

            this.arena.add(new Entities.Builder().code(t.getCode())
                    .lifeManager(new LifeManagerImpl(t.getLife(), t.getLifePattern()))
                    .position(pair.getX().isPresent() ? pair.getX().get() : null)
                    .movementManager(pair.getY().isPresent() ? pair.getY().get() : null)
                    .shootManager(shootManager.isPresent() ? shootManager.get() : null)
                    .contactDamage(t.getContactDamage().isPresent() ? t.getContactDamage().get() : null).build());
        });

    }

    @Override
    public void putBullet(final List<EntitiesInfo> entitiesInfo) {
        entitiesInfo.stream().forEach(t -> {
            final Pair<Optional<Position>, Optional<MovementManager>> pair = MovementManagerFactory
                    .getMovementManager(t.getPosition(), t.getMovementInfo());
            this.arena.add(new Entities.Builder().code(t.getCode())
                    .movementManager(pair.getY().isPresent() ? pair.getY().get() : null)
                    .contactDamage(t.getContactDamage().get()).build());
        });
    }

}
