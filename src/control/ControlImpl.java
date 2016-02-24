package control;

import java.util.Iterator;
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
    private final GameSettings gameSettings;
    private Iterator<Levels> levelIterator;

    public ControlImpl(ViewController view) {
        this.inputManager = new InputManagerImpl();
        this.model = ModelImpl.getModel();
        view.setListener(this);
        this.view = new ViewDecoratorImpl(view);
        this.gameSettings = new SettingsLoaderImpl().getGameSettings();
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
            this.view.changeScene(SceneType.MENU);
            break;
        case EXIT:
            System.exit(0);
            break;
        case LEVEL1:
            this.levelIterator = this.gameSettings.getLevelIterator();
            gameLoop(levelIterator.next());
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
        case NEXTLEVEL: 
            if(this.gameThread == null || this.gameThread.isRunning()) {
                throw new IllegalStateException();
            }
            gameLoop(this.levelIterator.next());
            break;
        default:
            inputManager.notifyViewInput(event);
            break;
         
        }
        
    }

    public List<Buttons> getButtons() {
        this.menuLoader = new MenuLoaderImpl(Menu.INITIAL);
        if(this.gameThread == null) {
            this.menuLoader = new MenuLoaderImpl(Menu.INITIAL);
        } else if(this.gameThread.isPaused()) {
            this.menuLoader = new MenuLoaderImpl(Menu.PAUSE);
        } else if (this.gameThread.getGameState() == GameState.WON) {
            if(this.levelIterator.hasNext()){
                this.menuLoader = new MenuLoaderImpl(Menu.WIN);
            } else {
                this.menuLoader = new MenuLoaderImpl(Menu.WINEND);
            }
            
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
