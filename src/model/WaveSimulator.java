package model;

import lombok.*;
import utils.WaveType;

/**
 *  Core logic for wave calculations.
 */
public class WaveSimulator {
    @Getter @Setter private double amplitude;
    @Getter @Setter private double angWaveNum;
    @Getter @Setter private double angFreq;
    @Getter @Setter private double phaseDiff;
    @Getter @Setter private WaveType type;

    /**
     * No-args Contructor with Sample SIN wave data.
     */
    public WaveSimulator() {
        this.amplitude = 1;
        this.angWaveNum = 1;
        this.angFreq = 1;
        this.phaseDiff = 0;
        this.type = WaveType.SIN;
    }
    
    /**
     * Full-args Contructor 
     * @param amplitude The Amplitude of the Wave
     * @param angWaveNum The Angular Wave Number of the Wave
     * @param angFreq The Angular Frequency of the Wave
     * @param phaseDiff The Phase Difference of the Wave
     * @param type The Type of the Wave (SIN or COS)
     */
    public WaveSimulator(double amplitude, double angWaveNum, double angFreq, double phaseDiff, WaveType type) {
        this.amplitude = amplitude;
        this.angWaveNum = angWaveNum;
        this.angFreq = angFreq;
        this.phaseDiff = phaseDiff;
        this.type = type;
    }
}
