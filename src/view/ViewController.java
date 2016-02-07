package view;

import java.util.List;
import java.util.Map;

import control.Entities;
import control.Pair;
import control.Position;

public interface ViewController {
    
    ViewController getView();
    void drawContent(Map<Entities,List<Pair<Integer,Position>>> entities);
    void changeStage(final boolean type);
    void getWhatIsAppened();
    
}