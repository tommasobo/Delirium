package control;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

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
        
        EntitiesInfoStore prova = new EntitiesInfoStore();
        
        EntitiesInfoStore a = new EntitiesInfoStore();
        
        List<EntitiesInfoStore> ls = new LinkedList<>();
        ls.add(a);
        ls.add(prova);
        
        LevelInfo li= new LevelInfo();
        li.setEntities(ls);
        
        
        
        Constructor constructor = new Constructor();
        DumperOptions options = new DumperOptions();
        options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
        options.setExplicitStart(true);
        options.setExplicitEnd(true);
        
        Yaml yaml = new Yaml(options);
        //Yaml yaml = new Yaml();
        
        /*TypeDescription listDescription = new TypeDescription(List.class);
        BufferedWriter buff = Files.newBufferedWriter(Paths.get("src/ciao.yaml"));
        MenuInfo mi = new MenuInfo();
        List<Buttons> b = new LinkedList<>();
        b.add(Buttons.NEWGAME);
        b.add(Buttons.EXIT);
        mi.setButtons(b);
        yaml.dump(mi, buff);*/
        
        MenuInfo mi = yaml.loadAs(Files.newInputStream(Paths.get("src/ciao.yaml")), MenuInfo.class);
        System.out.println(mi.getButtons());
        //List<EntitiesStoreImpl> up = yaml.loadAs(Files.newInputStream(Paths.get("src/ciao.yaml")), ls.getClass());
    }

}
