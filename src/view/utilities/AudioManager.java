package view.utilities;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.sound.sampled.AudioSystem;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Pair;
import view.configs.Actions;
import view.configs.Music;

/**
 * This class, created using singleton pattern, manages access to audio.
 */
public final class AudioManager {

    private static final AudioManager SINGLETON = new AudioManager();
    private final boolean audioState;
    private final List<Actions> allowedActions = Arrays.asList(Actions.MOVE, Actions.JUMP, Actions.SHOOT,
            Actions.DEATH);
    private Optional<Pair<Music, MediaPlayer>> theme = Optional.empty();
    private final Map<String, AudioClip> bufferedClip = new HashMap<>();
    private double effectsVolume = 1.0;

    /**
     * The constructor check if there are malfunctions in audio player and using this
     * test as a base set a boolean flag that indicates the availability of this
     * AudioManager.
     */
    private AudioManager() {
        boolean temp = true;
        try {
            final MediaPlayer mp = new MediaPlayer(new Media(
                    getClass().getResource("/music/" + Music.MENUTHEME.getName() + ".mp3").toExternalForm()));
            mp.setVolume(0);
            mp.stop();
        } catch (Exception e) {
            //e.printStackTrace();
            temp = false;
        }
        this.audioState = temp && AudioSystem.getMixerInfo().length > 0;
    }

    /**
     * Static method that returns the only permitted instance of this class.
     * 
     * @return An AudioManager instance
     */
    public static AudioManager getAudioManager() {
        return SINGLETON;
    }

    /**
     * Indicates if the manager is capable or not of reproducing audio on the
     * requiring system.
     * 
     * @return True if AudioManager is running correctly, false otherwise
     */
    public boolean isAudioAvailable() {
        return this.audioState;
    }

    /**
     * Play an audio clip.
     * 
     * @param action
     *            The action that shows which clip has to be played
     * @throws IllegalArgumentException
     *             If the required clip is not in the allowed ones list
     */
    public void playClip(final Actions action) {
        if (!this.allowedActions.contains(action)) {
            throw new IllegalArgumentException("No audio for action :" + action.getString());
        }
        if (!this.bufferedClip.containsKey(action.getString())) {
            this.bufferedClip.put(action.getString(), new AudioClip(
                    getClass().getResource("/music/effects/" + action.getString() + ".wav").toExternalForm()));
        }
        if (!this.bufferedClip.get(action.getString()).isPlaying()) {
            this.bufferedClip.get(action.getString()).play(this.effectsVolume);
        }
    }

    /**
     * Instantiate a media player to play game main themes. If the required
     * theme is already playing does nothing.
     * 
     * @param theme
     *            The theme to be played
     */
    public void playTheme(final Music theme) {
        if (!this.theme.isPresent() || this.theme.get().getKey() != theme) {
            final MediaPlayer mp = new MediaPlayer(
                    new Media(getClass().getResource("/music/" + theme.getName() + ".mp3").toExternalForm()));
            mp.setCycleCount(Integer.MAX_VALUE);
            if (this.theme.isPresent()) {
                mp.setVolume(this.theme.get().getValue().getVolume());
                this.theme.get().getValue().stop();
            }
            mp.play();
            this.theme = Optional.of(new Pair<>(theme, mp));
        }
    }

    /**
     * Get the volume of the theme currently playing.
     * 
     * @return The volume of the theme
     * @throws IllegalStateException
     *             If no theme is playing
     */
    public double getMusicVolume() {
        return this.theme.orElseThrow(IllegalStateException::new).getValue().getVolume();
    }

    /**
     * Set the volume of the theme currently playing.
     * 
     * @param volume
     *            The new volume of the theme
     * @throws IllegalStateException
     *             If no theme is playing
     */
    public void setMusicVolume(final double volume) {
        this.theme.orElseThrow(IllegalStateException::new).getValue().setVolume(volume);
    }

    /**
     * Get the audio clip's volume.
     * 
     * @return Effects volume
     */
    public double getEffectsVolume() {
        return this.effectsVolume;
    }

    /**
     * Set the audio clip's volume.
     * 
     * @param volume
     *            New effects volume
     */
    public void setEffectsVolume(final double volume) {
        this.effectsVolume = volume;
    }

    /**
     * Get the list of actions supported by this manager.
     * 
     * @return A list of actions
     */
    public List<Actions> getAllowedActions() {
        return Collections.unmodifiableList(this.allowedActions);
    }

}
