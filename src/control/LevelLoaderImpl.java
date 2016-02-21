package control;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

import org.yaml.snakeyaml.Yaml;

import model.EntitiesInfo;

public class LevelLoaderImpl {
    LevelInfo levelInfo;
    
    public LevelLoaderImpl(Levels menu) {
        //TODO crea classe loader per ottenere gli input stream?
        try (InputStream is = Files.newInputStream(Paths.get("res/storefiles/levels/" + menu.getFilename()))){
            this.levelInfo = new Yaml().loadAs(is, LevelInfo.class);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
