package control;

import java.util.LinkedList;
import java.util.List;

import javafx.geometry.Dimension2D;
import model.Actions;
import model.Bounds;
import model.Directions;
import model.EntitiesInfo;
import model.EntitiesInfoImpl;
import model.LifeManager;
import model.Model;
import model.ModelImpl;
import model.MovementTypes;
import model.Position;
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

        ls.add(database.putEntityAndSetCode((EntitiesInfo) new EntitiesInfoImpl(0, 60, null, MovementTypes.HERO,
                new Position(new Point(0, 240), Directions.RIGHT, new Dimension(40, 60)), new Bounds(0, 1000, 20, 300),
                Actions.STOP, 10, false, 0), Entities.BOCC));
        
        ls.add(database.putEntityAndSetCode((EntitiesInfo) new EntitiesInfoImpl(0, 30, null, MovementTypes.RANDOM,
                new Position(new Point(150, 200), Directions.RIGHT, new Dimension(100, 20)),
                new Bounds(150, 300, 50, 300), Actions.MOVE, 1, false, 0), Entities.PLATFORM));

        ls.add(database.putEntityAndSetCode((EntitiesInfo) new EntitiesInfoImpl(0, 30, null, MovementTypes.HORIZONTAL_LINEAR,
                new Position(new Point(500, 30), Directions.RIGHT, new Dimension(100, 50)),
                new Bounds(0, 1000, 0, 300), Actions.MOVE, 1, true, 1), Entities.BUG));

        ls.add(database.putEntityAndSetCode((EntitiesInfo)new EntitiesInfoImpl(0, 30, null, MovementTypes.VERTICAL_LINEAR,
                new Position(new Point(500, 100), Directions.RIGHT, new Dimension(65, 100)),
                new Bounds(0, 1000, 0, 300), Actions.JUMP, 1, true, 0), Entities.VOLPE));

        ls.add(database.putEntityAndSetCode((EntitiesInfo)new EntitiesInfoImpl(0, 30, null, MovementTypes.STATIC,
                new Position(new Point(0, 0), Directions.NONE, new Dimension(1000, 20)), new Bounds(0, 1000, 10, 300),
                Actions.STOP, 1, true, 0), Entities.GROUND));

        Dimension arenaDim = new Dimension(1000, 300);
        database.putArenaDimension(arenaDim);
        this.model.createArena(ls);
        this.view.changeScene(new Pair<SceneType, Dimension2D>(SceneType.DRAWABLE, new Dimension2D(1000, 300)));

        GameThread t = new GameThreadImpl(this.model, this.view, database, this.inputManager);
        t.start();
    }

}
