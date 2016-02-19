package control;

import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;

import model.Actions;

public class InputManagerImpl implements InputManager {

    private final Queue<Pair<model.Actions, Optional<model.Directions>>> actions;
    private boolean keyDeactivated = false;
    
    public InputManagerImpl() {
        actions = new LinkedList<>();
    }
    
    synchronized public void notifyViewInput(ViewEvents event) {
        if (event == ViewEvents.MLEFT || event == ViewEvents.JUMP || event == ViewEvents.MRIGHT || event == ViewEvents.SHOOT) {
            if (!this.actions.contains(Translator.tranViewEvents(event))) {
                this.actions.add(Translator.tranViewEvents(event));
            }
            if (event == ViewEvents.MLEFT && actions.contains(Translator.tranViewEvents(ViewEvents.MRIGHT))) {
                this.actions.remove(Translator.tranViewEvents(ViewEvents.MRIGHT));
                keyDeactivated = true;
            }
            if (event == ViewEvents.MRIGHT && actions.contains(Translator.tranViewEvents(ViewEvents.MLEFT))) {
                this.actions.remove(Translator.tranViewEvents(ViewEvents.MLEFT));
                keyDeactivated = true;
            }
        } else {
            if (this.actions.remove(Translator.tranViewEvents(event)) != true) {
                keyDeactivated = false;
            }
            if(event == ViewEvents.STOPMLEFT && keyDeactivated) {
                this.actions.add(Translator.tranViewEvents(ViewEvents.MRIGHT));
                keyDeactivated = false;
            }
            if(event == ViewEvents.STOPMRIGHT && keyDeactivated) {
                this.actions.add(Translator.tranViewEvents(ViewEvents.MLEFT));
                keyDeactivated = false;
            }
        }
    }
    
    synchronized public Pair<model.Actions, Optional<model.Directions>> getNextPGAction() {
        Pair<model.Actions, Optional<model.Directions>> action;
        if(!this.actions.isEmpty()) {
            action = this.actions.peek();
            
            if(action.getX() == Actions.JUMP && actions.stream().anyMatch(t -> t.getX() == Actions.MOVE)) {
                Optional<Pair<model.Actions, Optional<model.Directions>>> op = actions.stream().filter(t -> t.getX() == Actions.MOVE).findFirst();
                action = new Pair<>(model.Actions.MOVEONJUMP, op.get().getY());
            } else if(action.getX() == Actions.MOVE && actions.stream().anyMatch(t -> t.getX() == Actions.JUMP)) {
                action = new Pair<>(model.Actions.MOVEONJUMP, action.getY());
            }
            this.actions.add(this.actions.poll());
        } else {
            action = new Pair<>(Actions.STOP, Optional.empty());
        }
        
        return action;
    }
}
