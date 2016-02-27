package control.fileloading.menu;

import java.util.Map;

import control.viewcomunication.MenuCategory;
import control.viewcomunication.MenuCategoryEntries;

public interface MenuLoader {

    /**
     * 
     * @return A map that represent menu categories and relative buttons
     */
    Map<MenuCategory, MenuCategoryEntries> getMenuStructure();

}