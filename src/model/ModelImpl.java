package model;

import java.util.HashSet;
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
import model.exception.IllegalMonsterBoundsException;
import model.exception.NotUniqueCodeException;
import model.transfertentities.EntitiesInfo;
import model.transfertentities.EntitiesInfoToControl;
import model.transfertentities.EntitiesInfoToControlImpl;
import utility.Pair;

/**
 * This is the implementation of the interface model. This class use the
 * Singleton pattern because the control have to generate only one model.
 * 
 * @author josephgiovanelli
 *
 */
public final class ModelImpl implements Model {

    private static final int DEFAULT_OFFSET_X = 500;
    private static final ModelImpl SINGLETON = new ModelImpl();
    private Arena arena;
    private ArenaManager arenaManager;

    private ModelImpl() {
    }

    /**
     * This method return the singleton, the instance of model.
     * 
     * @return : the Model that can used by the control.
     */
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

    @Override
    public List<EntitiesInfo> updateArena() {

        this.removeEntities(this.arena.getEntities());
        this.removeEntities(this.arena.getBullets());

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
    public void createArena(final List<EntitiesInfo> entitiesInfo)
            throws NotUniqueCodeException, IllegalMonsterBoundsException {

        this.arena = new ArenaImpl();
        this.arenaManager = new ArenaManagerImpl(this.arena);

        this.checkCodes(entitiesInfo);
        this.checkMonsterBounds(entitiesInfo);

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
        
        long nHero = this.arena.getEntities().stream().filter(t -> t.getCode() == 0).count();
        long nGoal = this.arena.getEntities().stream().filter(t -> t.getCode() == -1).count();
        
        if (nHero < 1 || nGoal < 1) {
            throw new IllegalStateException("Hero or Goal not present");
        }

    }

    @Override
    public void putBullet(final List<EntitiesInfo> entitiesInfo)
            throws NotUniqueCodeException, IllegalMonsterBoundsException {

        this.checkCodes(entitiesInfo);
        this.checkMonsterBounds(entitiesInfo);

        entitiesInfo.stream().forEach(t -> {
            final Pair<Optional<Position>, Optional<MovementManager>> pair = MovementManagerFactory
                    .getMovementManager(t.getPosition(), t.getMovementInfo());
            this.arena.add(new Entities.Builder().code(t.getCode())
                    .movementManager(pair.getY().isPresent() ? pair.getY().get() : null)
                    .contactDamage(t.getContactDamage().get()).build());
        });
    }

    private void checkCodes(final List<EntitiesInfo> entitiesInfo) throws NotUniqueCodeException {
        if (!entitiesInfo.stream().map(t -> t.getCode()).allMatch(new HashSet<>()::add)) {
            throw new NotUniqueCodeException();
        }
    }

    private void checkMonsterBounds(final List<EntitiesInfo> entitiesInfo) throws IllegalMonsterBoundsException {
        for (final EntitiesInfo t : entitiesInfo) {
            if (t.getMovementInfo().isPresent()
                    && (t.getPosition().getPoint().getX() > t.getMovementInfo().get().getBounds().getMaxX()
                            || t.getPosition().getPoint().getX() < t.getMovementInfo().get().getBounds().getMinX()
                            || t.getPosition().getPoint().getY() > t.getMovementInfo().get().getBounds().getMaxY()
                            || t.getPosition().getPoint().getY() < t.getMovementInfo().get().getBounds().getMinY())) {
                throw new IllegalMonsterBoundsException();
            }
        }
    }

    private void removeEntities(final List< ? extends Entities> entities) {
        int size = entities.size();
        for (int i = 0; i < size; i++) {
            if (entities.get(i).getLifeManager().getLife() == 0) {
                entities.remove(i);
                i--;
                size--;
            }
        }
    }

}
