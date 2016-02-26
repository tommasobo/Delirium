package view.screens;

import java.util.List;

import view.configs.Notifications;
import view.utilities.ControlComunication;

public interface DynamicView {

    void updateScene(final List<ControlComunication> entities);

    void pauseScene(final Notifications notification);

    void playScene();

}
