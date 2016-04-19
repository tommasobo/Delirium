package view.screens.sprites;

import javafx.animation.Animation.Status;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Dimension2D;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import javafx.util.Pair;
import view.configs.Actions;
import view.configs.Directions;
import view.configs.Entities;

/**
 * This class extends the abstract sprite's class and implements the
 * UpdatableSprite interface in order to manage animated and updatable sprites.
 */
class UpdatableSpriteImpl extends AbstractSprite implements UpdatableSprite {

    private static final int ANIMATION_SPEED = 125;
    private Pair<String, Timeline> currentAnimation;
    private final SpriteRemover remover;

    /**
     * UpdatableSpriteImpl Constructor.
     * 
     * @param entity
     *            The entity that this sprite must represent
     * @param code
     *            Entity's ID
     * @param dimension
     *            Sprite's dimension
     * @param remover
     *            The SpriteRemover's instance to notify when this sprite will
     *            finish death action.
     */
    UpdatableSpriteImpl(final Entities entity, final int code, final Dimension2D dimension,
            final SpriteRemover remover) {
        super(entity, code, dimension);
        this.remover = remover;
    }

    @Override
    public void initSprite(final Actions action, final Directions direction) {
        if (this.currentAnimation != null) {
            throw new IllegalStateException("Init already been called");
        }
        super.checkAction(action);
        final String composedAction = this.composeAction(action, direction);
        this.currentAnimation = new Pair<>(composedAction, animate(composedAction, action.getDuration()));
    }

    @Override
    public void updateSprite(final Actions action, final Directions direction) {
        this.checkInit();
        super.checkAction(action);
        final String composedAction = this.composeAction(action, direction);
        if (!composedAction.equals(this.currentAnimation.getKey())
                && (this.currentAnimation.getValue().cycleCountProperty().get() < 0
                        || (this.currentAnimation.getValue().cycleCountProperty().get() > 0
                                && this.currentAnimation.getValue().getStatus() != Status.RUNNING))) {
            this.currentAnimation.getValue().stop();
            this.currentAnimation = new Pair<>(composedAction, animate(composedAction, action.getDuration()));
        }
        if (action == Actions.DEATH) {
            currentAnimation.getValue().setOnFinished(e -> this.remover.removeSprite(super.getCode()));
        }
    }

    @Override
    public void pauseSpriteAnimation() {
        this.checkInit();
        this.currentAnimation.getValue().pause();
    }

    @Override
    public void resumeSpriteAnimation() {
        this.checkInit();
        this.currentAnimation.getValue().play();
    }

    /**
     * This method starts a new animation for this sprite using images taken from resource's
     * manager.
     * 
     * @param composedAction
     *            The string that identifies the resources that the manager will
     *            return
     * @param duration
     *            The duration of this animation
     * @return The Timeline animation generated
     */
    private Timeline animate(final String composedAction, final int duration) {

        final Timeline timeline = new Timeline();
        timeline.setCycleCount(duration);
        timeline.setAutoReverse(false);

        int cont = 0;

        for (final ImageView im : super.getResourcesManager().getResources(super.getEntity(), composedAction,
                new Dimension2D(super.getSpritePane().getPrefWidth(), super.getSpritePane().getPrefHeight()))) {
            final KeyFrame key = new KeyFrame(Duration.millis(cont), e -> {
                super.getSpritePane().getChildren().clear();
                super.getSpritePane().getChildren().add(im);
            });
            timeline.getKeyFrames().add(key);
            cont += ANIMATION_SPEED;
        }

        timeline.play();
        return timeline;
    }

    /**
     * This method composes action and direction into a string accepted by resource manager.
     * 
     * @param action
     *            Entity's action
     * @param direction
     *            Entity's direction
     * @return Composed String
     */
    private String composeAction(final Actions action, final Directions direction) {
        return action.getString() + "-" + direction.getName();
    }

    private void checkInit() {
        if (this.currentAnimation == null) {
            throw new IllegalStateException("Init not been called");
        }
    }

    @Override
    public void changeDimension(Dimension2D newDim) {
        if (newDim.getHeight() != super.getSpritePane().getPrefHeight() || newDim.getWidth() != super.getSpritePane().getPrefWidth()) {
            super.getSpritePane().setPrefHeight(newDim.getHeight());
            super.getSpritePane().setPrefWidth(newDim.getWidth());
        }
        
    }

}
