package view.screens;

import java.util.Map;

import control.Buttons;
import control.Control;
import control.MenuCategory;
import control.MenuCategoryEntries;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.MenuButton;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import view.utilities.AudioManager;

/**
 * This class creates a box containing buttons. It is used by scenes to create
 * button menus.
 */
class ButtonsPane {

    private static final int DEF_WIDTH_DIVISOR = 5;
    private final VBox box;
    private final Control listener;

    /**
     * ButtonsPane Constructor.
     * 
     * @param listener
     *            The controller listener to which ask what buttons should be
     *            represented,
     */
    ButtonsPane(final Control listener) {
        this.box = new VBox();
        this.listener = listener;
    }

    /**
     * This methods build the vertical pane containing buttons using
     * informations requested to the controller using the listener and returns
     * it.
     * 
     * @param sceneWidth
     *            The width of the scene in which the pane will be draw
     * @return The vertical box containing buttons
     */
    public VBox getButtonPane(final double sceneWidth) {

        this.box.setAlignment(Pos.CENTER);
        this.box.setMaxWidth(sceneWidth / DEF_WIDTH_DIVISOR);
        final Map<MenuCategory, MenuCategoryEntries> buttons = this.listener.getButtons();
        buttons.keySet().forEach(category -> {
            if (category == MenuCategory.DEFAULT) {
                buttons.get(category).getEntries().forEach(button -> {
                    box.getChildren().add(this.createButton(button));
                });
            } else {
                final MenuButton menubut = new MenuButton(category.getName());
                menubut.setPopupSide(Side.RIGHT);
                menubut.setFocusTraversable(false);
                menubut.setMaxWidth(Double.MAX_VALUE);
                final ToggleGroup toggleGroup = new ToggleGroup();
                buttons.get(category).getEntries().forEach(menuitem -> {
                    final RadioMenuItem rmi = this.createMenuItem(menuitem);
                    if (buttons.get(category).getFocus().isPresent()
                            && buttons.get(category).getFocus().get() == menuitem) {
                        rmi.setSelected(true);
                    }
                    rmi.setToggleGroup(toggleGroup);
                    menubut.getItems().add(rmi);
                });
                box.getChildren().add(menubut);
            }
        });
        if (AudioManager.getAudioManager().isAudioAvailable()
                && buttons.keySet().stream().anyMatch(e -> e != MenuCategory.DEFAULT)) {
            box.getChildren().add(this.getAudioButton());
        }
        return this.box;

    }

    /**
     * Create a Radio Menu item.
     * 
     * @param menuitem
     *            The informations about this item.
     * @return The Radio Menu Item
     */
    private RadioMenuItem createMenuItem(final Buttons menuitem) {
        final RadioMenuItem radioItem = new RadioMenuItem(menuitem.getName());
        radioItem.setOnAction(e -> this.listener.notifyEvent(menuitem.getEvent()));
        return radioItem;
    }

    /**
     * Create a single Button.
     * 
     * @param button
     *            The informations about this item.
     * @return The created button.
     */
    private Node createButton(final Buttons button) {
        final Button but = new Button(button.getName());
        but.setId("button");
        but.setMaxWidth(Double.MAX_VALUE);
        but.setFocusTraversable(false);
        but.setOnMouseClicked(e -> this.listener.notifyEvent(button.getEvent()));
        return but;
    }

    /**
     * Build the menu button used to control audio in the game.
     * 
     * @return The button created
     */
    private MenuButton getAudioButton() {

        final MenuButton menubut = new MenuButton("AUDIO");
        menubut.setPopupSide(Side.RIGHT);
        menubut.setMaxWidth(Double.MAX_VALUE);
        final Slider sliderTheme = new Slider(0.0, 1.0, AudioManager.getAudioManager().getMusicVolume());
        sliderTheme.valueProperty()
                .addListener(e -> AudioManager.getAudioManager().setMusicVolume(sliderTheme.getValue()));
        final Text txt1 = new Text("THEME");
        txt1.setId("text2");
        final VBox themeBox = new VBox(txt1, sliderTheme);
        final Slider sliderEffects = new Slider(0.0, 1.0, AudioManager.getAudioManager().getEffectsVolume());
        sliderEffects.valueProperty()
                .addListener(e -> AudioManager.getAudioManager().setEffectsVolume(sliderEffects.getValue()));
        final Text txt2 = new Text("EFFECTS");
        txt2.setId("text2");
        final VBox effectsBox = new VBox(txt2, sliderEffects);
        menubut.getItems().addAll(new CustomMenuItem(themeBox, false), new CustomMenuItem(effectsBox, false));
        return menubut;

    }

}
