package control;

import java.util.List;

import model.EntitiesInfoToControl;
import view.ControlComunication;
import view.ViewPhysicalProperties;

public interface GameWorldTranslator {

    //eventualmente da sostare dentro controller impl
    List<ControlComunication> entitiesListFromModelToView(List<EntitiesInfoToControl> listInfo);

    ViewPhysicalProperties positionFromModeltoView(EntitiesInfoToControl info);

}