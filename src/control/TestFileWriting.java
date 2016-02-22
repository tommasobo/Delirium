package control;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonWriter;

import java.lang.reflect.Type;

import model.Actions;
import model.Bounds;
import model.Directions;
import model.EntitiesInfo;
import model.EntitiesInfoImpl;
import model.LifePattern;
import model.MovementInfo;
import model.MovementInfoImpl;
import model.MovementTypes;
import model.Position;
import model.ShootInfoImpl;
import model.ShootTypes;
import view.Entities;
public class TestFileWriting {

    public static void main(String[] args) throws  IOException{
        
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting().serializeNulls();
        Gson gson = builder.create();
        
        EntitiesInfoStore entity = new EntitiesInfoStore(0,
                new Position(new Point(0, 200), Directions.RIGHT, new Dimension(40, 60)),
                Optional.of(new MovementInfoImpl(10, new Bounds(0, 1000, 0, 300), Actions.STOP, false,
                        MovementTypes.HERO)),
                300, LifePattern.WITH_LIFE,
                Optional.of(new ShootInfoImpl(5, ShootTypes.HERO, 1, MovementTypes.HORIZONTAL_LINEAR, 200, 10)), Optional.of(55), Entities.MAGNO);
        
        EntitiesInfoStore entity2 = new EntitiesInfoStore(0,
                        new Position(new Point(300, 100), Directions.RIGHT, new Dimension(50, 70)),
                        Optional.of(new MovementInfoImpl(3, new Bounds(0, 1000, 40, 300), Actions.JUMP, false,
                                MovementTypes.VERTICAL_LINEAR)),
                        10, LifePattern.WITH_LIFE,
                        Optional.empty(), Optional.empty(), Entities.VOLPE);
        
        EntitiesInfoStore entity3 = new EntitiesInfoStore(0,
                        new Position(new Point(0, 0), Directions.RIGHT, new Dimension(1000, 40)),
                        Optional.empty(),
                        300, LifePattern.WITHOUT_LIFE,
                        Optional.empty(), Optional.empty(), Entities.GROUND);
        
        List<EntitiesInfoStore> ls = new LinkedList<>();
        ls.add(entity);
        ls.add(entity2);
        ls.add(entity3);
        LevelInfo li = new LevelInfo(ls, new Dimension(100, 300));
        
        List<Buttons> buttons = new LinkedList<>();
        buttons.add(Buttons.NEWGAME);
        buttons.add(Buttons.EXIT);
        
        
        BufferedWriter bw = new BufferedWriter(new FileWriter("src/ciao.json"));
        gson.toJson(li, bw);
        bw.close();
        
        /*BufferedReader br = Files.newBufferedReader(Paths.get("src/ciao.json"));
        LevelInfo up = gson.fromJson(br, LevelInfo.class);
        System.out.println(up.getEntities().get(1).getMovementInfo().isPresent());
        br.close();*/
    }

}
