package control;

import model.Actions;
import model.Directions;
import model.LifeManager;
import model.MovementTypes;

public class EntitiesStoreImpl {
        private int code;
        private int life;
        private LifeManager lifemanager;
        private MovementTypes movementTypes;
        // private final Position position;
        private int x;
        private int y;
        private int height;
        private int width;
        private Directions direction;
        private Actions action;
        // private final Bounds bounds;
        private int minX;
        private int maxX;
        private int minY;
        private int maxY;
        private int speed;
        private boolean canFly;
        private int contactDamage;
        
        /*public EntitiesStoreImpl(int code, int life, LifeManager lifemanager, MovementTypes movementTypes, int x, int y,
                int height, int width, Directions direction, Actions action, int minX, int maxX, int minY, int maxY,
                int speed, boolean canFly, int contactDamage) {
            this.code = code;
            this.life = life;
            this.lifemanager = lifemanager;
            this.movementTypes = movementTypes;
            this.x = x;
            this.y = y;
            this.height = height;
            this.width = width;
            this.direction = direction;
            this.action = action;
            this.minX = minX;
            this.maxX = maxX;
            this.minY = minY;
            this.maxY = maxY;
            this.speed = speed;
            this.canFly = canFly;
            this.contactDamage = contactDamage;
        }*/

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public int getLife() {
            return life;
        }

        public void setLife(int life) {
            this.life = life;
        }

        public LifeManager getLifemanager() {
            return lifemanager;
        }

        public void setLifemanager(LifeManager lifemanager) {
            this.lifemanager = lifemanager;
        }

        public MovementTypes getMovementTypes() {
            return movementTypes;
        }

        public void setMovementTypes(MovementTypes movementTypes) {
            this.movementTypes = movementTypes;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        public model.Directions getDirection() {
            return direction;
        }

        public void setDirection(model.Directions direction) {
            this.direction = direction;
        }

        public Actions getAction() {
            return action;
        }

        public void setAction(Actions action) {
            this.action = action;
        }

        public int getMinX() {
            return minX;
        }

        public void setMinX(int minX) {
            this.minX = minX;
        }

        public int getMaxX() {
            return maxX;
        }

        public void setMaxX(int maxX) {
            this.maxX = maxX;
        }

        public int getMinY() {
            return minY;
        }

        public void setMinY(int minY) {
            this.minY = minY;
        }

        public int getMaxY() {
            return maxY;
        }

        public void setMaxY(int maxY) {
            this.maxY = maxY;
        }

        public int getSpeed() {
            return speed;
        }

        public void setSpeed(int speed) {
            this.speed = speed;
        }

        public boolean isCanFly() {
            return canFly;
        }

        public void setCanFly(boolean canFly) {
            this.canFly = canFly;
        }

        public int getContactDamage() {
            return contactDamage;
        }

        public void setContactDamage(int contactDamage) {
            this.contactDamage = contactDamage;
        }
    
        
       
}
