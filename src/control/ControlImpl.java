package control;

import java.util.Iterator;
import java.util.Map;

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
    private Iterator<Levels> levelsIterator;
    private Menu menuToLoad;

    public ControlImpl(ViewController view) {
        this.inputManager = new InputManagerImpl();
        this.model = ModelImpl.getModel();
        view.setListener(this);
        this.view = new ViewDecoratorImpl(view);
        this.gameSettings = new SettingsLoaderImpl().getGameSettings();
        this.menuToLoad = Menu.NONE;
    }

    public void startGame() {
        this.view.changeScene(SceneType.MENU);
    }

    public void notifyEvent(ViewEvents event) {
        
        switch(event) {
        case BACKTOMAINMENU:
            if(this.gameThread != null && this.gameThread.isRunning()){
                this.gameThread.stopGame();
                if(this.gameThread.isPaused()) {
                    this.gameThread.reStart();
                }
            }
            this.view.changeScene(SceneType.MENU);
            break;
        case EXIT:
            System.exit(0);
            break;
        case LEVEL1:
            this.levelsIterator = this.gameSettings.getLevelIterator();
            gameLoop(levelsIterator.next());
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
            gameLoop(this.levelsIterator.next());
            break;
        case SETTINGS:
            this.menuToLoad = Menu.SETTINGS;
            this.view.changeScene(SceneType.MENU);
            break;
        case EASYMODE:
            this.gameSettings.setGameDifficulty(GameDifficulty.EASY);
            break;
        case NORMALMODE:
            this.gameSettings.setGameDifficulty(GameDifficulty.NORMAL);
            break;
        case HARDMODE:
            this.gameSettings.setGameDifficulty(GameDifficulty.HARD);
            break;
        case DELIRIUMMODE:
            this.gameSettings.setGameDifficulty(GameDifficulty.DELIRIUM);
            break;
        default:
            inputManager.notifyViewInput(event);
            break;
         
        }
        
    }

    public Map<MenuCategory, MenuCategoryEntries> getButtons() {
        this.menuLoader = new MenuLoaderImpl(Menu.INITIAL);
        switch(this.menuToLoad) {
        case SETTINGS:
            this.menuLoader = new SettingsMenuLoaderImpl(Menu.SETTINGS, this.gameSettings);
            break;
        default:
            if(this.gameThread == null)
                this.menuLoader = new MenuLoaderImpl(Menu.INITIAL);
            else {
                switch(this.gameThread.getGameState()) {
                case FINISH:
                    this.menuLoader = new MenuLoaderImpl(Menu.INITIAL);
                    break;
                case INGAME:
                    throw new IllegalArgumentException();
                case LOSE:
                    this.menuLoader = new MenuLoaderImpl(Menu.LOSE);
                    break;
                case PAUSED:
                    this.menuLoader = new MenuLoaderImpl(Menu.PAUSE);
                    break;
                case WON:
                    if(this.levelsIterator.hasNext()){
                        this.menuLoader = new MenuLoaderImpl(Menu.WIN);
                    } else {
                        this.menuLoader = new MenuLoaderImpl(Menu.WINEND);
                    }
                    break;
                default:
                    throw new IllegalArgumentException();
                }
                    
            }
            /*if(this.gameThread == null || this.gameThread.getGameState() == GameState.FINISH) {
                this.menuLoader = new MenuLoaderImpl(Menu.INITIAL);
            } else if(this.gameThread.isPaused()) {
                this.menuLoader = new MenuLoaderImpl(Menu.PAUSE);
            } else if (this.gameThread.getGameState() == GameState.WON) {
                if(this.levelsIterator.hasNext()){
                    this.menuLoader = new MenuLoaderImpl(Menu.WIN);
                } else {
                    this.menuLoader = new MenuLoaderImpl(Menu.WINEND);
                }
                this.gameThread.setGameEnd();
            } else if (this.gameThread.getGameState() == GameState.LOSE) {
                this.menuLoader = new MenuLoaderImpl(Menu.LOSE);
                this.gameThread.setGameEnd();
            }*/
            break;
        }
        this.menuToLoad = Menu.NONE;
        return this.menuLoader.getMenuStructure();
    }

    private void gameLoop(Levels level) {
        
        LevelLoaderImpl ll = new LevelLoaderImpl(level, this.gameSettings.getEntityStatsModifier());
        EntitiesDatabase database2 = ll.getDatabase();
        this.model.createArena(ll.getEntities());
        this.view.setLevelDimension(ll.getLevelInfo().getLevelDimension());
        this.view.changeScene(SceneType.DRAWABLE);

        this.gameThread = new GameThreadImpl(this.model, this.view, database2, this.inputManager);
        gameThread.start();
    }

}
