package vaniercollege.model;

import lombok.*;
import vaniercollege.utils.WaveType;

/**
 *  Core logic for wave calculations.
 */
public class WaveSimulator {
    @Getter @Setter private double amplitude;
    @Getter @Setter private double angWaveNum;
    @Getter @Setter private double angFreq;
    @Getter @Setter private double phaseDiff;
    @Getter @Setter private WaveType type;
    private double waveLength;
    private double frequency;
    private double speed;
    @Getter private double[][] points =  new double[1500][2];

    /**
     * No-args Constructor with Sample SIN wave data.
     */
    public WaveSimulator() {
        this.amplitude = 1;
        this.angWaveNum = 1;
        this.angFreq = 1;
        this.phaseDiff = 0;
        this.type = WaveType.SIN;
        this.speed = getSpeed();
        this.frequency = getFrequency();
        this.waveLength = getWaveLength();
        this.setPoints();
    }
    
    /**
     * Full-args Constructor
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
        this.speed = this.getSpeed();
        this.frequency = this.getFrequency();
        this.waveLength = this.getWaveLength();
        this.setPoints();
    }
    
    public double getWaveLength() {
        return getAngFreq() / (getAngWaveNum() * getFrequency());
    }
    
    public double getFrequency() {
        return getAngFreq() / (2 * Math.PI);
    }
    
    public double getSpeed() {
        return getAngFreq() / getAngWaveNum();
    }
    
    public double getYPos(double x, double t) {
        WaveType waveType = getType();
        double result = 0;
        
        switch (waveType) {
            case SIN -> {
                result = getAmplitude() * Math.sin((getAngWaveNum() * x) - (getAngFreq() * t) + getPhaseDiff());
            }
            case COS -> {
                result = getAmplitude() * Math.cos((getAngWaveNum() * x) - (getAngFreq() * t) + getPhaseDiff());
            }
        }
        return result;
    }

    public void setPoints() {
        double start = 0.01;
        double end = 15.00;
        int idx = 0;

        while (start <= end && idx < points.length) {
            points[idx][0] = start;
            points[idx][1] = getYPos(start, 0);
            start = start + 0.1;
            idx++;
        }
    }

    public double setNum(String input) {
        double num = 0;
        input = input.replace(" ","");

        if(input.contains("\\pi")) {
            int idxMult =  input.indexOf("\\") - 1;
            if(idxMult != -1) {
                if(input.charAt(idxMult) == '-') {
                    return -3.14;
                } else {
                    num += Double.parseDouble(String.valueOf(input.charAt(idxMult))) * 3.14;
                }
            } else {
                num += 3.14;
            }
            input = input.replace("\\pi", "");
        } else if(input.equals("-")) {
            return 0;
        } else if (input.contains("\\")) {
            return 0;
        } else {
            num += Double.parseDouble(input);
        }

        return num;
    }
}
