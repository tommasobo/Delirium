package view;

public interface AudioManager {
    
    void playClip(final Entities entity, final Actions action);
    void playTheme();
    void stopTheme();
    double getMusicVolume();
    void setMusicVolume(final double volume);
    double getEffectsVolume();
    void setEffectsVolume(final double volume);
    
}
