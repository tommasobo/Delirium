package view;

import java.util.Optional;

import view.screens.DynamicView;
import view.screens.StaticView;

public class VisitorImpl implements Visitor {

    private Optional<DynamicView> view = Optional.empty();

    @Override
    public void visit(final StaticView view) {
        this.view = Optional.empty();
    }

    @Override
    public void visit(final DynamicView view) {
        this.view = Optional.of(view);
    }

    public Optional<DynamicView> getView() {
        return this.view;
    }
}
