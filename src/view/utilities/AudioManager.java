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

public final class AudioManager {

    private static final AudioManager SINGLETON = new AudioManager();
    private final boolean audioState;
    private final List<Actions> allowedActions = Arrays.asList(Actions.MOVE, Actions.JUMP, Actions.SHOOT, Actions.DEATH);
    private Optional<Pair<Music, MediaPlayer>> theme = Optional.empty();
    private final Map<String, AudioClip> bufferedClip = new HashMap<>();
    private double effectsVolume = 1.0;

    private AudioManager() {
        boolean temp = true;
        try {
            final MediaPlayer mp = new MediaPlayer(
                    new Media(getClass().getResource("../music/" + Music.MENUTHEME.getName() + ".mp3").toExternalForm()));
            mp.setVolume(0);
            mp.stop();
        } catch (Exception e) {
            temp = false;
        }
        this.audioState = temp && AudioSystem.getMixerInfo().length > 0;
    }

    public static AudioManager getAudioManager() {
        return SINGLETON;
    }

    public boolean isAudioAvailable() {
        return this.audioState;
    }

    public void playClip(final Actions action) {
        if (!this.allowedActions.contains(action)) {
            throw new IllegalArgumentException("No audio for action :" + action.getString());
        }
        if (!this.bufferedClip.containsKey(action.getString())) {
            this.bufferedClip.put(action.getString(), new AudioClip(
                    getClass().getResource("../music/effects/" + action.getString() + ".wav").toExternalForm()));
        }
        if (!this.bufferedClip.get(action.getString()).isPlaying()) {
            this.bufferedClip.get(action.getString()).play(this.effectsVolume);
        }
    }

    public void playTheme(final Music theme) {
        if (!this.theme.isPresent() || this.theme.get().getKey() != theme) {
            final MediaPlayer mp = new MediaPlayer(
                    new Media(getClass().getResource("../music/" + theme.getName() + ".mp3").toExternalForm()));
            mp.setCycleCount(Integer.MAX_VALUE);
            if (this.theme.isPresent()) {
                mp.setVolume(this.theme.get().getValue().getVolume());
                this.theme.get().getValue().stop();
            }
            mp.play();
            this.theme = Optional.of(new Pair<>(theme, mp));
        }
    }

    public double getMusicVolume() {
        return this.theme.orElseThrow(IllegalStateException::new).getValue().getVolume();
    }

    public void setMusicVolume(final double volume) {
        this.theme.orElseThrow(IllegalStateException::new).getValue().setVolume(volume);
    }

    public double getEffectsVolume() {
        return this.effectsVolume;
    }

    public void setEffectsVolume(final double volume) {
        this.effectsVolume = volume;
    }
    
    public List<Actions> getAllowedActions() {
        return Collections.unmodifiableList(this.allowedActions);
    }

}
