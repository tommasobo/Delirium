package model.arena.entities;

import java.util.Optional;

import model.arena.entities.life.LifeManager;
import model.arena.entities.movement.MovementManager;
import model.arena.entities.shoot.ShootManager;
import model.arena.utility.Actions;
import model.arena.utility.Directions;

/**
 * This interface describes any entities that are in the @Arena. Each entity is
 * different from another on the basis of its fields which determine his status.
 * Here is used the composition of some component that are builded with the
 * Strategy Pattern. An entity can has or not a specific field. In this idea we
 * can have static entities that can shoot like tower and dynamic entities that
 * cannot shoot like platform or a ground that can hurt at the contact.
 * 
 * @author josephgiovanelli
 *
 */
public interface Entities {

    /**
     * Each entities in unique so has a unique code.
     * 
     * @return : the unique code that you can ask.
     */
    int getCode();

    /**
     * Each entities has a position otherwise it isn't in the arena.
     * 
     * @return : the position that ypu ask.
     */
    Position getPosition();

    /**
     * The method that allow you to change the position of the monster in next
     * time or in a collision.
     * 
     * @param point
     *            : the position consist of a point.
     * @param direction
     *            : the position consist of a direction.
     */
    void setPosition(final Point point, final Directions direction);

    /**
     * Any entity has a manager of a life. Some has a life and then dead but
     * some are immortal like the ground.
     * 
     * @return : the LifeManager that manage the life.
     */
    LifeManager getLifeManager();

    /**
     * An entity can be static or dynamic. If is static this field is empty,
     * otherwise is a @MovementManager that use the Strategy Pattern. In this
     * architecture the Inheritance is moved in the fields of the entities.
     * 
     * @return : the MovementManager that you ask.
     */
    Optional<MovementManager> getMovementManager();

    /**
     * An entity can be firing or not. If is not firing this field is empty,
     * otherwise is a @ShootManager that use the Strategy Pattern. In this
     * architecture the Inheritance is moved in the fields of the entities.
     * 
     * @return : the ShootManager that you ask.
     */
    Optional<ShootManager> getShootManager();

    /**
     * An entity can be dangerous or not. If is not dangerous this field is
     * empty, otherwise there is a number that determinate the contact damage.
     * 
     * @return : the contact damage that you ask.
     */
    Optional<Integer> getContactDamage();

    /**
     * The entities has an action in a determinate moment. They can be in @STOP
     * for an example or in @MOVE.
     * 
     * @return : the action that you ask
     */
    Actions getAction();

    /**
     * You can set the action with this method.
     * 
     * @param action
     *            : this value is the action that you want to set.
     */
    void setAction(final Actions action);

    /**
     * This method is override in any implementation of this interface in order
     * to determinate in the entity the instance of the her.
     * 
     * @param visitor
     *            : the @EntitiesVisitor that can call @add method in order to
     *            put the entity in the right field
     */
    void accept(final EntitiesVisitor visitor);

    /**
     * This tool allow you to create an entity like a "stream" and do the right
     * control in order to instance the right entity that you want and call the
     * right constructor of the implementation of Entities.
     * 
     * @author josephgiovanelli
     *
     */
    class Builder {
        private Optional<Integer> code = Optional.empty();
        private Optional<Position> position = Optional.empty();
        private LifeManager lifeManager;
        private Optional<MovementManager> movementManager = Optional.empty();
        private Optional<ShootManager> shootManager = Optional.empty();
        private Optional<Integer> contactDamage = Optional.empty();

        public Builder code(final int code) {
            this.code = Optional.of(code);
            return this;
        }

        public Builder position(final Position position) {
            this.position = Optional.ofNullable(position);
            return this;
        }

        public Builder lifeManager(final LifeManager lifeManager) {
            this.lifeManager = lifeManager;
            return this;
        }

        public Builder movementManager(final MovementManager movementManager) {
            this.movementManager = Optional.ofNullable(movementManager);
            return this;
        }

        public Builder shootManager(final ShootManager shootManager) {
            this.shootManager = Optional.ofNullable(shootManager);
            return this;
        }

        public Builder contactDamage(final Integer contactDamage) {
            this.contactDamage = Optional.ofNullable(contactDamage);
            return this;
        }

        public Entities build() throws IllegalStateException {

            if (this.code.isPresent() && this.lifeManager == null && !this.position.isPresent()
                    && this.movementManager.isPresent() && !this.shootManager.isPresent()
                    && this.contactDamage.isPresent()) {
                return new Bullet(this.code.get(), this.movementManager.get(), this.contactDamage.get());
            }

            if (this.code.isPresent() && this.code.get() == 0 && this.lifeManager != null && !this.position.isPresent()
                    && this.movementManager.isPresent() && this.shootManager.isPresent()
                    && this.contactDamage.isPresent()) {
                return new HeroImpl(this.code.get(), this.lifeManager, this.movementManager.get(),
                        this.shootManager.get(), this.contactDamage.get());
            }

            if (!this.code.isPresent() || this.lifeManager == null
                    || (!this.position.isPresent() && !this.movementManager.isPresent())
                    || (this.position.isPresent() && this.movementManager.isPresent())) {
                throw new IllegalStateException();
            }

            if (this.position.isPresent()) {
                return new EntitiesImpl(this.code.get(), this.lifeManager, this.position.get(), this.shootManager,
                        this.contactDamage);
            } else {
                return new EntitiesImpl(this.code.get(), this.lifeManager, this.movementManager.get(),
                        this.shootManager, this.contactDamage);
            }

        }

    }

}
