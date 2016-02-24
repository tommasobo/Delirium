package control;

import java.util.Map;

public interface MenuLoader {

    Map<MenuCategory, MenuCategoryEntries> getMenuStructure();

}