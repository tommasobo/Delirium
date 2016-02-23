package view;

import java.util.List;

import control.Buttons;
import control.Control;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.PopupWindow.AnchorLocation;

public class ButtonsPane {
    
    private final VBox box;
    private final Control listener;
    
    public ButtonsPane(final Control listener) {
        this.box = new VBox();
        this.listener = listener;
    }
    
    public VBox getButtonPane() {
        
        box.setAlignment(Pos.CENTER);
        final List<Buttons> buttons = this.listener.getButtons();
        for (final Buttons b : buttons) {          
            final Button but = new Button(b.name());
            but.setId("button");
            but.setOnAction(e -> this.listener.notifyEvent(b.getEvent()));
            box.getChildren().add(but);
        }
        //da rivedere
        if (AudioManager.getAudioManager().isAudioAvailable()) {
            final Button themeMusic = new Button("AUDIO SETTINGS");
            themeMusic.setId("button");
            themeMusic.setOnAction(e -> this.getAudioContextMenu(themeMusic).show(themeMusic, Side.RIGHT, 0, -13));
            box.getChildren().add(themeMusic);
        }
        
        return box;
    }
    
    private ContextMenu getAudioContextMenu(final Button themeMusic) {
        
        final ContextMenu cm = new ContextMenu();
        cm.setOnShowing(e -> themeMusic.setDisable(true));
        cm.setOnHidden(e -> themeMusic.setDisable(false));
        final Slider sliderTheme = new Slider(0.0, 1.0, AudioManager.getAudioManager().getMusicVolume());
        sliderTheme.valueProperty().addListener(e -> AudioManager.getAudioManager().setMusicVolume(sliderTheme.getValue()));
        final Slider sliderEffects = new Slider(0.0, 1.0, AudioManager.getAudioManager().getEffectsVolume());
        sliderEffects.valueProperty().addListener(e -> AudioManager.getAudioManager().setEffectsVolume(sliderEffects.getValue()));
        cm.getItems().addAll(new CustomMenuItem(sliderTheme, false), new CustomMenuItem(sliderEffects, false));
        return cm;
    }
    
}
