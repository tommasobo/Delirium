package model;

import java.util.LinkedList;
import java.util.List;

public class ArenaImpl implements Arena {
    private Hero hero;
    private Entities goal;
    private List<Entities> entities;
    private List<Bullet> bullets;
    
    private EntitiesVisitor addVisitor = new EntitiesVisitorImpl(this);
    
    
    
    public ArenaImpl() {
        this.entities = new LinkedList<>();
        this.bullets = new LinkedList<>();
    }

    /* (non-Javadoc)
     * @see model.Arena#add(model.Entities)
     */
    @Override
    public void add(Entities entities) {
        entities.accept(addVisitor);
    }
    
    /* (non-Javadoc)
     * @see model.Arena#add(model.EntitiesImpl)
     */
    @Override
    public void add(EntitiesImpl entitiesImpl) {
        this.entities.add(entitiesImpl);
        if (entitiesImpl.getCode() == -1) {
            this.goal = entitiesImpl;
        }
    }

    /* (non-Javadoc)
     * @see model.Arena#add(model.Hero)
     */
    @Override
    public void add(HeroImpl hero) {
        this.hero = hero;
        this.entities.add(hero);
    }

    /* (non-Javadoc)
     * @see model.Arena#add(model.Bullet)
     */
    @Override
    public void add(Bullet bullet) {
        this.bullets.add(bullet);
    }

    /* (non-Javadoc)
     * @see model.Arena#getHero()
     */
    @Override
    public Hero getHero() {
        return hero;
    }

    /* (non-Javadoc)
     * @see model.Arena#getGoal()
     */
    @Override
    public Entities getGoal() {
        return goal;
    }

    /* (non-Javadoc)
     * @see model.Arena#getEntities()
     */
    @Override
    public List<Entities> getEntities() {
        return entities;
    }

    /* (non-Javadoc)
     * @see model.Arena#getBullets()
     */
    @Override
    public List<Bullet> getBullets() {
        return bullets;
    }
    
    
    
    

    
}
