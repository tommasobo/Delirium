package control.viewcomunication;

import java.util.List;
import java.util.Optional;

/**
 * That class contains all elements to determinate the structure of a menu
 * category
 * 
 * @author Matteo Magnani
 *
 */
public class MenuCategoryEntriesImpl implements MenuCategoryEntries {
    private final List<Buttons> entries;
    private Optional<Buttons> focus;

    /**
     * 
     * @param entries
     *            The list of menu's buttons
     * @param focus
     *            The "focus" menu entry, the menu entry already selected
     */
    public MenuCategoryEntriesImpl(final List<Buttons> entries, final Optional<Buttons> focus) {
        super();
        this.entries = entries;
        this.focus = focus;
    }

    @Override
    public Optional<Buttons> getFocus() {
        return focus;
    }

    /**
     * 
     * @param focus
     *            The "focus" menu entry, the menu entry already selected
     */
    public void setFocus(final Optional<Buttons> focus) {
        this.focus = focus;
    }

    @Override
    public List<Buttons> getEntries() {
        return entries;
    }
}
