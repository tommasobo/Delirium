package view.screens;

import java.util.List;

import view.ControlComunication;
import view.Notifications;

public interface DynamicView {

    void updateScene(final List<ControlComunication> entities);

    void pauseScene(final Notifications notification);

    void playScene();

}
