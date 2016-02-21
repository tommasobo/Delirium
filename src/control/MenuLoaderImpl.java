package control;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.yaml.snakeyaml.Yaml;

public class MenuLoaderImpl implements MenuLoader {

    MenuInfo menuInfo;
    
    public MenuLoaderImpl(Menu menu) {
        //TODO crea classe loader per ottenere gli input stream?
        try (InputStream is = Files.newInputStream(Paths.get("res/storefiles/menu/" + menu.getFilename()))){
            this.menuInfo = new Yaml().loadAs(is, MenuInfo.class);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    //TODO cosa metto? il get del menuInfo o il get diretto della buttonList?
    public List<Buttons> getButtonsList() {
        return this.menuInfo.getButtons();
    }
}
