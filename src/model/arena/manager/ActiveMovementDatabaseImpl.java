package model.arena.manager;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import model.arena.entities.Entities;

/**
 * Database that associates entities to other entities that are on them.
 * 
 * @author Matteo Magnani
 *
 */
class ActiveMovementDatabaseImpl implements ActiveMovementDatabase {
    private final Map<Integer, Set<Entities>> dependences;

    /**
     * 
     */
    ActiveMovementDatabaseImpl() {
        this.dependences = new HashMap<>();
    }

    @Override
    public void putEntity(final int masterEntityCode, final Entities passiveEntity) {
        Set<Entities> add = new HashSet<>();
        add.add(passiveEntity);
        this.dependences.merge(masterEntityCode, add, (toAdd, old) -> {
            toAdd.addAll(old);
            return toAdd;
        });
    }

    @Override
    public Set<Entities> getRelativeEntities(final int masterEntityCode) {
        return this.dependences.get(masterEntityCode) == null ? new HashSet<>()
                : this.dependences.get(masterEntityCode);
    }

    @Override
    public void removeEntityFromAllDependences(final Entities entity) {
        this.dependences.entrySet().stream().map(t -> t.getValue()).forEach(t -> {
            if (t.contains(entity)) {
                t.remove(entity);
            }
        });
    }
}
