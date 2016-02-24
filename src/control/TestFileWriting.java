package control;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale.Category;
import java.util.Map;
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
        /*Map<MenuCategory, MenuCategoryEntriesImpl> map = new HashMap<>();
        List<Buttons> def = new LinkedList<>();
        List<Buttons> difficulty = new LinkedList<>();
        def.add(Buttons.NEWGAME);
        def.add(Buttons.NEXTLEVEL);
        difficulty.add(Buttons.MAINMENU);
        difficulty.add(Buttons.EXIT);
        map.put(MenuCategory.DEFAULT, new MenuCategoryEntriesImpl(def, Optional.empty()));
        map.put(MenuCategory.DIFFICULTY, new MenuCategoryEntriesImpl(difficulty, Optional.empty()));
        try (BufferedWriter bw = Files.newBufferedWriter(Paths.get("src/ciao.json"));){
            GsonBuilder builder = new GsonBuilder();
            builder.setPrettyPrinting().serializeNulls();
            Gson gson = builder.create();
            gson.toJson(map, bw);
            
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }*/
        
        try (BufferedReader br = Files.newBufferedReader(Paths.get("src/ciao.json"));){
            Gson gson = new Gson();
            Type buttonsListType = new TypeToken<Map<MenuCategory, MenuCategoryEntriesImpl>>() {}.getType();
            Map<MenuCategory, MenuCategoryEntries> map = gson.fromJson(br, buttonsListType);
            System.out.println(map.get(MenuCategory.DEFAULT).getEntries());
            System.out.println(map.get(MenuCategory.DIFFICULTY).getFocus().isPresent());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        /*BufferedReader br = Files.newBufferedReader(Paths.get("src/ciao.json"));
        LevelInfo up = gson.fromJson(br, LevelInfo.class);
        System.out.println(up.getEntities().get(1).getMovementInfo().isPresent());
        br.close();*/
    }

}
