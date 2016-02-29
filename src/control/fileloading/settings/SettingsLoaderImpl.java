package control.fileloading.settings;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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
     * 
     * @throws IOException
     */
    public SettingsLoaderImpl() throws IOException {
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(this.getClass().getResourceAsStream("/storefiles/gameSettings.json")))) {
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
