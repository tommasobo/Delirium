package control;

import java.util.List;

public interface Control {
	public void notifyEvent(ViewEvents event);
	public List<Buttons> getButtons();

}
