package view;

import java.util.List;
import java.util.Map;

import control.Entities;
import control.Pair;
import control.Position;
import javafx.stage.Stage;

public class ButtonsView implements GenericView {
    
    private final Stage stage;
    
    public ButtonsView(final Stage stage) {
        this.stage = stage;
    }

    @Override
    public void initView() {
        // TODO Auto-generated method stub  
    }

    @Override
    public void updateView(Map<Entities, List<Pair<Integer, Position>>> entities) {
        // TODO Auto-generated method stub
    }

    @Override
    public void display() {
        // TODO Auto-generated method stub
    }
    
}
