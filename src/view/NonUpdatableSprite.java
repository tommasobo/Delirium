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
            double unit = super.getSpritePane().getPrefWidth() / 100;
            HBox box = new HBox();
            ImageView im = temp.get(0);
            im.setFitWidth(100);
            im.autosize();
            box.getChildren().add(im);
            im = temp.get(1);
            for (int i = 0; i< unit-2; i--) {
                final ImageView iv = new ImageView(im.getImage());
                iv.setFitWidth(100);
                iv.autosize();
                box.getChildren().add(iv);
            }
            im = temp.get(2);
            im.setFitWidth(100);
            im.autosize();
            box.getChildren().add(im);
            super.getSpritePane().getChildren().add(box);
          //ignorance mode : off

        }
    }

}
