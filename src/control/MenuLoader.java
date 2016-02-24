package control;

import java.util.List;
import java.util.Map;

public interface MenuLoader {

    Map<MenuCategory, MenuCategoryEntries> getMenuStructure();

}