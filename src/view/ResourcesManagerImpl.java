package view;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import javafx.geometry.Dimension2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ResourcesManagerImpl implements ResourcesManager {
    
    private final Entities entity;
    private final Dimension2D dimension;
    private final Map<String, List<ImageView>> buffer = new HashMap<>();

    public ResourcesManagerImpl(final Entities entityName, final Dimension2D dimension) {
        this.entity = entityName;
        this.dimension = dimension;
    }

    @Override
    public List<ImageView> getResources(final String composedAction) {
        if (this.buffer.containsKey(composedAction)) {
            return new LinkedList<>(buffer.get(composedAction));
        }
        System.out.println(this.entity.getName() + "-" +composedAction);
        final List<ImageView> temp = new LinkedList<>();
        IntStream.range(0, this.entity.getnAssets()).forEach(n -> {
            temp.add(new ImageView(new Image(this.entity.getName() + "/" + composedAction + Integer.toString(n) + ".png", dimension.getWidth(), dimension.getHeight(), false, true)));
        });
        this.buffer.put(composedAction, temp);
        return new LinkedList<>(temp);
    }

}
