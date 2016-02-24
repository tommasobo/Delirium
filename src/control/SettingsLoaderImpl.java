package control;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class SettingsLoaderImpl implements SettingsLoader {
    
    private GameSettings gameSettings;
    
    public SettingsLoaderImpl() {
        //TODO crea classe loader per ottenere gli input stream?
        //TODO mettere eccezioni per mancato file load
        try (BufferedReader br = Files.newBufferedReader(Paths.get("res/storefiles/gameSettings.json"));){
            Gson gson = new Gson();
            this.gameSettings = gson.fromJson(br, GameSettings.class);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public GameSettings getGameSettings() {
        return gameSettings;
    }
    
    
}
