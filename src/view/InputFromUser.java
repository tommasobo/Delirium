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
        if (KeyEvent.KEY_PRESSED.equals(event.getEventType())) {
            if (code == KeyCode.A){
                this.listener.notifyEvent(ViewEvents.MLEFT);
            }
            if (code == KeyCode.D){
                this.listener.notifyEvent(ViewEvents.MRIGHT);    
            }
            if (code == KeyCode.W){
                this.listener.notifyEvent(ViewEvents.JUMP);
            }
            if (code == KeyCode.SPACE){
                this.listener.notifyEvent(ViewEvents.SHOOT);
            }
        } else {
            if (code == KeyCode.A){
                this.listener.notifyEvent(ViewEvents.STOPMLEFT);
            }
            if (code == KeyCode.D){
                this.listener.notifyEvent(ViewEvents.STOPMRIGHT);    
            }
            if (code == KeyCode.W){
                this.listener.notifyEvent(ViewEvents.STOPJUMP);
            }
            if (code == KeyCode.SPACE){
                this.listener.notifyEvent(ViewEvents.STOPSHOOT);
            }
            if (code == KeyCode.ESCAPE){
                this.listener.notifyEvent(ViewEvents.PAUSE);
            }
        }
        event.consume();
    }
}
