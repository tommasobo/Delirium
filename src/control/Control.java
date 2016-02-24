package control;

import java.util.Map;

public interface Control {
	public void startGame();
	public void notifyEvent(ViewEvents event);
	public Map<MenuCategory, MenuCategoryEntries> getButtons();

}
