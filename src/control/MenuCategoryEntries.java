package control;

import java.util.List;
import java.util.Optional;

public interface MenuCategoryEntries {

    Optional<Buttons> getFocus();

    List<Buttons> getEntries();

}