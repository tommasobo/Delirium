package view.screens.sprites;

import java.util.LinkedList;
import java.util.List;

import javafx.geometry.Dimension2D;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import view.configs.Actions;
import view.configs.Directions;
import view.configs.Entities;

/**
 * Extension of the abstract sprite in order to manage no animated and no
 * updatable sprites.
 */
class NonUpdatableSprite extends AbstractSprite {

    private static final int DEF_WIDTH = 60;
    private static final int DEF_COMPOSITION_UNITS = 3;

    /**
     * NonUpdatableSprite Constructor.
     * 
     * @param entity
     *            The entity that this sprite must represent
     * @param code
     *            The entity ID
     * @param dimension
     *            The sprite dimension
     */
    NonUpdatableSprite(final Entities entity, final int code, final Dimension2D dimension) {
        super(entity, code, dimension);
    }

    @Override
    public void initSprite(final Actions action, final Directions direction) {
        if (!super.getSpritePane().getChildren().isEmpty()) {
            throw new IllegalStateException("Init already been called");
        }
        super.checkAction(action);
        final List<ImageView> temp = super.getResourcesManager().getResources(super.getEntity(), action.getString(),
                new Dimension2D(super.getSpritePane().getPrefWidth(), super.getSpritePane().getPrefHeight()));
        if (super.getEntity().getnAssets() < DEF_COMPOSITION_UNITS) {
            super.getSpritePane().getChildren().add(temp.get(0));
        } else {
            if (super.getSpritePane().getPrefWidth() <= DEF_WIDTH * DEF_COMPOSITION_UNITS) {
                super.getSpritePane().getChildren()
                        .add(this.getHorizontalComposedImage(temp,
                                (int) (super.getSpritePane().getPrefWidth() / DEF_COMPOSITION_UNITS),
                                (int) (super.getSpritePane().getPrefWidth() % DEF_COMPOSITION_UNITS)));
            } else {
                final List<ImageView> newList = new LinkedList<>();
                newList.add(temp.get(0));
                for (int i = 0; i <= (super.getSpritePane().getPrefWidth() / DEF_WIDTH) - DEF_COMPOSITION_UNITS; i++) {
                    newList.add(new ImageView(temp.get(1).getImage()));
                }
                newList.add(temp.get(2));
                super.getSpritePane().getChildren().add(this.getHorizontalComposedImage(newList, DEF_WIDTH,
                        (int) super.getSpritePane().getPrefWidth() % DEF_WIDTH));
            }
        }
    }

    /**
     * Method used to compose horizontal images using a variable number of
     * pieces.
     * 
     * @param images
     *            The list of ImageView to use
     * @param unit
     *            The width that every ImageView should have
     * @param offset
     *            The additional width that the central ImageView should have.
     * @return The result of composition
     */
    private HBox getHorizontalComposedImage(final List<ImageView> images, final int unit, final int offset) {
        final HBox box = new HBox();
        ImageView im;
        for (int i = 0; i < images.size(); i++) {
            im = images.get(i);
            if (i == images.size() / 2 + 1) {
                im.setFitWidth(unit + offset);
            } else {
                im.setFitWidth(unit);
            }
            box.getChildren().add(im);
        }
        return box;
    }

}
