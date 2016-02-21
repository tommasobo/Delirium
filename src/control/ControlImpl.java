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
import model.LifePattern;
import model.Model;
import model.ModelImpl;
import model.MovementInfo;
import model.MovementTypes;
import model.Position;
import model.ShootInfo;
import model.ShootTypes;
import view.Entities;
import view.SceneType;
import view.ViewController;
import view.ViewControllerImpl;

public class ControlImpl implements Control {
    private final Model model;
    private ViewController view;
    private final InputManager inputManager;
    private GameThread gameThread;

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
            return;
        } else if (event.equals(ViewEvents.PAUSE)) {
            if(gameThread != null) {
                if( this.gameThread.isPaused()) {
                    gameThread.reStart();
                    this.view.resumeGame();
                } else {
                    gameThread.pause();
                    this.view.pauseGame();
                }
            }
            return;
        } 
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
                        new Position(new Point(0, 200), Directions.RIGHT, new Dimension(40, 60)),
                        Optional.of(new MovementInfo(10, new Bounds(0, 1000, 0, 300), Actions.STOP, false,
                                MovementTypes.HERO)),
                        300, LifePattern.WITH_LIFE,
                        Optional.of(new ShootInfo(5, ShootTypes.HERO, 1, MovementTypes.HORIZONTAL_LINEAR, 200, 10)), Optional.of(0)),
                Entities.MAGNO));

        ls.add(database.putEntityAndSetCode(
                (EntitiesInfo) new EntitiesInfoImpl(0,
                        new Position(new Point(300, 100), Directions.RIGHT, new Dimension(50, 70)),
                        Optional.of(new MovementInfo(3, new Bounds(0, 1000, 40, 300), Actions.JUMP, false,
                                MovementTypes.VERTICAL_LINEAR)),
                        10, LifePattern.WITH_LIFE,
                        Optional.empty(), Optional.empty()),
                Entities.VOLPE));
        
        //Optional.of(new ShootInfo(200, 1, MovementTypes.HORIZONTAL_LINEAR, 200, 10))
        
        ls.add(database.putEntityAndSetCode(
                (EntitiesInfo) new EntitiesInfoImpl(0,
                        new Position(new Point(0, 0), Directions.RIGHT, new Dimension(1000, 40)),
                        Optional.empty(),
                        300, LifePattern.WITHOUT_LIFE,
                        Optional.empty(), Optional.empty()),
                Entities.GROUND));
        
        /*ls.add(database.putEntityAndSetCode(
                (EntitiesInfo) new EntitiesInfoImpl(0,
                        new Position(new Point(500, 40), Directions.LEFT, new Dimension(50, 70)),
                        Optional.empty(),
                        300, LifePattern.WITH_LIFE,
                        Optional.of(new ShootInfo(200, 1, MovementTypes.HORIZONTAL_LINEAR, 200, 10)), Optional.empty()),
                Entities.DINO));*/
        
        /*ls.add(database.putEntityAndSetCode(
                (EntitiesInfo) new EntitiesInfoImpl(0,
                        new Position(new Point(700, 40), Directions.RIGHT, new Dimension(70, 40)),
                        Optional.of(new MovementInfo(3, new Bounds(700, 1000, 40, 300), Actions.MOVE, false,
                                MovementTypes.HORIZONTAL_LINEAR)),
                        300, LifePattern.WITH_LIFE,
                        Optional.of(new ShootInfo(200, 1, MovementTypes.HORIZONTAL_LINEAR, 200, 10)), Optional.empty()),
                Entities.BUG));*/
        
        /*ls.add(database.putEntityAndSetCode(
                (EntitiesInfo) new EntitiesInfoImpl(0,
                        new Position(new Point(600, 200), Directions.RIGHT, new Dimension(100, 20)),
                        Optional.of(new MovementInfo(3, new Bounds(500, 1000, 100, 300), Actions.MOVE, false,
                                MovementTypes.RANDOM)),
                        300, LifePattern.WITHOUT_LIFE,
                        Optional.empty(), Optional.empty()),
                Entities.PLATFORM));*/
        

        Dimension arenaDim = new Dimension(1000, 300);
        database.putArenaDimension(arenaDim);
        this.model.createArena(ls);
        this.view.changeScene(new Pair<SceneType, Dimension2D>(SceneType.DRAWABLE, new Dimension2D(1000, 300)));

        this.gameThread = new GameThreadImpl(this.model, this.view, database, this.inputManager);
        gameThread.start();
    }

}
