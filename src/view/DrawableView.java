package view;

import java.util.List;
import java.util.Map;

import control.Entities;
import control.Pair;
import control.Position;
import javafx.scene.CacheHint;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

public class DrawableView implements GenericView {
    
    private final Stage stage;
    private Scene drawableScene;
    private GraphicsContext gc;
    
    public DrawableView(final Stage stage) {
        this.stage = stage;
    }

    @Override
    public void initView() {
        
        Group root = new Group();
        this.drawableScene = new Scene(root);
        Canvas canvas = new Canvas(1000, 500);
        canvas.setCache(true);
        canvas.setCacheHint(CacheHint.SPEED);
        root.getChildren().add(canvas);
        this.gc = canvas.getGraphicsContext2D();
    }

    @Override
    public void updateView(final Map<Entities, List<Pair<Integer, Position>>> entities) {
        gc.clearRect(0, 0, 1000, 500);
        //gc.fillRect(0, 0, 1000, 500);
        entities.keySet().forEach(k -> {
            entities.get(k).forEach(e -> {
                //da aggiungere il meccanismo di reperimento delle immagini
                //gc.drawImage(new Image("") , e.getY().getPoint().getX(), e.getY().getPoint().getX(), e.getY().getDimension().getWidth(), e.getY().getDimension().getWidth());
                gc.fillRect(e.getY().getPoint().getX(), e.getY().getPoint().getX(), e.getY().getDimension().getWidth(), e.getY().getDimension().getWidth());
            });
        });
        
    }

    @Override
    public void display() {
        stage.setScene(drawableScene);
        stage.show();
    }

}
