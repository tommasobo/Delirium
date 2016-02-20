package view;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.sound.sampled.AudioSystem;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class AudioManager {
    
    private final static AudioManager audioManager = new AudioManager();
    private final boolean audioState;
    private Optional<MediaPlayer> theme = Optional.empty();
    private final Map<String,AudioClip> bufferedClip = new HashMap<>();
    private double effectsVolume = 1.0;
    
    private AudioManager() {
        this.audioState = AudioSystem.getMixerInfo().length > 0;
    }
    
    static AudioManager getAudioManager() {
        return audioManager;
    }
    
    public boolean isAudioAvailable() {
        return this.audioState;
    }

    public void playClip(final Entities entity, final Actions action) {
        final String effectIdentifier = entity.getName() + action.getString();
        if (!this.bufferedClip.containsKey(effectIdentifier)) {
            this.bufferedClip.put(effectIdentifier, new AudioClip(effectIdentifier + ".wav"));
        }
        this.bufferedClip.get(effectIdentifier).play(this.effectsVolume);
    }
   
    public void playTheme(final String id) {
        final MediaPlayer mp = new MediaPlayer(new Media(getClass().getResource("/music/" + id + ".mp3").toExternalForm()));
        mp.setCycleCount(Integer.MAX_VALUE);
        if (this.theme.isPresent()) {
            mp.setVolume(this.theme.get().getVolume());
            this.theme.get().stop();
        }
        mp.play();
        this.theme = Optional.of(mp);
    }
    
    public double getMusicVolume() {
        return this.theme.orElseThrow(IllegalStateException::new).getVolume();
    }

    public void setMusicVolume(final double volume) {
        this.theme.orElseThrow(IllegalStateException::new).setVolume(volume);
    }
    
    public double getEffectsVolume() {
        return this.effectsVolume;
    }

    public void setEffectsVolume(final double volume) {
        this.effectsVolume = volume;
    }
    
    public void muteTheme() {
        this.theme.orElseThrow(IllegalStateException::new).setMute(true);
    }
    
    public void unMuteTheme() {
        this.theme.orElseThrow(IllegalStateException::new).setMute(false);
    }
}
