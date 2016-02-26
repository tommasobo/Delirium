package control;

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

public class MenuLoaderImpl implements MenuLoader {

    private Map<MenuCategory, MenuCategoryEntriesImpl> menuStructure;
    
    public MenuLoaderImpl(final Menu menu) throws IOException {
        //TODO crea classe loader per ottenere gli input stream?
        //TODO mettere eccezioni per mancato file load
        try (BufferedReader br = Files.newBufferedReader(Paths.get("res/storefiles/menu/" + menu.getFilename() + ".json"))){
            final Gson gson = new Gson();
            final Type buttonsListType = new TypeToken<Map<MenuCategory, MenuCategoryEntriesImpl>>() {}.getType();
            this.menuStructure = gson.fromJson(br, buttonsListType);
        } catch (FileNotFoundException e) {
            throw e;
        }
    }
    
    public Map<MenuCategory, MenuCategoryEntries> getMenuStructure() {
        return new HashMap<>(this.menuStructure);
    }
    
    protected Map<MenuCategory, MenuCategoryEntriesImpl> getMenuStructurePrimitive() {
        return new HashMap<>(this.menuStructure);
    }
}
