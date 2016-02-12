package view;

import control.Control;
import control.ViewEvents;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class InputFromUser implements EventHandler<KeyEvent>{
    
    final private Control listener;
    
    public InputFromUser(final Control listener) {
        this.listener = listener;
    }
    
    @Override
    public void handle(KeyEvent event) {
        KeyCode code = event.getCode();
        
        if (code == KeyCode.RIGHT){
            this.listener.notifyEvent(ViewEvents.MRIGHT);
        }
        if (code == KeyCode.LEFT){
            this.listener.notifyEvent(ViewEvents.MLEFT);    
        }
        if (code == KeyCode.SPACE){
            this.listener.notifyEvent(ViewEvents.JUMP);    
        }
    }

}
