package control;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class MenuLoaderImpl implements MenuLoader {

    private Map<MenuCategory, MenuCategoryEntriesImpl> menuStructure;
    private final Menu menuType;
    
    public MenuLoaderImpl(final Menu menu) {
        this.menuType = menu;
        //TODO crea classe loader per ottenere gli input stream?
        //TODO mettere eccezioni per mancato file load
        try (BufferedReader br = Files.newBufferedReader(Paths.get("res/storefiles/menu/" + menu.getFilename() + ".json"))){
            final Gson gson = new Gson();
            final Type buttonsListType = new TypeToken<Map<MenuCategory, MenuCategoryEntriesImpl>>() {}.getType();
            this.menuStructure = gson.fromJson(br, buttonsListType);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public Map<MenuCategory, MenuCategoryEntries> getMenuStructure() {
        return new HashMap<>(this.menuStructure);
    }
    
    protected Map<MenuCategory, MenuCategoryEntriesImpl> getMenuStructurePrimitive() {
        return new HashMap<>(this.menuStructure);
    }

    public Menu getMenuType() {
        return menuType;
    }
}
