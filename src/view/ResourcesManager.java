package view;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import javafx.geometry.Dimension2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Pair;

public class ResourcesManager {
    
    private static final ResourcesManager resourceManager = new ResourcesManager();
    private final Map<Pair<String, Dimension2D>, List<ImageView>> buffer = new HashMap<>();

    private ResourcesManager() {}
    
    public static ResourcesManager getResourceManager() {
        return resourceManager;
    }

    public List<ImageView> getResources(final Entities entity, final String composedAction, final Dimension2D dimension) {
        final Pair<String, Dimension2D> key = new Pair<String, Dimension2D>(entity.getName() + "/" + composedAction, dimension);
        if (this.buffer.containsKey(key)) {
            return new LinkedList<>(buffer.get(key));
        }
        System.out.println(key.getKey());
        final List<ImageView> temp = new LinkedList<>();
        IntStream.range(0, entity.getnAssets()).forEach(n -> {
            temp.add(new ImageView(new Image("images/" + key.getKey() + Integer.toString(n) + ".png", dimension.getWidth(), dimension.getHeight(), false, true)));
        });
        this.buffer.put(key, temp);
        return new LinkedList<>(temp);
    }

}
