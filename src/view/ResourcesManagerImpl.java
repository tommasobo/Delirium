package view;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

import javafx.geometry.Dimension2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ResourcesManagerImpl implements ResourcesManager {
    
    private final Entities entity;
    private final Dimension2D dimension;

    public ResourcesManagerImpl(final Entities entityName, final Dimension2D dimension) {
        this.entity = entityName;
        this.dimension = dimension;
    }

    @Override
    public List<ImageView> getResources(final String composedAction) {
        final List<ImageView> temp = new LinkedList<>();
        
        IntStream.range(0, this.entity.getnAssets()).forEach(n -> {
            System.out.println(this.entity.getName() + "/" + composedAction + Integer.toString(n) + ".png");
            temp.add(new ImageView(new Image(this.entity.getName() + "/" + composedAction + Integer.toString(n) + ".png", dimension.getWidth(), dimension.getHeight(), false, true)));
        });
        return temp;
    }

}
