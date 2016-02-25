package control;

import java.util.Map;

public interface Control {
	void startGame();
	void notifyEvent(ViewEvents event);
	Map<MenuCategory, MenuCategoryEntries> getButtons();

}
