package view.screens;

import control.Control;
import control.ViewEvents;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * The handler used to handle user inputs.
 */
class InputFromUser implements EventHandler<KeyEvent> {

    private final Control listener;

    /**
     * InputFromUser Constructor
     * 
     * @param listener
     *            Listener to which notify user actions.
     */
    InputFromUser(final Control listener) {
        this.listener = listener;
    }

    @Override
    public void handle(final KeyEvent event) {
        final KeyCode code = event.getCode();
        if (KeyEvent.KEY_PRESSED.equals(event.getEventType())) {
            if (code == KeyCode.A) {
                this.listener.notifyEvent(ViewEvents.MLEFT);
            }
            if (code == KeyCode.D) {
                this.listener.notifyEvent(ViewEvents.MRIGHT);
            }
            if (code == KeyCode.W) {
                this.listener.notifyEvent(ViewEvents.JUMP);
            }
            if (code == KeyCode.SPACE) {
                this.listener.notifyEvent(ViewEvents.SHOOT);
            }
        } else {
            if (code == KeyCode.A) {
                this.listener.notifyEvent(ViewEvents.STOPMLEFT);
            }
            if (code == KeyCode.D) {
                this.listener.notifyEvent(ViewEvents.STOPMRIGHT);
            }
            if (code == KeyCode.W) {
                this.listener.notifyEvent(ViewEvents.STOPJUMP);
            }
            if (code == KeyCode.SPACE) {
                this.listener.notifyEvent(ViewEvents.STOPSHOOT);
            }
            if (code == KeyCode.ESCAPE) {
                this.listener.notifyEvent(ViewEvents.PAUSE);
            }
        }
        event.consume();
    }
}
