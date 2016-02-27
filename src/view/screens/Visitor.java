package view.screens;

/**
 * The methods available to GenericViews.
 */
public interface Visitor {
    /**
     * This method take a StaticView as parameter.
     * 
     * @param view
     *            The StaticView.
     */
    void visit(StaticView view);

    /**
     * This method take a DynamicView as parameter.
     * 
     * @param view
     *            The StaticView.
     */
    void visit(DynamicView view);

}