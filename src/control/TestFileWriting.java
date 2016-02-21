package control;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.TypeDescription;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import model.Actions;
import model.MovementInfo;
import model.ShootTypes;
public class TestFileWriting {

    public static void main(String[] args) throws  IOException{
        //EntitiesStoreImpl prova = new EntitiesStoreImpl(0, 10, LifeManager.WITH_LIFE, MovementTypes.HERO, 1, 1, 60, 20, model.Directions.LEFT, Actions.STOP, 0, 1000, 0, 300, 5, true, 3);
        
        DimensionStore dim = new DimensionStore();
        
        EntitiesInfoStore prova = new EntitiesInfoStore();
        ShootInfoStore sis = new ShootInfoStore();
        sis.setBulletDamage(10);
        sis.setShootType(ShootTypes.HERO);
        MovementInfoStore mi = new MovementInfoStore();
        mi.setCanFly(true);
        prova.setMovementInfo(mi);
        prova.setShootInfo(sis);
        
        
        Constructor constructor = new Constructor();
        DumperOptions options = new DumperOptions();
        options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
        options.setExplicitStart(true);
        options.setExplicitEnd(true);
        
        Yaml yaml = new Yaml(options);
        
        //TypeDescription listDescription = new TypeDescription(List.class);
        BufferedWriter buff = Files.newBufferedWriter(Paths.get("src/ciao.yaml"));
        yaml.dump(prova, buff);
        //EntitiesInfoStore up = yaml.loadAs(Files.newInputStream(Paths.get("src/ciao.yaml")), EntitiesInfoStore.class);
        //System.out.println(up.getShootInfo().get().getShootType());
        
    }

}
