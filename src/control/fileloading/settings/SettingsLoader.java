package control.fileloading.settings;

import control.game.settings.GameSettings;

public interface SettingsLoader {

    /**
     * 
     * @return Game settings loaded
     */
    GameSettings getGameSettings();

}