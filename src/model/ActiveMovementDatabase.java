package model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ActiveMovementDatabase {
    private final Map<Integer, Set<Entities>> dependences;
    
    public ActiveMovementDatabase() {
        this.dependences = new HashMap<>();
    }
    
    public void putEntity(int masterEntityCode, Entities passiveEntity) {
        Set<Entities> add = new HashSet<>();
        add.add(passiveEntity);
        this.dependences.merge(masterEntityCode, add, (toAdd, old) -> {
            toAdd.addAll(old);
            return toAdd;
        });
    }
    
    public Set<Entities> getRelativeEntities(int masterEntityCode) {
        //TODO METTERE CONTROLLO PIU BELLO
        return this.dependences.get(masterEntityCode) == null ? new HashSet<>() : this.dependences.get(masterEntityCode);
    }
    
    public void removeEntityFromAllDependences(Entities entity) {
        this.dependences.entrySet().stream().map(t -> t.getValue()).forEach(t -> {
            //TODO controllo inutile
            if(t.contains(entity)) {
                t.remove(entity);
            }
        });
    }
}
