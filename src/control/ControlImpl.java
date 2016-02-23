package control;

import java.util.List;

import model.Model;
import model.ModelImpl;
import view.Notifications;
import view.SceneType;
import view.ViewController;

public class ControlImpl implements Control {
    private final Model model;
    private ViewDecorator view;
    private final InputManager inputManager;
    private GameThread gameThread;
    private MenuLoader menuLoader;

    public ControlImpl(ViewController view) {
        this.inputManager = new InputManagerImpl();
        this.model = ModelImpl.getModel();
        view.setListener(this);
        this.view = new ViewDecoratorImpl(view);
    }

    public void startGame() {
        this.view.changeScene(SceneType.MENU);
    }

    public void notifyEvent(ViewEvents event) {
        
        switch(event) {
        case BACKTOMAINMENU:
            this.gameThread.stopGame();
            if(this.gameThread.isPaused()) {
                this.gameThread.reStart();
            }
            this.menuLoader = new MenuLoaderImpl(Menu.INITIAL);
            this.view.changeScene(SceneType.MENU);
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
                    this.view.notifySceneEvent(Notifications.PLAY);
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
        this.view.setLevelDimension(ll.getLevelInfo().getLevelDimension());
        this.view.changeScene(SceneType.DRAWABLE);

        this.gameThread = new GameThreadImpl(this.model, this.view, database2, this.inputManager);
        gameThread.start();
    }

}
