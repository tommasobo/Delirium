package control.fileloading.settings;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.google.gson.Gson;

import control.game.settings.GameSettings;
import control.game.settings.GameSettingsImpl;

/**
 * Class that load game settings from the file on object creation and save them
 * 
 * @author Matteo Magnani
 *
 */
public class SettingsLoaderImpl implements SettingsLoader {
    
    private GameSettings gameSettings;
    
    /**
     * The constructor load settings from default file
     * @throws IOException
     */
    public SettingsLoaderImpl() throws IOException {
        try (BufferedReader br = Files.newBufferedReader(Paths.get("res/storefiles/gameSettings.json"));){
            final Gson gson = new Gson();
            this.gameSettings = gson.fromJson(br, GameSettingsImpl.class);
        } catch (IOException e) {
            throw e;
        }
    }

    @Override
    public GameSettings getGameSettings() {
        return gameSettings;
    }
    
    
}
