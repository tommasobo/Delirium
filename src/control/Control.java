package control;

import java.util.Map;

import control.viewcomunication.MenuCategory;
import control.viewcomunication.MenuCategoryEntries;
import control.viewcomunication.ViewEvents;

/**
 * The interface control declare all methods for the comunication to view.
 * @author Matteo Magnani
 *
 */
public interface Control {
    /**
     * The method starts the game and initialize the initial menu.
     */
    void startGame();

    /**
     * The method takes an event that come from view and translates it into game event.
     * @param event The view event that happened
     */
    void notifyEvent(ViewEvents event);

    /**
     * The method gets the appropriate menu structure according to game state and last view event translated.
     * @return A map that represents the appropriate menu structure
     */
    Map<MenuCategory, MenuCategoryEntries> getButtons();

}
