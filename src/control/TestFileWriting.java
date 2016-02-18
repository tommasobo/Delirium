package control;

import java.io.IOException;

import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.TypeDescription;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import model.Actions;
public class TestFileWriting {

    public static void main(String[] args) throws  IOException{
        //EntitiesStoreImpl prova = new EntitiesStoreImpl(0, 10, LifeManager.WITH_LIFE, MovementTypes.HERO, 1, 1, 60, 20, model.Directions.LEFT, Actions.STOP, 0, 1000, 0, 300, 5, true, 3);
        
        DimensionStoreImpl dim = new DimensionStoreImpl();
        dim.setX(1);
        dim.setY(2);
        
        EntitiesStoreImpl prova = new EntitiesStoreImpl();
        prova.setAction(Actions.JUMP);
        
        EntitiesStoreImpl a = new EntitiesStoreImpl();
        prova.setAction(Actions.FALL);
        
        
        
        Constructor constructor = new Constructor();
        constructor.addTypeDescription(new TypeDescription(EntitiesStoreImpl.class, "!entity"));
        DumperOptions options = new DumperOptions();
        options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
        options.setExplicitStart(true);
        options.setExplicitEnd(true);
        
        Yaml yaml = new Yaml(options);
        /*BufferedWriter buff = Files.newBufferedWriter(Paths.get("src/ciao.yaml"));
        yaml.dump(dim, buff);
        yaml.dump(prova, buff);
        yaml.dump(a, buff);*/
        
        //EntitiesStoreImpl ciao = yaml.loadAs(Files.newInputStream(Paths.get("src/ciao.yaml")), EntitiesStoreImpl.class);
        //DimensionStoreImpl ci = yaml.loadAs(Files.newInputStream(Paths.get("src/ciao.yaml")), DimensionStoreImpl.class);
        yaml.loadAll("src/ciao.yaml");
        //System.out.println(ciao.getMaxY());
    }

}
