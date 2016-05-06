package view.screens;

import control.Control;
import control.viewcomunication.ViewEvents;
import javafx.geometry.Dimension2D;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Pair;

public class MenuItem extends StackPane {     
    
    private final double buttonWidth;
    private final double buttonHeight;
    private final StaticView staticView;
    private final Control controller;
    private final Dimension2D screenDimension;
        
        public MenuItem(String name, StaticView sv, ViewEvents event, Control controller, Dimension2D dimension2d) {
            this.controller = controller;
            this.staticView = sv;
            this.screenDimension = dimension2d;
            this.buttonWidth = this.screenDimension.getWidth() / 2.54;
            this.buttonHeight = this.screenDimension.getHeight() / 12.875;
            javafx.scene.shape.Rectangle bg = new javafx.scene.shape.Rectangle(this.buttonWidth, this.buttonHeight);
            javafx.scene.paint.LinearGradient gradient = new javafx.scene.paint.LinearGradient(0, 0, 1, 0 , true, CycleMethod.NO_CYCLE, new Stop[]{
                    new Stop(0, Color.ALICEBLUE),
                    new Stop(0.35, Color.BLACK),
                    new Stop(0.65, Color.BLACK),
                    new Stop(1, Color.ALICEBLUE)
            });
            

            bg.setFill(gradient);
            bg.setId("rect");
            bg.setVisible(false);
            
            Text text = new Text(name + ""  );
            text.setId("menutxt");
            text.setFill(Color.BLACK);
            text.setFont(Font.font(this.screenDimension.getWidth() / 35.55 ));
            
            this.getChildren().addAll(this.createSeparator().getKey(), this.createSeparator().getValue(), bg, text);
                        
            this.setOnMouseEntered(e -> {
                bg.setVisible(true);
                text.setFill(Color.WHITE);
                staticView.animateMenu(staticView.getLeftPane(), "shoot-dx");
                staticView.animateMenu(staticView.getRightPane(), "shoot-sx");
            });
            
            this.setOnMouseExited(e -> {
                bg.setVisible(false);
                text.setFill(Color.BLACK);
            });
            
            this.setOnMousePressed(e -> {
                text.setFill(Color.FIREBRICK);
                this.controller.notifyEvent(event);
            });
            
            this.setOnMouseReleased(e -> {
                text.setFill(Color.BLACK);
            });
        }
        
        private Pair<Line, Line> createSeparator() {   
            Line sep = new Line();
            Line sep2 = new Line();
            sep.setEndX(this.buttonWidth);
            sep.setTranslateY(-this.buttonHeight / 2);
            sep2.setEndX(this.buttonWidth);
            sep2.setTranslateY(this.buttonHeight / 2);
            javafx.scene.paint.LinearGradient gradient = new javafx.scene.paint.LinearGradient(0, 0, 1, 0 , true, CycleMethod.NO_CYCLE, new Stop[]{
                    new Stop(0, Color.ALICEBLUE),
                    new Stop(0.35, Color.BLACK),
                    new Stop(0.65, Color.BLACK),
                    new Stop(1, Color.ALICEBLUE)
            });
            sep.setStroke(gradient);
            sep2.setStroke(gradient);
            return new Pair<Line, Line>(sep, sep2);
        }

    }