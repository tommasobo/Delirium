package control;

import java.util.LinkedList;
import java.util.List;

import model.EntitiesInfoToControl;
import model.Position;
import view.ControlComunication;
import view.ViewPhysicalProperties;

public class GameWorldTranslatorImpl implements GameWorldTranslator {
    private final EntitiesDatabase database;
    private final int screenMoltiplicatorFactor;
    public GameWorldTranslatorImpl(final EntitiesDatabase database, final int screenMoltiplicatorFactor) {
        super();
        this.database = database;
        this.screenMoltiplicatorFactor = screenMoltiplicatorFactor;
    }
    
    //eventualmente da sostare dentro controller impl
    @Override
    public List<ControlComunication> entitiesListFromModelToView(final List<EntitiesInfoToControl> listInfo) {
        final List<ControlComunication> viewList = new LinkedList<>();
        listInfo.stream().forEach(e -> {
            viewList.add(new ControlComunication(e.getCode(), this.database.getViewEntity(e.getCode()), e.getLife(),
                    positionFromModeltoView(e), Translator.getViewActionsForEntities(e)));
        });
        return viewList;
    }

    
    @Override
    public ViewPhysicalProperties positionFromModeltoView(final EntitiesInfoToControl info) {
        // TODO mi serve una interfaccia position senza setter, anche un point
        final Position p = info.getPosition();
        return new ViewPhysicalProperties(p.getPoint().getX() * this.screenMoltiplicatorFactor,
                (this.database.getArenaDimension().getHeight() - p.getPoint().getY() - p.getDimension().getHeight()) * this.screenMoltiplicatorFactor,
                p.getDimension().getWidth() * this.screenMoltiplicatorFactor, p.getDimension().getHeight() * this.screenMoltiplicatorFactor, info.getSpeed().isPresent() ? info.getSpeed().get() * this.screenMoltiplicatorFactor : 0,
                Translator.directionFromModeltoView(p.getDirection()));
    }
}
