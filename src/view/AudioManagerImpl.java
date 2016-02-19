package view;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class AudioManagerImpl implements AudioManager {
    
    private final static AudioManager audioManager = new AudioManagerImpl();
    private final Map<String,AudioClip> bufferedClip = new HashMap<>();
    private MediaPlayer theme;
    private double effectsVolume = 1.0;
    
    private AudioManagerImpl() {};
    
    static AudioManager getAudioManager() {
        return audioManager;
    }

    @Override
    public void playClip(final Entities entity, final Actions action) {
        final String effectIdentifier = entity.getName() + action.getString();
        if (!this.bufferedClip.containsKey(effectIdentifier)) {
            this.bufferedClip.put(effectIdentifier, new AudioClip(effectIdentifier + ".wav"));
        }
        this.bufferedClip.get(effectIdentifier).play(this.effectsVolume);
    }
    
    

    @Override
    public double getMusicVolume() {
        return this.theme.getVolume();
    }

    @Override
    public void setMusicVolume(final double volume) {
        this.theme.setVolume(volume);
    }
    
    @Override
    public double getEffectsVolume() {
        return this.effectsVolume;
    }

    @Override
    public void setEffectsVolume(final double volume) {
        this.effectsVolume = volume;
    }

    @Override
    public void playTheme() {
        this.theme = new MediaPlayer(new Media(""));
        this.theme.setCycleCount(Integer.MAX_VALUE);
        this.theme.play();
    }

    @Override
    public void stopTheme() {
        this.theme.stop();
    }
    
}
