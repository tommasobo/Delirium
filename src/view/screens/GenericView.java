package view.screens;

public interface GenericView {

    void display();

    void initScene();

    void accept(final Visitor visitor);

}
