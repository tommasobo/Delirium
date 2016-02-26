package view.screens;

import view.Visitor;

public interface GenericView {

    void display();

    void initScene();

    void accept(final Visitor visitor);

}
