package control.viewcomunication;

import java.util.List;
import java.util.Optional;

/**
 * Interface that declare methods for object that represent menu's category structure
 * @author Matteo Magnani
 *
 */
public interface MenuCategoryEntries {

    /**
     * 
     * @return The "focus" menu entry, the menu entry already selected
     */
    Optional<Buttons> getFocus();

    /**
     * 
     * @return The list of menu's buttons
     */
    List<Buttons> getEntries();

}