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
        if(this.gameThread == null) {
            this.menuLoader = new MenuLoaderImpl(Menu.INITIAL);
        } else
        
        if(this.gameThread.getGameState() == GameState.PAUSED) {
            this.menuLoader = new MenuLoaderImpl(Menu.PAUSE);
        } else if (this.gameThread.getGameState() == GameState.WON) {
            this.menuLoader = new MenuLoaderImpl(Menu.WIN);
        } else if (this.gameThread.getGameState() == GameState.LOSE) {
            this.menuLoader = new MenuLoaderImpl(Menu.LOSE);
        } else if(!this.gameThread.isRunning()) {
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
