package vaniercollege.controller;

import vaniercollege.model.WaveSimulator;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.sound.sampled.*;

/**
 * Handles sound generation and play.
 *
 * @author Yu Duo Zhang (2480549)
 */
public class SoundController {
    private static final int SAMPLE_RATE = 44100;
    private final WaveSimulator wave;
    private final Clip clip;    // Audio Clip to be played
    private byte[] buffer;      //Buffer to store the audio data.

    /**
     * Sound Controller constructor, requires the Wave to generate sound for.
     * @param wave the WaveSimulator object to generate sound for.
     */
    public SoundController(WaveSimulator wave) throws LineUnavailableException, IOException {
        this.wave = wave;
        this.clip = AudioSystem.getClip();
        updateBuffer();
        getSound();
    }

    /**
     * Get the byte value of the amplitude that will be put into the sound data buffer.
     *
     * @param amplitude The amplitude of the wave
     * @return The byte value of the amplitude
     */
    private byte getBufferValue(double amplitude) {
        // Converted to a range of -127 to 127 (byte range) so it can be put into the buffer which is a byte array.
        return Integer.valueOf((int) Math.round(amplitude * 127)).byteValue();
    }

    /**
     * Update the sound data buffer using the wave data
     */
    private void updateBuffer() {
        buffer = new byte[clip.getBufferSize()];
        for (int i = 0; i < buffer.length; i++) {
            buffer[i] = getBufferValue(wave.getYPos(0, i / SAMPLE_RATE));
        }
    }

    /**
     * Generate the sound from the buffer that has the wave data.
     */
    private void getSound() throws LineUnavailableException, IOException {
        clip.stop();
        clip.close();

        AudioFormat audioFormat = new AudioFormat(
                SAMPLE_RATE,  // sample rate
                8,  // sample size in bits
                1,  // channels
                true,  // signed
                false  // bigEndian
        );

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(buffer);
        AudioInputStream audioInputStream = new AudioInputStream(
                byteArrayInputStream,
                audioFormat,
                buffer.length
        );

        clip.open(audioInputStream);
    }

    /**
     * Start playing the sound.
     */
    public void start() {
        if (clip != null) {
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }

    /**
     * Stop playing the sound.
     */
    public void stop() {
        if (clip != null) {
            clip.stop();
        }
    }
}
