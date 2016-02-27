package view.screens;

/**
 * Contains methods that every scene drawn by this software should have,
 * independently from its type.
 *
 */
public interface GenericView {
    /**
     * Make the scene visible.
     * 
     * @throws IllegalStateException
     *             If it is called before the initialization.
     */
    void display();

    /**
     * Configure the scene to draw and initialize it. This method can only be
     * called once.
     * 
     * @throws IllegalStateException
     *             If the scene was already initialized
     */
    void initScene();

    /**
     * Method used to distinguish classes that implement this interface.
     * 
     * @param visitor
     *            The visitor
     */
    void accept(final Visitor visitor);

}
