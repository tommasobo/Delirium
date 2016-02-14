package view;

import java.util.List;

import javafx.geometry.Dimension2D;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class NonUpdatableSprite extends AbstractSprite {

    public NonUpdatableSprite(final Entities entity, final Dimension2D dimension) {
        super(entity, dimension);
    }

    @Override
    public void initSprite(Actions action) {
        final List<ImageView> temp = super.getResourcesManager().getResources(action);
        if (temp.size() == 1) {
            super.getSpritePane().getChildren().add(temp.get(0));
        } else {
            
            //ignorance mode : on
            HBox box = new HBox();
            if (super.getSpritePane().getPrefWidth() <= 180) {
                final int unit = (int)super.getSpritePane().getPrefWidth() / 3;
                final int offset = (int)super.getSpritePane().getPrefWidth() % 3;
                ImageView im = temp.get(0);
                im.setFitWidth(unit);
                box.getChildren().add(im);
                im = temp.get(1);
                im.setFitWidth(unit + offset);
                box.getChildren().add(im);
                im = temp.get(2);
                im.setFitWidth(unit);
                box.getChildren().add(im);
            } else {
                final int unit = (int)super.getSpritePane().getPrefWidth() / 60;
                final int offset = (int)super.getSpritePane().getPrefWidth() % 60;
                ImageView im = temp.get(0);
                im.setFitWidth(60);
                box.getChildren().add(im);
                for (int i = unit - 3; i > 0; i--) {
                    final ImageView copy = new ImageView(temp.get(1).getImage());
                    copy.setFitWidth(60);
                    box.getChildren().add(copy);
                }
                im = temp.get(1);
                im.setFitWidth(60 + offset);
                box.getChildren().add(im);
                im = temp.get(2);
                im.setFitWidth(60);
                box.getChildren().add(im);
            }
            super.getSpritePane().getChildren().add(box);
          //ignorance mode : off
        }
    }

}
