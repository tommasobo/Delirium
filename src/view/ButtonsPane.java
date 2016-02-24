package view;

import java.util.List;
import java.util.Map;

import control.Buttons;
import control.Control;
import control.MenuCategory;
import control.MenuCategoryEntries;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuButton;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;

public class ButtonsPane {
    
    private final VBox box;
    private final Control listener;
    
    public ButtonsPane(final Control listener) {
        this.box = new VBox();
        this.listener = listener;
    }
    
    public VBox getButtonPane() {
        /*
        box.setAlignment(Pos.CENTER);
        final List<Buttons> buttons = this.listener.getButtons();
        for (final Buttons b : buttons) {          
            final Button but = new Button(b.name());
            but.setId("button");
            but.setFocusTraversable(false);
            but.setOnMouseClicked(e -> this.listener.notifyEvent(b.getEvent()));
            box.getChildren().add(but);
        }
        */
        
        this.box.setAlignment(Pos.CENTER);
        final Map<MenuCategory, MenuCategoryEntries> buttons = this.listener.getButtons();
        buttons.keySet().forEach(category -> {
            if (category == MenuCategory.DEFAULT) {
                buttons.get(category).getEntries().forEach(button -> {
                    box.getChildren().add(this.createButton(button));
                });
            } else {
                final MenuButton menubut = new MenuButton(category.getName());
                menubut.setPopupSide(Side.RIGHT);
                ToggleGroup toggleGroup = new ToggleGroup();
                buttons.get(category).getEntries().forEach(menuitem -> {
                    final RadioMenuItem rmi = this.createMenuItem(menuitem);
                    if (buttons.get(category).getFocus().isPresent() && buttons.get(category).getFocus().get() == menuitem) {
                        rmi.setSelected(true);
                    }
                    rmi.setToggleGroup(toggleGroup);
                    menubut.getItems().add(rmi);
                });
                box.getChildren().add(menubut);
            }
        });
        return this.box;
        
        /*
        final MenuButton menubut = new MenuButton("prova");
        menubut.setId("button");
        menubut.setPopupSide(Side.RIGHT);
        ToggleGroup toggleGroup = new ToggleGroup();

        RadioMenuItem radioItem1 = new RadioMenuItem("Option 1");
        radioItem1.setSelected(true);
        radioItem1.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                System.out.println("radio toggled");
            }
        });
        radioItem1.setToggleGroup(toggleGroup);
        RadioMenuItem radioItem2 = new RadioMenuItem("Option 2");
        radioItem2.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                System.out.println("radio toggled");
            }
        });
        radioItem2.setToggleGroup(toggleGroup);
        menubut.getItems().addAll(radioItem1, radioItem2);
        box.getChildren().add(menubut);
        
        //da rivedere
        if (AudioManager.getAudioManager().isAudioAvailable()) {
            final Button themeMusic = new Button("AUDIO SETTINGS");
            themeMusic.setId("button");
            themeMusic.setFocusTraversable(false);
            themeMusic.setOnMouseClicked(e -> this.getAudioContextMenu(themeMusic).show(themeMusic, Side.RIGHT, 0, -13));
            box.getChildren().add(themeMusic);
        }
        
        return box;
        */
    }
    
    
    
    private RadioMenuItem createMenuItem(final Buttons menuitem) {
        final RadioMenuItem radioItem = new RadioMenuItem(menuitem.getName());
        radioItem.setOnAction(e -> this.listener.notifyEvent(menuitem.getEvent()));
        return radioItem;
    }

    private Node createButton(final Buttons button) {
        final Button but = new Button(button.getName());
        but.setId("button");
        but.setFocusTraversable(false);
        but.setOnMouseClicked(e -> this.listener.notifyEvent(button.getEvent()));
        return but;
    }
/*
    private ContextMenu getAudioContextMenu(final Button themeMusic) {
        
        //final ContextMenu cm = new ContextMenu();
        
        final MenuButton menubut = new MenuButton("AUDIO");
        Menu m = new Menu();
        menubut.setPopupSide(Side.RIGHT);
        final Slider sliderTheme = new Slider(0.0, 1.0, AudioManager.getAudioManager().getMusicVolume());
        sliderTheme.valueProperty().addListener(e -> AudioManager.getAudioManager().setMusicVolume(sliderTheme.getValue()));
        
        final Slider sliderEffects = new Slider(0.0, 1.0, AudioManager.getAudioManager().getEffectsVolume());
        sliderEffects.valueProperty().addListener(e -> AudioManager.getAudioManager().setEffectsVolume(sliderEffects.getValue()));
        menubut.getItems().addAll(new CustomMenuItem(sliderTheme, false), new CustomMenuItem(sliderEffects, false));
        return cm;
    }
 */   
}
