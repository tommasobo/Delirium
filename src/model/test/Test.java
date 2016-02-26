package model.test;

import static org.junit.Assert.assertEquals;
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

public class Test {

    Model modelTest = ModelImpl.getModel();
    EntitiesInfo hero, goal, monster;
    Position heroPosition, monsterPosition;
    MovementInfo heroMovementInfo, monsterMovementInfo;
    ShootInfo heroShootInfo;
    List<EntitiesInfo> entities;

    private void istanceEntities() {
        this.heroPosition = new Position(new Point(0, 0), Directions.RIGHT, new Dimension(40, 60));
        this.heroMovementInfo = new MovementInfoImpl(5, new Bounds(0, 200, 0, 200), Actions.STOP, false,
                MovementTypes.HERO);
        this.heroShootInfo = new ShootInfoImpl(10, ShootTypes.HERO, 10, MovementTypes.HORIZONTAL_LINEAR, 200, 3);
        this.hero = new EntitiesInfoImpl(0, heroPosition, Optional.of(heroMovementInfo), 30, LifePattern.WITH_LIFE,
                Optional.of(heroShootInfo), Optional.of(0));
        this.entities = new LinkedList<>(Arrays.asList(this.hero));
        this.goal = new EntitiesInfoImpl(-1, new Position(new Point(100, 100), Directions.LEFT, new Dimension(40, 60)),
                Optional.empty(), 1, LifePattern.WITHOUT_LIFE, Optional.empty(), Optional.empty());
        this.entities.add(this.goal);
        this.monsterPosition = new Position(new Point(150, 150), Directions.RIGHT, new Dimension(20, 20));
        this.monsterMovementInfo = new MovementInfoImpl(4, new Bounds(100, 200, 100, 200), Actions.MOVE, true,
                MovementTypes.HORIZONTAL_LINEAR);
        this.monster = new EntitiesInfoImpl(2, monsterPosition, Optional.of(monsterMovementInfo), 5,
                LifePattern.WITH_LIFE, Optional.empty(), Optional.empty());
        this.entities.add(this.monster);

    }

    @org.junit.Test
    public void testOK1() {

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

    // Test di eccezioni
    @org.junit.Test
    public void testEXC() {

        this.istanceEntities();
        this.monsterPosition.setPoint(new Point(this.monsterPosition.getPoint().getX(), 0));

        try {
            this.modelTest.createArena(this.entities);
            fail("The control of the bounds is wrong");
        } catch (IllegalMonsterBoundsException e) {
            System.out.println("The control of the bounds is right");
        }

        EntitiesInfo otherMonster = new EntitiesInfoImpl(2, monsterPosition, Optional.of(monsterMovementInfo), 5,
                LifePattern.WITH_LIFE, Optional.empty(), Optional.empty());
        this.entities.add(otherMonster);

        try {
            this.modelTest.createArena(this.entities);
            fail("The control of the unique code is wrong");
        } catch (NotUniqueCodeException e) {
            System.out.println("The control of the unique code is right");
        }
    }

}
