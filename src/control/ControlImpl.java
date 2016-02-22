package control;

import java.util.List;

import javafx.geometry.Dimension2D;
import model.Model;
import model.ModelImpl;
import view.Notifications;
import view.SceneType;
import view.ViewController;
import view.ViewControllerImpl;

public class ControlImpl implements Control {
    private final Model model;
    private ViewController view;
    private final InputManager inputManager;
    private GameThread gameThread;
    private MenuLoader menuLoader;

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
        
        switch(event) {
        case BACKTOMAINMENU:
            this.gameThread.stopGame();
            if(this.gameThread.isPaused()) {
                this.gameThread.reStart();
            }
            this.menuLoader = new MenuLoaderImpl(Menu.INITIAL);
            this.view.changeScene(new Pair<SceneType, Dimension2D>(SceneType.MENU, new Dimension2D(1000, 300)));
            break;
        case EXIT:
            System.exit(0);
            break;
        case LEVEL1:
            gameLoop(Levels.LEVEL1);
            break;
        case PAUSE:
            this.menuLoader = new MenuLoaderImpl(Menu.PAUSE);
            if(gameThread != null) {
                if( this.gameThread.isPaused()) {
                    gameThread.reStart();
                    this.view.notifySceneEvent(Notifications.PLAY);;
                } else {
                    gameThread.pause();
                    this.view.notifySceneEvent(Notifications.PAUSE);
                }
            }
            break;
        default:
            inputManager.notifyViewInput(event);
            break;
         
        }
        
    }

    public List<Buttons> getButtons() {
        if(this.menuLoader == null) {
            this.menuLoader = new MenuLoaderImpl(Menu.INITIAL);
        }
        return this.menuLoader.getButtonsList();
    }

    private void gameLoop(Levels level) {
        
        LevelLoaderImpl ll = new LevelLoaderImpl(level);
        EntitiesDatabase database2 = ll.getDatabase();
        this.model.createArena(ll.getEntities());
        this.view.changeScene(new Pair<SceneType, Dimension2D>(SceneType.DRAWABLE, new Dimension2D(1000, 300)));

        this.gameThread = new GameThreadImpl(this.model, this.view, database2, this.inputManager);
        gameThread.start();
    }

}
