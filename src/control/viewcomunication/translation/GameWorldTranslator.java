package control.viewcomunication.translation;

import java.util.List;

import model.transfertentities.EntitiesInfoToControl;
import view.utilities.ControlCommunication;

/**
 * Interface that declare methods to translate model's entities to elements that
 * the view can correctly print
 * 
 * @author Matteo Magnani
 *
 */
public interface GameWorldTranslator {

    /**
     * The method translate the input model's entities to view entities.
     * Positions are modified according to view's necessities
     * 
     * @param listInfo
     *            The list of model's entities
     * @return The list of view's entities
     */
    List<ControlCommunication> entitiesListFromModelToView(List<EntitiesInfoToControl> listInfo);

}