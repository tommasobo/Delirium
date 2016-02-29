package control.fileloading.menu;

import java.util.Map;

import control.viewcomunication.MenuCategory;
import control.viewcomunication.MenuCategoryEntries;

/**
 * Interface that declares methods for MenuLoader.
 * @author Matteo Magnani
 *
 */
public interface MenuLoader {

    /**
     * 
     * @return A map that represent menu categories and relative buttons
     */
    Map<MenuCategory, MenuCategoryEntries> getMenuStructure();

}