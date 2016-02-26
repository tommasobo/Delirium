package view;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javafx.geometry.Dimension2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Pair;

public final class ResourcesManager {

    private static final ResourcesManager SINGLETON = new ResourcesManager();
    private final Map<Pair<String, Dimension2D>, List<Image>> buffer = new HashMap<>();

    private ResourcesManager() {
    }

    public static ResourcesManager getResourceManager() {
        return SINGLETON;
    }

    public List<ImageView> getResources(final Entities entity, final String composedAction,
            final Dimension2D dimension) {
        final Pair<String, Dimension2D> key = new Pair<String, Dimension2D>(entity.getName() + "/" + composedAction,
                dimension);
        if (this.buffer.containsKey(key)) {
            return this.buffer.get(key).stream().map(e -> new ImageView(e)).collect(Collectors.toList());
        }
        final List<Image> temp = new LinkedList<>();
        IntStream.range(0, entity.getnAssets()).forEach(n -> {
            temp.add(new Image("images/" + key.getKey() + Integer.toString(n) + ".png", dimension.getWidth(),
                    dimension.getHeight(), false, true));
        });
        this.buffer.put(key, temp);
        return temp.stream().map(e -> new ImageView(e)).collect(Collectors.toList());
    }

}
