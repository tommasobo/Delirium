package model.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import model.Model;
import model.ModelImpl;
import model.arena.entities.Point;
import model.arena.entities.Position;
import model.arena.entities.life.LifePattern;
import model.arena.entities.movement.AbstractDinamicMovementManager;
import model.arena.utility.Actions;
import model.arena.utility.Bounds;
import model.arena.utility.Directions;
import model.arena.utility.MovementTypes;
import model.exception.IllegalMonsterBoundsException;
import model.exception.NotUniqueCodeException;
import model.transfertentities.EntitiesInfo;
import model.transfertentities.EntitiesInfoImpl;
import model.transfertentities.EntitiesInfoToControl;
import model.transfertentities.MovementInfo;
import model.transfertentities.MovementInfoImpl;
import model.transfertentities.ShootInfo;
import model.transfertentities.ShootInfoImpl;
import model.transfertentities.ShootTypes;
import utility.Dimension;
import utility.Pair;

/**
 * This test check the basic and fundamental behaviour of the model.
 * 
 * @author josephgiovanelli
 *
 */
public class Test {

    private Model modelTest = ModelImpl.getModel();
    private EntitiesInfo hero, goal, monster, ground;
    private Position heroPosition, monsterPosition, groundPosition;
    private MovementInfo heroMovementInfo, monsterMovementInfo;
    private ShootInfo heroShootInfo;
    private List<EntitiesInfo> entities;

    private void istanceEntities() {
        this.heroPosition = new Position(new Point(0, 0), Directions.RIGHT, new Pair<>(new Dimension(40, 60), new Dimension(40, 30)));
        this.heroMovementInfo = new MovementInfoImpl(5, new Bounds(0, 200, 0, 200), Actions.STOP, false,
                MovementTypes.HERO);
        this.heroShootInfo = new ShootInfoImpl(10, ShootTypes.HERO, 10, MovementTypes.HORIZONTAL_LINEAR, 200, 3);
        this.hero = new EntitiesInfoImpl(0, heroPosition, Optional.of(heroMovementInfo), 30, LifePattern.WITH_LIFE,
                Optional.of(heroShootInfo), Optional.of(0));
        this.entities = new LinkedList<>(Arrays.asList(this.hero));
        this.goal = new EntitiesInfoImpl(-1, new Position(new Point(100, 100), Directions.LEFT, new Pair<>(new Dimension(40, 60), new Dimension(40, 30))),
                Optional.empty(), 1, LifePattern.WITHOUT_LIFE, Optional.empty(), Optional.empty());
        this.entities.add(this.goal);
        this.monsterPosition = new Position(new Point(150, 150), Directions.RIGHT, new Pair<>(new Dimension(40, 60), new Dimension(40, 30)));
        this.monsterMovementInfo = new MovementInfoImpl(4, new Bounds(100, 200, 100, 200), Actions.MOVE, true,
                MovementTypes.HORIZONTAL_LINEAR);
        this.monster = new EntitiesInfoImpl(2, monsterPosition, Optional.of(monsterMovementInfo), 5,
                LifePattern.WITH_LIFE, Optional.empty(), Optional.empty());
        this.entities.add(this.monster);

    }

    private void istanceEntitiesForCollisions() {
        this.heroPosition = new Position(new Point(0, 50), Directions.RIGHT, new Pair<>(new Dimension(40, 60), new Dimension(40, 30)));
        this.heroMovementInfo = new MovementInfoImpl(7, new Bounds(0, 200, 0, 200), Actions.STOP, false,
                MovementTypes.HERO);
        this.heroShootInfo = new ShootInfoImpl(10, ShootTypes.HERO, 10, MovementTypes.HORIZONTAL_LINEAR, 200, 3);
        this.hero = new EntitiesInfoImpl(0, heroPosition, Optional.of(heroMovementInfo), 30, LifePattern.WITH_LIFE,
                Optional.of(heroShootInfo), Optional.of(0));
        this.entities = new LinkedList<>(Arrays.asList(this.hero));
        this.groundPosition = new Position(new Point(0, 0), Directions.NONE, new Pair<>(new Dimension(40, 60), new Dimension(40, 30)));
        this.ground = new EntitiesInfoImpl(1, this.groundPosition, Optional.empty(), 1, LifePattern.WITHOUT_LIFE,
                Optional.empty(), Optional.empty());
        this.entities.add(this.ground);
        this.goal = new EntitiesInfoImpl(-1, new Position(new Point(100, 100), Directions.LEFT, new Pair<>(new Dimension(40, 60), new Dimension(40, 30))),
                Optional.empty(), 1, LifePattern.WITHOUT_LIFE, Optional.empty(), Optional.empty());
        this.entities.add(this.goal);
        this.monsterPosition = new Position(new Point(60, 40), Directions.LEFT, new Pair<>(new Dimension(40, 60), new Dimension(40, 30)));
        this.monsterMovementInfo = new MovementInfoImpl(5, new Bounds(0, 200, 0, 200), Actions.MOVE, true,
                MovementTypes.HORIZONTAL_LINEAR);
        this.monster = new EntitiesInfoImpl(2, monsterPosition, Optional.of(monsterMovementInfo), 5,
                LifePattern.WITH_LIFE, Optional.empty(), Optional.empty());
        this.entities.add(this.monster);

    }

    private void istanceEntitiesForReactiveMovement() {
        this.heroPosition = new Position(new Point(0, 60), Directions.RIGHT, new Pair<>(new Dimension(40, 60), new Dimension(40, 30)));
        this.heroMovementInfo = new MovementInfoImpl(7, new Bounds(0, 200, 0, 200), Actions.STOP, false,
                MovementTypes.HERO);
        this.heroShootInfo = new ShootInfoImpl(10, ShootTypes.HERO, 10, MovementTypes.HORIZONTAL_LINEAR, 200, 3);
        this.hero = new EntitiesInfoImpl(0, heroPosition, Optional.of(heroMovementInfo), 30, LifePattern.WITH_LIFE,
                Optional.of(heroShootInfo), Optional.of(0));
        this.entities = new LinkedList<>(Arrays.asList(this.hero));
        this.groundPosition = new Position(new Point(0, 0), Directions.NONE, new Pair<>(new Dimension(40, 60), new Dimension(40, 30)));
        this.ground = new EntitiesInfoImpl(1, this.groundPosition, Optional.empty(), 1, LifePattern.WITHOUT_LIFE,
                Optional.empty(), Optional.empty());
        this.entities.add(this.ground);
        this.goal = new EntitiesInfoImpl(-1, new Position(new Point(100, 100), Directions.LEFT, new Pair<>(new Dimension(40, 60), new Dimension(40, 30))),
                Optional.empty(), 1, LifePattern.WITHOUT_LIFE, Optional.empty(), Optional.empty());
        this.entities.add(this.goal);
        this.monsterPosition = new Position(new Point(0, 40), Directions.RIGHT, new Pair<>(new Dimension(40, 60), new Dimension(40, 30)));
        this.monsterMovementInfo = new MovementInfoImpl(1, new Bounds(0, 200, 0, 200), Actions.MOVE, true,
                MovementTypes.HORIZONTAL_LINEAR);
        this.monster = new EntitiesInfoImpl(2, monsterPosition, Optional.of(monsterMovementInfo), 5,
                LifePattern.WITH_LIFE, Optional.empty(), Optional.empty());
        this.entities.add(this.monster);

    }

    /**
     * This test test movement of the entities.
     */
    @org.junit.Test
    public void movementTest() {

        this.istanceEntities();

        this.modelTest.createArena(entities);
        this.modelTest.notifyEvent(Actions.MOVE);
        this.modelTest.updateArena();
        List<EntitiesInfoToControl> answer = this.modelTest.getState();

        Point heroPoint = Actions.MOVE.apply(this.heroPosition.getPoint(), this.heroMovementInfo.getSpeed(),
                this.heroPosition.getDirection());
        Position newHeroPosition = answer.stream().filter(t -> t.getCode() == 0).map(t -> t.getPosition()).findAny()
                .get();
        Point monsterPoint = Actions.MOVE.apply(this.monsterPosition.getPoint(), this.monsterMovementInfo.getSpeed(),
                this.monsterPosition.getDirection());
        Position newMonsterPosition = answer.stream().filter(t -> t.getCode() == 2).map(t -> t.getPosition()).findAny()
                .get();

        assertEquals(newHeroPosition.getPoint(), heroPoint);
        System.out.println("The hero move right to: " + heroPoint);
        assertEquals(newMonsterPosition.getPoint(), monsterPoint);
        System.out.println("The monster move right to: " + monsterPoint);
        this.modelTest.notifyEvent(Directions.LEFT);
        this.modelTest.notifyEvent(Actions.MOVE);
        this.modelTest.updateArena();
        newHeroPosition = this.modelTest.getState().stream().filter(t -> t.getCode() == 0).map(t -> t.getPosition())
                .findAny().get();
        heroPoint = Actions.MOVE.apply(heroPoint, heroMovementInfo.getSpeed(), Directions.LEFT);

        assertEquals(newHeroPosition.getPoint(), heroPoint);

        System.out.println("The hero move right to: " + heroPoint);

    }

    @org.junit.Test
    public void collisionAndGravityTest() {

        System.out.println("");
        System.out.println("----------------------------------------------------------");
        System.out.println("");
        System.out.println("Collision and Gravity test begin");
        System.out.println("");
        this.istanceEntitiesForCollisions();

        this.modelTest.createArena(entities);
        Position oldHeroPosition = this.heroPosition;
        this.modelTest.notifyEvent(Actions.STOP);
        this.modelTest.updateArena();
        List<EntitiesInfoToControl> answer = this.modelTest.getState();
        Position newHeroPosition = answer.stream().filter(t -> t.getCode() == 0).map(t -> t.getPosition()).findAny()
                .get();
        assertEquals(newHeroPosition.getPoint().getY(),
                oldHeroPosition.getPoint().getY() - AbstractDinamicMovementManager.GRAVITY);
        System.out.println("Gravity works and send hero to y " + newHeroPosition.getPoint().getY() + "from y "
                + oldHeroPosition.getPoint().getY());
        oldHeroPosition = newHeroPosition;
        this.modelTest.updateArena();
        answer = this.modelTest.getState();
        newHeroPosition = answer.stream().filter(t -> t.getCode() == 0).map(t -> t.getPosition()).findAny().get();
        assertTrue(newHeroPosition.getPoint().getY() != oldHeroPosition.getPoint().getY()
                - AbstractDinamicMovementManager.GRAVITY);
        assertEquals(newHeroPosition.getPoint().getY(),
                this.groundPosition.getPoint().getY() + this.groundPosition.getDimension().getX().getHeight());
        System.out.println("Collisions work and set Hero's y position on the top of the ground, and not under (1)");

        oldHeroPosition = newHeroPosition;
        this.modelTest.updateArena();
        answer = this.modelTest.getState();
        newHeroPosition = answer.stream().filter(t -> t.getCode() == 0).map(t -> t.getPosition()).findAny().get();
        assertTrue(newHeroPosition.getPoint().getY() != oldHeroPosition.getPoint().getY()
                - AbstractDinamicMovementManager.GRAVITY);
        assertTrue(newHeroPosition.getPoint().getY() == oldHeroPosition.getPoint().getY());
        assertEquals(newHeroPosition.getPoint().getY(),
                this.groundPosition.getPoint().getY() + this.groundPosition.getDimension().getX().getHeight());
        System.out.println("Collisions work and set Hero's y position on the top of the ground, and not under (2)");

        oldHeroPosition = newHeroPosition;
        this.modelTest.updateArena();
        answer = this.modelTest.getState();
        newHeroPosition = answer.stream().filter(t -> t.getCode() == 0).map(t -> t.getPosition()).findAny().get();
        assertTrue(newHeroPosition.getPoint().getY() != oldHeroPosition.getPoint().getY()
                - AbstractDinamicMovementManager.GRAVITY);
        assertTrue(newHeroPosition.getPoint().getY() == oldHeroPosition.getPoint().getY());
        assertEquals(newHeroPosition.getPoint().getY(),
                this.groundPosition.getPoint().getY() + this.groundPosition.getDimension().getX().getHeight());
        System.out.println("Collisions work and set Hero's y position on the top of the ground, and not under (3)");

        Position oldMonsterPosition = answer.stream().filter(t -> t.getCode() == 2).map(t -> t.getPosition()).findAny()
                .get();
        this.modelTest.notifyEvent(Actions.MOVE);
        oldHeroPosition = newHeroPosition;
        this.modelTest.updateArena();
        this.modelTest.notifyEvent(Actions.STOP);
        answer = this.modelTest.getState();
        newHeroPosition = answer.stream().filter(t -> t.getCode() == 0).map(t -> t.getPosition()).findAny().get();
        Position newMonsterPosition = answer.stream().filter(t -> t.getCode() == 2).map(t -> t.getPosition()).findAny()
                .get();
        assertEquals(newHeroPosition.getPoint().getX(), oldHeroPosition.getPoint().getX());
        assertEquals(newHeroPosition.getPoint().getX() + newHeroPosition.getDimension().getX().getWidth(),
                newMonsterPosition.getPoint().getX());
        System.out.println("Collisions work, hero cannot move because have an entity on left. Hero point: "
                + newHeroPosition.getPoint() + " whith with: " + newHeroPosition.getDimension().getX().getWidth()
                + "    Other entity point:" + newMonsterPosition.getPoint());
        assertTrue(newMonsterPosition.getDirection() != oldMonsterPosition.getDirection());
        assertEquals(newMonsterPosition.getDirection(), Directions.RIGHT);
        System.out.println("The monster, after collision, has changed his direction from: "
                + oldMonsterPosition.getDirection() + " to " + newMonsterPosition.getDirection());

        istanceEntitiesForReactiveMovement();
        this.modelTest.createArena(entities);
        oldHeroPosition = this.heroPosition;
        this.modelTest.updateArena();
        answer = this.modelTest.getState();
        newHeroPosition = answer.stream().filter(t -> t.getCode() == 0).map(t -> t.getPosition()).findAny().get();
        this.modelTest.updateArena();
        answer = this.modelTest.getState();
        oldHeroPosition = newHeroPosition;
        newHeroPosition = answer.stream().filter(t -> t.getCode() == 0).map(t -> t.getPosition()).findAny().get();
        assertEquals(newHeroPosition.getPoint().getX(),
                oldHeroPosition.getPoint().getX() + this.monsterMovementInfo.getSpeed());
        System.out.println("The hero is on entity and moves with it");

        System.out.println("");
        System.out.println("Collision and Gravity test end");
        System.out.println("");
        System.out.println("----------------------------------------------------------");
        System.out.println("");

    }

    /**
     * This test check the exception of the model.
     */
    @org.junit.Test
    public void testEXC() {

        this.istanceEntities();
        Point tempMonsterPoint = this.monsterPosition.getPoint();
        this.monsterPosition.setPoint(new Point(this.monsterPosition.getPoint().getX(), 0));

        try {
            this.modelTest.createArena(this.entities);
            fail("The control of the bounds is wrong");
        } catch (IllegalMonsterBoundsException e) {
            System.out.println("The control of the bounds is right");
        }

        this.monsterPosition.setPoint(tempMonsterPoint);
        EntitiesInfo otherMonster = new EntitiesInfoImpl(2, monsterPosition, Optional.of(monsterMovementInfo), 5,
                LifePattern.WITH_LIFE, Optional.empty(), Optional.empty());
        this.entities.add(otherMonster);

        try {
            this.modelTest.createArena(this.entities);
            fail("The control of the unique code is wrong");
        } catch (NotUniqueCodeException e) {
            System.out.println("The control of the unique code is right");
        }
        this.entities.remove(otherMonster);
        this.entities.remove(this.hero);
        try {
            this.modelTest.createArena(this.entities);
            fail("Hero have to be present");
        } catch (IllegalStateException  e) {
            System.out.println("The control of Hero present is right");
        }

        this.entities.add(this.hero);
        this.entities.remove(this.goal);
        try {
            this.modelTest.createArena(this.entities);
            fail("Goal have to be present");
        } catch (IllegalStateException  e) {
            System.out.println("The control of Goal present is right");
        }
    }

}
