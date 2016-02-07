package view;

import javafx.stage.Stage;

public class StaticView extends GenericViewImpl {

    public StaticView(Stage stage) {
        super(stage);
    }
    
    @Override
    protected void firstDraw() {
        //aggiungere disegno dei pulsanti ottenuti dal listener
    }

}
