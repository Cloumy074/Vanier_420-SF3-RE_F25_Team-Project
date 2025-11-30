package vaniercollege.model;

import lombok.*;
import vaniercollege.utils.WaveType;

/**
 *  Core logic for wave calculations.
 *
 * @author Yu Duo Zhang (2480549)
 */
public class WaveSimulator {
    @Getter
    @Setter
    private double amplitude;
    @Getter
    @Setter
    private double angWaveNum;
    @Getter
    @Setter
    private double angFreq;
    @Getter
    @Setter
    private double phaseDiff;
    @Getter
    @Setter
    private WaveType type;
    @Getter
    private double[][] points = new double[150][2];    // 150 because 15/0.1 = 150

    /**
     * No-args Constructor with Sample SIN wave data.
     */
    public WaveSimulator() {
        this.amplitude = 1;
        this.angWaveNum = 1;
        this.angFreq = 1;
        this.phaseDiff = 0;
        this.type = WaveType.SIN;
        this.setPoints(0);
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
        this.setPoints(0);
    }

    /**
     * Get the wave length by using the formula wavelength = velocity / frequency.
     * @return The Wavelength of the wave as a double.
     */
    public double getWaveLength() {
        return getSpeed() / getFrequency();
    }

    /**
     * Get the frequency by using the formula frequency = angular frequency / 2PI.
     * @return The Frequency of the wave as a double.
     */
    public double getFrequency() {
        return getAngFreq() / (2 * Math.PI);
    }

    /**
     * Get the velocity (or speed) by using the formula velocity = angular frequency / angular wave number
     * @return The Velocity (or speed) of the wave as a double
     */
    public double getSpeed() {
        return getAngFreq() / getAngWaveNum();
    }

    /**
     * Get the position of the wave for a certain position X at a certain time.
     * @param x The position X
     * @param t The time.
     * @return the Y position of the wave.
     */
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

    /**
     * Set all the Y positions of the Wave to make a series of points for the chart.
     */
    public void setPoints(double time) {
        double start = 0.1;
        double end = 15.00;
        int idx = 0;

        while (start <= end && idx < points.length) {
            points[idx][0] = start;
            points[idx][1] = getYPos(start, time);
            start = start + 0.1;
            idx++;
        }
    }

    /**
     * Convert the input String into double numbers.
     * @param input the String that user inputted
     * @return the number that user meant by the input.
     */
    public double convertToNum(String input) {
        input = input.replace(" ", "");
        double num = 0;
        // Discard all spaces
        input = input.replace(" ", "");

        // Handle PI
        if (input.contains("\\pi")) {
            int idxMult = input.indexOf("\\") - 1;
            // If there are other numbers before PI (Which means a multiplication to PI)
            if (idxMult != -1) {
                num += Double.parseDouble(input.replace("\\pi", "")) * Math.PI;
            } else {
                num += Math.PI;
            }
        }
        // Handle while typing but is still trying to convert issue
        else if (input.equals("-")) {
            return 0;
        } else if (input.contains("\\")) {
            return 0;
        } else if (input.isEmpty()) {
            return 0;
        }
        // If it does not need to handle PI, directly parse.
        else {
            num += Double.parseDouble(input);
        }

        return num;
    }

    @Override
    public String toString() {
        return String.format("%.2f,%.2f,%.2f,%.2f,%s", this.getAmplitude(), this.getAngWaveNum(), this.getAngFreq(), this.getPhaseDiff(), this.getType().toString().toUpperCase());
    }
}
