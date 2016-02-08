package view;

import java.util.List;
import java.util.Map;

import control.Control;
import control.Entities;
import control.Pair;
import control.Position;
import javafx.scene.CacheHint;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

public class DynamicViewImpl extends GenericViewImpl implements DynamicView {
    
    private GraphicsContext gc;
    
    public DynamicViewImpl(final Stage stage, final Control listener) {
        super(stage, listener);
    }

    @Override
    public void updateScene(Map<Entities, List<Pair<Integer, Position>>> entities) {
        gc.clearRect(0, 0, super.root.getScene().getWidth() , super.root.getScene().getHeight());
        entities.keySet().forEach(k -> {
            entities.get(k).forEach(e -> {
                //da aggiungere il meccanismo di reperimento delle immagini
                //gc.drawImage(new Image("") , e.getY().getPoint().getX(), e.getY().getPoint().getX(), e.getY().getDimension().getWidth(), e.getY().getDimension().getWidth());
                gc.fillRect(e.getY().getPoint().getX(), e.getY().getPoint().getX(), e.getY().getDimension().getWidth(), e.getY().getDimension().getHeight());
            });
        });
    }

    @Override
    protected void firstDraw() {
        
        super.stage.setHeight(500);
        super.stage.setWidth(1000);
        Canvas canvas = new Canvas(super.root.getScene().getWidth() , super.root.getScene().getHeight());
        canvas.setCache(true);
        canvas.setCacheHint(CacheHint.SPEED);
        super.root.getChildren().add(canvas);
        this.gc = canvas.getGraphicsContext2D();
        super.root.getScene().setOnKeyPressed(new InputFromUser(listener));
   
    }
 
}
