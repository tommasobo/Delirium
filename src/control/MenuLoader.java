package control;

import java.util.Map;

public interface MenuLoader {

    /**
     * 
     * @return A map that represent menu categories and relative buttons
     */
    Map<MenuCategory, MenuCategoryEntries> getMenuStructure();

}