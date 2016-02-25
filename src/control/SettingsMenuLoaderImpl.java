package control;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class SettingsMenuLoaderImpl extends MenuLoaderImpl {
    private final GameSettings gameSettings;
    
    public SettingsMenuLoaderImpl(final Menu menu, final GameSettings gameSettings) {
        super(menu);
        this.gameSettings = gameSettings;
    }
    
    @Override
    public Map<MenuCategory, MenuCategoryEntries> getMenuStructure() {
        final Map<MenuCategory, MenuCategoryEntriesImpl> menu = super.getMenuStructurePrimitive();
        menu.get(MenuCategory.DIFFICULTY).setFocus(Optional.of(getDifficultyButton(this.gameSettings.getGameDifficulty())));
        return new HashMap<>(menu);
    }
    
    //TODO è un metodo statico
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
