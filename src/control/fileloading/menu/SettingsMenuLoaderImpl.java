package control.fileloading.menu;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import control.game.settings.GameDifficulty;
import control.game.settings.GameSettings;
import control.viewcomunication.Buttons;
import control.viewcomunication.MenuCategory;
import control.viewcomunication.MenuCategoryEntries;
import control.viewcomunication.MenuCategoryEntriesImpl;

/**
 * A menu loader that contains game settings in order to set the already checked
 * buttons on settings menu.
 * 
 * @author Matteo Magnani
 *
 */
public class SettingsMenuLoaderImpl extends MenuLoaderImpl {
    private final GameSettings gameSettings;

    /**
     * 
     * @param menu Menu to load
     * @param gameSettings Current game's settings
     * @throws IOException
     *          If something is wrong.
     */
    public SettingsMenuLoaderImpl(final Menu menu, final GameSettings gameSettings) throws IOException {
        super(menu);
        this.gameSettings = gameSettings;
    }

    @Override
    public Map<MenuCategory, MenuCategoryEntries> getMenuStructure() {
        final Map<MenuCategory, MenuCategoryEntriesImpl> menu = super.menuStructure;
        menu.get(MenuCategory.DIFFICULTY)
                .setFocus(Optional.of(getDifficultyButton(this.gameSettings.getGameDifficulty())));
        return new HashMap<>(menu);
    }

    /**
     * 
     * @param gameDifficulty
     * @return the view button that represent the current difficulty
     */
    private Buttons getDifficultyButton(final GameDifficulty gameDifficulty) {
        switch (gameDifficulty) {
        case DELIRIUM:
            return Buttons.DELIRIUMMODE;
        case EASY:
            return Buttons.EASYMODE;
        case HARD:
            return Buttons.HARDMODE;
        case NORMAL:
            return Buttons.NORMALMODE;
        default:
            throw new IllegalArgumentException();

        }
    }
}
