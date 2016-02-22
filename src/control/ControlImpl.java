package control;

import java.util.List;

import javafx.geometry.Dimension2D;
import model.Model;
import model.ModelImpl;
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
        
        if (event.equals(ViewEvents.LEVEL1)) {
            gameLoop(ViewEvents.LEVEL1);
            return;
        } else if (event.equals(ViewEvents.EXIT)) {
            System.exit(0);
            return;
        } else if (event.equals(ViewEvents.PAUSE)) {
            this.menuLoader = new MenuLoaderImpl(Menu.PAUSE);
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
        if(this.menuLoader == null) {
            this.menuLoader = new MenuLoaderImpl(Menu.INITIAL);
        }
        return this.menuLoader.getButtonsList();
    }

    private void gameLoop(ViewEvents level) {
        
        LevelLoaderImpl ll = new LevelLoaderImpl(Levels.LEVEL1);
        EntitiesDatabase database2 = ll.getDatabase();
        this.model.createArena(ll.getEntities());
        this.view.changeScene(new Pair<SceneType, Dimension2D>(SceneType.DRAWABLE, new Dimension2D(1000, 300)));

        this.gameThread = new GameThreadImpl(this.model, this.view, database2, this.inputManager);
        gameThread.start();
    }

}
