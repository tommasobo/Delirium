package view;

public interface Visitor {

    void visit(StaticView view);

    void visit(DynamicView view);

}