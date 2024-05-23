package main;

import java.net.URL;
import javax.sound.sampled.*;

public class Sound {
    private Clip[] clips = new Clip[30];
    private URL[] soundURL = new URL[30];
    private FloatControl[] volumeControls = new FloatControl[30];

    public Sound() {
        soundURL[0] = getClass().getResource("/sound/newbie_melody.mid");
        soundURL[1] = getClass().getResource("/sfx/item_pick_up.wav");
        soundURL[2] = getClass().getResource("/sfx/open_door.wav");
        soundURL[3] = getClass().getResource("/sfx/success_jingle.mid");
        soundURL[4] = getClass().getResource("/sfx/death/slime_death.wav");
        soundURL[5] = getClass().getResource("/sfx/attack/weapon_slash.wav");
        soundURL[6] = getClass().getResource("/sfx/damage/player_hit.wav");
        soundURL[7] = getClass().getResource("/sfx/damage/slime_hit.wav");

        soundURL[8] = getClass().getResource("/sfx/jingles/attack_level_up.mid");
        soundURL[9] = getClass().getResource("/sfx/jingles/strength_level_up.mid");
        soundURL[10] = getClass().getResource("/sfx/jingles/defence_level_up.mid");
        soundURL[11] = getClass().getResource("/sfx/jingles/hitpoints_level_up.mid");

        // Pre-load all clips
        for (int i = 0; i < soundURL.length; i++) {
            if (soundURL[i] != null) {
                try {
                    AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);

                    // Convert the AudioInputStream to a supported format if necessary
                    AudioFormat baseFormat = ais.getFormat();
                    AudioFormat targetFormat = new AudioFormat(
                            AudioFormat.Encoding.PCM_SIGNED,
                            baseFormat.getSampleRate(),
                            16, // 16 bits per sample
                            baseFormat.getChannels(),
                            baseFormat.getChannels() * 2,
                            baseFormat.getSampleRate(),
                            false // Big endian
                    );

                    AudioInputStream convertedAis = AudioSystem.getAudioInputStream(targetFormat, ais);

                    clips[i] = AudioSystem.getClip();
                    clips[i].open(convertedAis);

                    volumeControls[i] = (FloatControl) clips[i].getControl(FloatControl.Type.MASTER_GAIN);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void play(int i) {
        if (clips[i] != null) {
            clips[i].setFramePosition(0); // rewind to the beginning
            clips[i].start();
        }
    }

    public void stop(int i) {
        if (clips[i] != null) {
            clips[i].stop();
        }
    }

    public void loop(int i) {
        if (clips[i] != null) {
            clips[i].loop(Clip.LOOP_CONTINUOUSLY);
        }
    }

    public void setVolume(int i, float volume) {
        if (volumeControls[i] != null) {
            volumeControls[i].setValue(volume);
        }
    }
}
