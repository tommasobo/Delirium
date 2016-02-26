package control;

import java.util.List;

import model.EntitiesInfoToControl;
import view.ControlComunication;

public interface GameWorldTranslator {

    List<ControlComunication> entitiesListFromModelToView(List<EntitiesInfoToControl> listInfo);

}