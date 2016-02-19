package control;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import javafx.geometry.Dimension2D;
import model.Actions;
import model.Bounds;
import model.Directions;
import model.EntitiesInfo;
import model.EntitiesInfoImpl;
import model.LifeManager;
import model.LifePattern;
import model.Model;
import model.ModelImpl;
import model.MovementInfo;
import model.MovementTypes;
import model.Position;
import model.ShootInfo;
import view.Entities;
import view.SceneType;
import view.ViewController;
import view.ViewControllerImpl;

public class ControlImpl implements Control {
    private final Model model;
    private ViewController view;
    private final InputManager inputManager;

    public ControlImpl() {
        this.inputManager = new InputManagerImpl();
        this.model = ModelImpl.getModel();
        this.view = ViewControllerImpl.getView();
        this.view.setListener(this);
    }

    public void startGame() {
        this.view.changeScene(new Pair<SceneType, Dimension2D>(SceneType.MENU, new Dimension2D(1000, 300)));
    }

    public void notifyEvent(ViewEvents event) {
        
        if (event.equals(ViewEvents.LEVEL1)) {
            gameLoop(ViewEvents.LEVEL1);
            return;
        } else if (event.equals(ViewEvents.EXIT)) {
            System.exit(0);
        } /*else if (event.equals(ViewEvents.PAUSE)) {
            
        }*/
        
        inputManager.notifyViewInput(event);

        
    }

    public List<Buttons> getButtons() {
        List<Buttons> list = new LinkedList<>();
        list.add(Buttons.NEWGAME);
        list.add(Buttons.EXIT);
        return list;
    }

    private void gameLoop(ViewEvents level) {
        // codice lettura file e put dei mostri, creazione database
        EntitiesDatabase database = new EntitiesDatabaseImpl();

        List<EntitiesInfo> ls = new LinkedList<>();

        ls.add(database.putEntityAndSetCode(
                (EntitiesInfo) new EntitiesInfoImpl(0,
                        new Position(new Point(0, 200), Directions.RIGHT, new Dimension(180, 120)),
                        Optional.of(new MovementInfo(10, new Bounds(0, 3000, 0, 900), Actions.STOP, false,
                                MovementTypes.HERO)),
                        300, LifePattern.WITH_LIFE,
                        Optional.of(new ShootInfo(5, 1, MovementTypes.HORIZONTAL_LINEAR, 200, 10)), Optional.of(0)),
                Entities.MAGNO));

        ls.add(database.putEntityAndSetCode(
                (EntitiesInfo) new EntitiesInfoImpl(0,
                        new Position(new Point(1000, 200), Directions.RIGHT, new Dimension(180, 120)),
                        Optional.of(new MovementInfo(10, new Bounds(0, 3000, 0, 900), Actions.JUMP, false,
                                MovementTypes.VERTICAL_LINEAR)),
                        300, LifePattern.WITH_LIFE,
                        Optional.of(new ShootInfo(500, 1, MovementTypes.HORIZONTAL_LINEAR, 200, 10)), Optional.empty()),
                Entities.VOLPE));
        
        ls.add(database.putEntityAndSetCode(
                (EntitiesInfo) new EntitiesInfoImpl(0,
                        new Position(new Point(0, 0), Directions.RIGHT, new Dimension(3000, 40)),
                        Optional.empty(),
                        300, LifePattern.WITHOUT_LIFE,
                        Optional.empty(), Optional.empty()),
                Entities.GROUND));

        Dimension arenaDim = new Dimension(3000, 900);
        database.putArenaDimension(arenaDim);
        this.model.createArena(ls);
        this.view.changeScene(new Pair<SceneType, Dimension2D>(SceneType.DRAWABLE, new Dimension2D(3000, 900)));

        GameThread t = new GameThreadImpl(this.model, this.view, database, this.inputManager);
        t.start();
    }

}
