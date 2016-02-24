package control;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class MenuLoaderImpl implements MenuLoader {

    private List<Buttons> buttons;
    private final Menu menuType;
    
    public MenuLoaderImpl(Menu menu) {
        this.menuType = menu;
        //TODO crea classe loader per ottenere gli input stream?
        //TODO mettere eccezioni per mancato file load
        try (BufferedReader br = Files.newBufferedReader(Paths.get("res/storefiles/menu/" + menu.getFilename() + ".json"));){
            Gson gson = new Gson();
            Type buttonsListType = new TypeToken<List<Buttons>>() {}.getType();
            this.buttons = gson.fromJson(br, buttonsListType);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public List<Buttons> getButtonsList() {
        return new LinkedList<>(buttons);
    }

    public Menu getMenuType() {
        return menuType;
    }
}
