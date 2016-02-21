package view;

public interface GenericView {
    
    void display();
    void initScene();
    void accept(final Visitor visitor);

}
