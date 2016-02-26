package control;

import java.util.List;

import model.transfertentities.EntitiesInfoToControl;
import view.utilities.ControlComunication;

public interface GameWorldTranslator {

    List<ControlComunication> entitiesListFromModelToView(List<EntitiesInfoToControl> listInfo);

}