package control;

import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;

import model.arena.utility.Actions;
import utility.Pair;

/**
 * This class take all view keys events and save their translation to model
 * actions in a circle queue. This implementation allows an efficient manage of
 * multiple keys pressed at the same time
 * 
 * @author Matteo Magnani
 *
 */
public class InputManagerImpl implements InputManager {

    private final Queue<Pair<model.arena.utility.Actions, Optional<model.arena.utility.Directions>>> actions;
    private boolean keyDeactivated;
    private boolean noMoves;
    
    public InputManagerImpl() {
        this.actions = new LinkedList<>();
        this.keyDeactivated = false;
        this.noMoves = false;
    }
    
    /**
     * The method take a view event that represent a key pressed. If it is a
     * "start key press" put it in the circular queue, if it is a
     * "stop key press" remove it from the queue. The method also adopt
     * strategies to eliminate opposite direction keys from the queue and re-add
     * a key removed after his opposite is removed from the queue. The queue
     * contains key pressed already translated in PG actions.The method is
     * synchronized in order to make it usable to view and game thread.
     */
    synchronized public void notifyViewInput(final ViewEvents event) {
        if (event == ViewEvents.MLEFT || event == ViewEvents.JUMP || event == ViewEvents.MRIGHT || event == ViewEvents.SHOOT) {
            if (!this.actions.contains(Translator.translateViewInput(event))) {
                this.actions.add(Translator.translateViewInput(event));
            }
            if (event == ViewEvents.MLEFT && actions.contains(Translator.translateViewInput(ViewEvents.MRIGHT))) {
                this.actions.remove(Translator.translateViewInput(ViewEvents.MRIGHT));
                this.keyDeactivated = true;
            }
            if (event == ViewEvents.MRIGHT && actions.contains(Translator.translateViewInput(ViewEvents.MLEFT))) {
                this.actions.remove(Translator.translateViewInput(ViewEvents.MLEFT));
                this.keyDeactivated = true;
            }
        } else {
            if (this.actions.remove(Translator.translateViewInput(event))) {
                if(!actions.stream().map(t -> t.getX()).anyMatch(t -> t == model.arena.utility.Actions.MOVE)) {
                    this.noMoves = true;
                }
            } else {
                this.keyDeactivated = false;
            }
            if(event == ViewEvents.STOPMLEFT && keyDeactivated) {
                this.actions.add(Translator.translateViewInput(ViewEvents.MRIGHT));
                this.keyDeactivated = false;
            }
            if(event == ViewEvents.STOPMRIGHT && keyDeactivated) {
                this.actions.add(Translator.translateViewInput(ViewEvents.MLEFT));
                this.keyDeactivated = false;
            }
        }
    }
    
    /**
     * The method take the next PG action in the circular queue. The method
     * adopt strategies to take "move and jump" combo action and to stop PG
     * movement if there are actions to perform but there aren't any move
     * actions in the queue
     */
    synchronized public Pair<model.arena.utility.Actions, Optional<model.arena.utility.Directions>> getNextPGAction() {
        Pair<model.arena.utility.Actions, Optional<model.arena.utility.Directions>> action;
        if(noMoves || this.actions.isEmpty()) {
            this.noMoves = false;
            action = new Pair<>(Actions.STOP, Optional.empty());
        } else {
            action = this.actions.peek();
            
            if(action.getX() == Actions.JUMP && actions.stream().anyMatch(t -> t.getX() == Actions.MOVE)) {
                final Optional<Pair<model.arena.utility.Actions, Optional<model.arena.utility.Directions>>> op = actions.stream().filter(t -> t.getX() == Actions.MOVE).findFirst();
                action = new Pair<>(model.arena.utility.Actions.MOVEONJUMP, op.get().getY());
            } else if(action.getX() == Actions.MOVE && actions.stream().anyMatch(t -> t.getX() == Actions.JUMP)) {
                action = new Pair<>(model.arena.utility.Actions.MOVEONJUMP, action.getY());
            }
            this.actions.add(this.actions.poll());
        }
        
        return action;
    }
}
