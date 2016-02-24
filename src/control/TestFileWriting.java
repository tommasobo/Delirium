package control;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
public class TestFileWriting {

    public static void main(String[] args) throws  IOException{
        Map<MenuCategory, MenuCategoryEntriesImpl> map = new HashMap<>();
        List<Buttons> def = new LinkedList<>();
        List<Buttons> difficulty = new LinkedList<>();
        //def.add(Buttons.NEXTLEVEL);
        def.add(Buttons.MAINMENU);
        //def.add(Buttons.EXIT);
        difficulty.add(Buttons.EASYMODE);
        difficulty.add(Buttons.HARDMODE);
        map.put(MenuCategory.DEFAULT, new MenuCategoryEntriesImpl(def, Optional.empty()));
        map.put(MenuCategory.DIFFICULTY, new MenuCategoryEntriesImpl(difficulty, Optional.empty()));
        try (BufferedWriter bw = Files.newBufferedWriter(Paths.get("src/settingsMenu.json"));){
            GsonBuilder builder = new GsonBuilder();
            builder.setPrettyPrinting().serializeNulls();
            Gson gson = builder.create();
            gson.toJson(map, bw);
            
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        try (BufferedReader br = Files.newBufferedReader(Paths.get("src/ciao.json"));){
            Gson gson = new Gson();
            Type buttonsListType = new TypeToken<Map<MenuCategory, MenuCategoryEntriesImpl>>() {}.getType();
            map = gson.fromJson(br, buttonsListType);
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
