package control.fileloading.settings;

import control.game.settings.GameSettings;

/**
 * Interface that declares methods for a settings loader. 
 * @author Matteo Magnani
 *
 */
public interface SettingsLoader {

    /**
     * 
     * @return Game settings loaded
     */
    GameSettings getGameSettings();

}