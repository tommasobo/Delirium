package view.screens;

import java.util.Optional;

/**
 * The implementation of Visitor's interface. This class distinguishes between
 * Static and Dynamic views declared as Generic ones and makes a specific action
 * based on the scene type.
 */
public class VisitorImpl implements Visitor {

    private Optional<DynamicView> view = Optional.empty();

    /**
     * VisitorImpl Constructor.
     */
    public VisitorImpl() {
    }

    @Override
    public void visit(final StaticView view) {
        this.view = Optional.empty();
    }

    @Override
    public void visit(final DynamicView view) {
        this.view = Optional.of(view);
    }

    /**
     * This method returns an optional containing a DynamicView if the visit method was called
     * using this type of scene as parameter. Otherwise it returns an
     * Optional.empty().
     * 
     * @return Optional<DynamicView>
     */
    public Optional<DynamicView> getView() {
        return this.view;
    }
}
