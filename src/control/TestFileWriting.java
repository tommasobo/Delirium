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
        List<Levels> levels = new LinkedList<>();
        levels.add(Levels.LEVEL1);
        levels.add(Levels.LEVEL2);
        GameSettings gs = new GameSettings(levels);
        
        
        BufferedWriter bw = new BufferedWriter(new FileWriter("src/ciao.json"));
        gson.toJson(gs, bw);
        bw.close();
        
        /*BufferedReader br = Files.newBufferedReader(Paths.get("src/ciao.json"));
        LevelInfo up = gson.fromJson(br, LevelInfo.class);
        System.out.println(up.getEntities().get(1).getMovementInfo().isPresent());
        br.close();*/
    }

}
