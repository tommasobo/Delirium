package control;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import control.exceptions.CriticIOExceptions;
import model.Model;
import model.ModelImpl;
import view.ViewController;
import view.configs.Notifications;
import view.configs.SceneType;

public class ControlImpl implements Control {
    private final Model model;
    final private ViewDecorator view;
    private final InputManager inputManager;
    private GameThread gameThread;
    private final GameSettings gameSettings;
    private Iterator<Levels> levelsIterator;
    private Menu menuToLoad;

    public ControlImpl(final ViewController view) {
        this.inputManager = new InputManagerImpl();
        this.model = ModelImpl.getModel();
        view.setListener(this);
        this.view = new ViewDecoratorImpl(view);
        try {
            this.gameSettings = new SettingsLoaderImpl().getGameSettings();
        } catch (IOException e) {
            throw new CriticIOExceptions(e);
        }
        this.menuToLoad = Menu.NONE;
    }

    public void startGame() {
        this.view.changeScene(SceneType.MENU);
    }

    public void notifyEvent(final ViewEvents event) {
        
        switch(event) {
        case BACKTOMAINMENU:
            if(this.gameThread != null && this.gameThread.isRunning()){
                this.gameThread.stopGame();
                if(this.gameThread.isPaused()) {
                    this.gameThread.reStart();
                }
                this.gameThread.setGameEnd();
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
        MenuLoader menuLoader;
        try {
            menuLoader = new MenuLoaderImpl(Menu.INITIAL);
            if(this.menuToLoad == Menu.NONE) {
                if(this.gameThread == null) {
                    menuLoader = new MenuLoaderImpl(Menu.INITIAL);
                } else {
                    switch(this.gameThread.getGameState()) {
                    case FINISH:
                        menuLoader = new MenuLoaderImpl(Menu.INITIAL);
                        break;
                    case INGAME:
                        throw new IllegalArgumentException();
                    case LOSE:
                        menuLoader = new MenuLoaderImpl(Menu.LOSE);
                        this.gameThread.setGameEnd();
                        break;
                    case PAUSED:
                        menuLoader = new MenuLoaderImpl(Menu.PAUSE);
                        break;
                    case WON:
                        if(this.levelsIterator.hasNext()){
                            menuLoader = new MenuLoaderImpl(Menu.WIN);
                        } else {
                            menuLoader = new MenuLoaderImpl(Menu.WINEND);
                        }
                        this.gameThread.setGameEnd();
                        break;
                    default:
                        throw new IllegalStateException("The thread is in state" + this.gameThread.getGameState().toString());
                    }      
                }
                
            } else {
                menuLoader = new SettingsMenuLoaderImpl(this.menuToLoad, this.gameSettings);
                this.menuToLoad = Menu.NONE;
            }
        }catch (IOException e) {
            throw new CriticIOExceptions(e);
        }
        return menuLoader.getMenuStructure();
    }

    private void gameLoop(final Levels level) {
        
        LevelLoaderImpl ll;
        try {
            ll = new LevelLoaderImpl(level, this.gameSettings.getEntityStatsModifier());
        } catch (IOException e) {
            throw new CriticIOExceptions(e);
        }
        final EntitiesDatabase database = ll.getDatabase();
        this.model.createArena(ll.getEntities());
        this.view.setLevelDimension(database.getArenaDimension());
        this.view.changeScene(SceneType.DRAWABLE);

        this.gameThread = new GameThreadImpl(this.model, this.view, database, this.inputManager);
        gameThread.start();
    }

}
