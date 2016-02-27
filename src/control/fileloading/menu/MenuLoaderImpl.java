package control.fileloading.menu;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import control.viewcomunication.MenuCategory;
import control.viewcomunication.MenuCategoryEntries;
import control.viewcomunication.MenuCategoryEntriesImpl;


/**
 * Class that load menu structure from the file on object creation and save them
 * 
 * @author Matteo Magnani
 *
 */
public class MenuLoaderImpl implements MenuLoader {

    protected Map<MenuCategory, MenuCategoryEntriesImpl> menuStructure;
    
    /**
     * 
     * @param menu The menu to load
     * @throws IOException
     */
    public MenuLoaderImpl(final Menu menu) throws IOException {
        try (BufferedReader br = Files.newBufferedReader(Paths.get("res/storefiles/menu/" + menu.getFilename() + ".json"))){
            final Gson gson = new Gson();
            final Type buttonsListType = new TypeToken<Map<MenuCategory, MenuCategoryEntriesImpl>>() {}.getType();
            this.menuStructure = gson.fromJson(br, buttonsListType);
        } catch (FileNotFoundException e) {
            throw e;
        }
    }
    
    @Override
    public Map<MenuCategory, MenuCategoryEntries> getMenuStructure() {
        return new HashMap<>(this.menuStructure);
    }
}
