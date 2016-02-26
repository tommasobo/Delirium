package view;

import view.screens.DynamicView;
import view.screens.StaticView;

public interface Visitor {

    void visit(StaticView view);

    void visit(DynamicView view);

}